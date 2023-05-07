package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class ReceiptActivity extends AppCompatActivity {

    //private ImageView imgTour;
    private TextView name, email, phone, nameTour, price_Tour, totalPrice;
    private Button btnConfirm;
    AlertDialog dialog;

    SharedPreferences preferences;

    String CHANNEL_ID = "Travel App v2.6 (BETA)";
    private static final String KEY_IMG_TOUR = "img_tour";

    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";

    private static final String KEY_NAME_TOUR = "name_tour";
    private static final String KEY_COUNT_ITEMS = "count_items";
    private static final String KEY_PRICE_TOUR = "price_tour";

    private static final String KEY_TOTAL_PRICE = "total_price";
    private JobScheduler scheduler;
    private static final int JOB_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        //imgTour = findViewById(R.id.imgTour);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);

        nameTour = findViewById(R.id.nameTour);
        price_Tour = findViewById(R.id.price_Tour);
        totalPrice = findViewById(R.id.totalPrice);

        btnConfirm = findViewById(R.id.btnConfirm);

        preferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);

        String imgTourview = preferences.getString(KEY_IMG_TOUR, null);

        String nameView = preferences.getString(KEY_NAME, null);
        String emailView = preferences.getString(KEY_EMAIL, null);
        String phoneView = preferences.getString(KEY_PHONE, null);

        String nameTourView = preferences.getString("nameTour", null);
        String totalItemsView = preferences.getString(KEY_COUNT_ITEMS, null);
        String priceView = preferences.getString("priceTour", null);

        String totalPriceView = preferences.getString("priceTour", null);

        if (nameView != null || emailView != null || phoneView != null || nameTourView != null || totalItemsView != null || priceView != null || totalPriceView != null) {
           // Glide.with(this).asBitmap().load(imgTourview).into(imgTour);
            name.setText(nameView);
            email.setText(emailView);
            phone.setText(phoneView);
            nameTour.setText(nameTourView);
            price_Tour.setText(priceView);
            totalPrice.setText(totalPriceView);
        }


        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new AlertDialog.Builder(ReceiptActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setTitle("Message")
                        .setMessage("\nAre you sure booked this tour?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(ReceiptActivity.this, "Success Booked Ticket", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(ReceiptActivity.this, HomeActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                ComponentName serviceName = new ComponentName(getPackageName(),
                                        NotificationJobService.class.getName());
                                JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, serviceName);
                                JobInfo myJobInfo = builder.build();
                                scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
                                scheduler.schedule(myJobInfo);
                                startActivity(intent);
                                //PendingIntent pendingIntent = PendingIntent.getActivity(ReceiptActivity.this, 0, intent, 0);

                               /* //notification bar
                                NotificationCompat.Builder builder = new NotificationCompat.Builder(ReceiptActivity.this, CHANNEL_ID)
                                        .setSmallIcon(R.drawable.ic_ticket)
                                        .setContentTitle("Detail Ticket")
                                        .setStyle(new NotificationCompat.BigTextStyle()
                                                .bigText("\nYour Ticket Successfully Booked!\n" +
                                                        "===================================" + "\n" +

                                                        "Name person \t: " + nameView + "\n" +
                                                        "Name city: " + nameTourView + "\n" +
                                                        "Total cities: " + totalItemsView + "\n" +
                                                        "Total price: " + totalPriceView + "\n" +
                                                        "==================================="))
                                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                        // Set the intent that will fire when the user taps the notification
                                        .setContentIntent(pendingIntent)
                                        .setAutoCancel(true);
                                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(ReceiptActivity.this);
                                if (ActivityCompat.checkSelfPermission(ReceiptActivity.this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                                    // TODO: Consider calling
                                    //    ActivityCompat#requestPermissions
                                    // here to request the missing permissions, and then overriding
                                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                    //                                          int[] grantResults)
                                    // to handle the case where the user grants the permission. See the documentation
                                    // for ActivityCompat#requestPermissions for more details.
                                    return;
                                }
                                notificationManager.notify(25, builder.build());
                                finish();*/
                            }
                        })
                        .setNegativeButton( "No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                resetDetailTour();
                                Toast.makeText( ReceiptActivity.this, "Fail Booked Ticket", Toast.LENGTH_LONG).show();
                                Intent intent= new Intent( ReceiptActivity.this, ReceiptActivity.class);//Dashboard
                                startActivity(intent);
                                finish();
                            }
                        })
                        .show();
            }
        });
    }
    private void  resetDetailTour(){
        SharedPreferences.Editor editor= preferences.edit();
        editor.putString(KEY_NAME_TOUR, null);
        editor.putString(KEY_COUNT_ITEMS, null);
        editor.putString(KEY_TOTAL_PRICE, null);
        editor.apply();
    }

    protected void onDestroy() {
        if (scheduler != null) {
            scheduler.cancelAll();
            scheduler = null;
        }
        super.onDestroy();
    }
}
