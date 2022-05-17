package com.example.do_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn_text1, btn_text2, btn_naver, btn_call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_text2 = findViewById(R.id.btn_text2);

        btn_text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "버튼이 눌렸습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onButton1Clicked(View v) {
        Toast.makeText(this, "Button이 눌렸습니다.", Toast.LENGTH_SHORT).show();
    }

    public void onButton_naverClicked(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http:www.naver.com"));
        startActivity(intent);
    }

    public void onButton_callClicked(View v) {
        Intent intent = new Intent(String.valueOf(MainActivity.this), Uri.parse("tell:010-1000-1000"));
        startActivity(intent);
    }

}