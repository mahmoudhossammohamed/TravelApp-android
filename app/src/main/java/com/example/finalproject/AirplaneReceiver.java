package com.example.finalproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AirplaneReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String intentAction = intent.getAction();
        if (intentAction != null){
            String toastmessage = "unknown intent action";
            switch(intentAction){
                case Intent.ACTION_AIRPLANE_MODE_CHANGED:
                    toastmessage = "Airplane mode changed";
                    break;

            }
            //display the toast
            Toast.makeText(context, toastmessage,Toast.LENGTH_SHORT).show();
        }
    }
}
