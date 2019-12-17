package com.example.molta;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    ImageView image;
    Button back;

    public static  final String GET_GAMBAR ="getgambar";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        image = findViewById(R.id.imageCapture);
        back = findViewById(R.id.btnBack);

        if (getIntent().hasExtra(GET_GAMBAR)){
            Bitmap b = getIntent().getParcelableExtra(GET_GAMBAR);
            image.setImageBitmap(b);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
