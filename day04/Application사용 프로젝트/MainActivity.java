package com.example.appicationex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView tv1;
    EditText edt1;
    Button btn1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        tv1 = (TextView) findViewById(R.id.tv1);
        edt1 = (EditText) findViewById(R.id.edt1);
        btn1 = (Button) findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApplication myApp = (MyApplication) getApplication();

                String str = edt1.getText().toString();
                edt1.setText("");

                myApp.setGlobalString(str);
                tv1.setText("ID : " + myApp.getGlobalString());
            }
        });
    }
}