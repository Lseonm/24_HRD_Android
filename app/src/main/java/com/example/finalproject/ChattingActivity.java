package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ChattingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chatting);

        Button btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        BottomNavigationView chattingBnavi = findViewById(R.id.chattingBnavi);

        chattingBnavi.setSelectedItemId(R.id.menu_chat);

        chattingBnavi.setOnNavigationItemSelectedListener(item -> {
            if(item.getItemId() == R.id.menu_home){
                startActivity(new Intent(this, MainActivity.class));
                return true;
            } else if (item.getItemId() == R.id.menu_myInfo) {
                startActivity(new Intent(this, MyInfoActivity.class));
                return true;
            }
            return false;
        });
    }
}
