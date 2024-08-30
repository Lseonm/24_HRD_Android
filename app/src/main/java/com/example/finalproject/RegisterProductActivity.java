package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterProductActivity extends AppCompatActivity {
    Button btnBack,btnRegister;
    EditText edtTitle, edtMoney, edtText;
    CheckBox cbFree;
    Spinner spnCategory;

    private ProductDataSource productDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_product);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        edtTitle = (EditText) findViewById(R.id.edtTitle);
        edtMoney = (EditText) findViewById(R.id.edtMoney);
        edtText = (EditText) findViewById(R.id.edtText);

        cbFree = (CheckBox) findViewById(R.id.cbFree);

        spnCategory = (Spinner) findViewById(R.id.spnCategory);




        // 데이터베이스 초기화
        productDataSource = new ProductDataSource(this);
        productDataSource.open();



        // 버튼 기능
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeText();
            }
        });


        // CheckBox 기능
        cbFree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cbFree.isChecked())
                    edtMoney.setVisibility(View.INVISIBLE);
                else
                    edtMoney.setVisibility(View.VISIBLE);
            }
        });


        // Spiner 기능
        spnCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    // 텍스트 작성 메소드
    protected void writeText() {
        String title = edtTitle.getText().toString().trim();
        String price = edtMoney.getText().toString().trim();
        String description = edtText.getText().toString().trim();
        boolean isFree = cbFree.isChecked();
        String category = spnCategory.getSelectedItem().toString();


        if (!title.isEmpty() && !description.isEmpty() && (isFree || !price.isEmpty())) {
            Product product = new Product(title, price, description, isFree, category);
            productDataSource.addProduct(product);

            Toast.makeText(getApplicationContext(), "제품 등록이 완료되었습니다.", Toast.LENGTH_LONG).show();

            Intent intent = null;
            if (category.equals("정보보안학과")) {
                intent = new Intent(RegisterProductActivity.this, InformationSecurityActivity.class);
            } else if(category.equals("교양")){
                intent = new Intent(RegisterProductActivity.this, ElectivecourseActivity.class);
            }
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "내용을 작성해주세요.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        productDataSource.close();
        super.onDestroy();
    }
}