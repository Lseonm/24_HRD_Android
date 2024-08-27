package com.example.threadex;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView tv1;
    private int count = 0;
    private boolean isRunning  = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tv1 = (TextView) findViewById(R.id.tv1);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRunning) {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    count++;
                    Log.i("test", "Thread running" + count);
                    runOnUiThread(new Runnable() { // UI 업데이트
                        @Override
                        public void run() {
                            tv1.setText(String.valueOf(count));
                        }
                    });
                }
            }
        });

        thread.start();

    }

    // 액티비티 종료 시 스레드 종료 (메모리 누수 방지)
    @Override
    protected void onDestroy() {
        super.onDestroy();
        isRunning = false;
    }
}