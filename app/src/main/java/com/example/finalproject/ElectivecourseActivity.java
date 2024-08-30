package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ElectivecourseActivity extends AppCompatActivity {

    private ArrayList<Product> productList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private ProductDataSource productDataSource;

    Intent intent;
    Button btnBack, btnRegisterProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_electivecourse);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnRegisterProduct = (Button) findViewById(R.id.btnRegisterProduct);
        recyclerView = findViewById(R.id.electiveCourseRV);

        // 리사이클러뷰
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productDataSource = new ProductDataSource(this);
        productDataSource.open();

        adapter = new ProductAdapter(productList, productDataSource, this);
        recyclerView.setAdapter(adapter);

        loadProducts("교양");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(ElectivecourseActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnRegisterProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(ElectivecourseActivity.this, RegisterProductActivity.class);
                intent.putExtra("category", "교양"); // "교양" 카테고리 전달
                startActivity(intent);
            }
        });
    }

    // 특정 카테고리의 제품 로드
    private void loadProducts(String category) {
        productList.clear();
        productList.addAll(productDataSource.getProductsByCategory("교양"));
        adapter.notifyDataSetChanged();  // RecyclerView 업데이트
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadProducts("정보보안학과");  // 화면이 다시 활성화될 때 데이터 새로 고침
    }

    @Override
    protected void onDestroy() {
        productDataSource.close();
        super.onDestroy();
    }
}