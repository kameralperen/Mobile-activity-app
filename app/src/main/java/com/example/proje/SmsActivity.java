package com.example.proje;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SmsActivity extends AppCompatActivity {
    EditText input_tel, input_sms;

    Button btn_gonder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        input_tel = findViewById(R.id.tel_input);
        input_sms = findViewById(R.id.mesaj_input);

        btn_gonder = findViewById(R.id.btn_sms);
        btn_gonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(SmsActivity.this, android.Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                    sendSMS();
                } else {
                    ActivityCompat.requestPermissions(SmsActivity.this, new String[]{android.Manifest.permission.SEND_SMS}, 100);

                }

            }
        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            sendSMS();
        } else {
            Toast.makeText(this, "SMS izni reddedildi.", Toast.LENGTH_SHORT).show();
        }
    }




    private void sendSMS() {
        String phoneNumber = input_tel.getText().toString();
        String message = input_sms.getText().toString();

        try {
            if(!phoneNumber.isEmpty() && !message.isEmpty()) {

                SmsManager smsManager = SmsManager.getDefault();

                smsManager.sendTextMessage(phoneNumber, null, message, null, null);

                showToast("SMS başarıyla gönderildi");

            }else {
                Toast.makeText(this, "Lütfen telefonunuzu ve mesajınızı giriniz", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            showToast("SMS Gönderme Başarısız!");
            e.printStackTrace();
        }

    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

