package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    Adapter adapter;
    RecyclerView recyclerView;
    TextView tvName, tvEmail;
    Button btnCheckTicket;
    String name, email;
    Intent checkTicketIntent;
    SharedPreferences preferences;

    WifiReceiver wifiReceiver = new WifiReceiver();
    private AirplaneReceiver airReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        airReceiver = new AirplaneReceiver();
        IntentFilter airFilter = new IntentFilter();
        airFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        this.registerReceiver(airReceiver, airFilter);

        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        btnCheckTicket = findViewById(R.id.btnCheckTicket);

        preferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        name = preferences.getString("name", "not available");
        email = preferences.getString("email", "not available");

        tvName.setText(name);
        tvEmail.setText(email);

        recyclerView = findViewById(R.id.homeRecycler);
        adapter = new Adapter(this, getCities());

        recyclerView.setAdapter(adapter);
        btnCheckTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkTicketIntent=new Intent(HomeActivity.this,ReceiptActivity.class);
                startActivity(checkTicketIntent);
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(wifiReceiver, filter);
    }

    @Override
    protected void onStop(){
        super.onStop();
        unregisterReceiver(wifiReceiver);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(airReceiver);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            Intent loginIntent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(loginIntent);
            return true;
        } else if (item.getItemId() == R.id.callCenter || item.getItemId() == R.id.email || item.getItemId() == R.id.editUser) {
            Toast.makeText(this, "This feature isn't available yet", Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private List<Model> getCities() {
        List<Model> allCities = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            allCities.add(new Model("Alexandria", "7500 EGP", R.drawable.city1, "Egypt's second-largest city with a population of around four million, Alexandria is the country's largest seaport and the center of much of its maritime activity. It is also one of the oldest cities in Egypt and lies around 225 kilometers northwest of Cairo."));
            allCities.add(new Model("Beni Suef", "5000 EGP", R.drawable.city2, "Beni Suef is the capital city of the Beni Suef Governorate in Egypt. Beni Suef is the location of Beni Suef University.[2] An important agricultural trade centre on the west bank of the Nile River, the city is located 110 km (70 miles) south of Cairo."));
            allCities.add(new Model("Cairo", "2500 EGP", R.drawable.city3, "Cairo is the capital of Egypt and the largest city in Africa, with a name that means \"the victorious city.\" It is located on both banks of the River Nile near the head of the river's delta in northern Egypt and has been settled for more than 6,000 years, serving as the capital of numerous Egyptian kingdoms."));
        }
        return allCities;
    }
}