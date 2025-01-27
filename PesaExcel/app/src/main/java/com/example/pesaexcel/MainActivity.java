package com.example.pesaexcel;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.database.Cursor;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Telephony;
import android.util.Log;

import android.widget.ListAdapter;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> smsList=new ArrayList<>();
    private static final int READ_SMS_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.listView);
    }
}