package com.vijay.jsonwizard.demo.activities;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.vijay.jsonwizard.activities.JsonFormActivity;
import com.vijay.jsonwizard.demo.R;
import com.vijay.jsonwizard.demo.utils.CommonUtils;

import me.tankery.permission.PermissionRequestActivity;

/**
 * Created by vijay on 5/16/15.
 */
public class MainActivity extends AppCompatActivity {

    private static final int    REQUEST_CODE_GET_JSON = 1;
    private static final String TAG                   = "MainActivity";
    private static final String DATA_JSON_PATH        = "data.json";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

             //Request for permission
        PermissionRequestActivity.start(this,11,
                new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE},
                "Please provide the permission", "app won't work without the permissions");


        findViewById(R.id.button_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, JsonFormActivity.class);
                String json = CommonUtils.loadJSONFromAsset(getApplicationContext(), DATA_JSON_PATH);
                intent.putExtra("json", json);
                startActivityForResult(intent, REQUEST_CODE_GET_JSON);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_GET_JSON && resultCode == RESULT_OK) {
            Log.d(TAG, data.getStringExtra("json"));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
