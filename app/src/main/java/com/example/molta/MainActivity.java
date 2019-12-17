package com.example.molta;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button capture, logout;
    ImageView inputImage;

    private static final int CAPTURE_CAMERA = 1;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        capture = findViewById(R.id.btnImageCapture);
        logout = findViewById(R.id.btnLogout);
        inputImage = findViewById(R.id.imageInput);

        firebaseAuth = FirebaseAuth.getInstance();

        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent,CAPTURE_CAMERA);
                }
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"You have Logout",Toast.LENGTH_SHORT).show();

                firebaseAuth.signOut();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK &&  requestCode == CAPTURE_CAMERA) {
            Bundle extras = data.getExtras();
            Bitmap b = (Bitmap) data.getExtras().get("data");
            Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
            intent.putExtra(ResultActivity.GET_GAMBAR,b);
            startActivity(intent);
        }
    }
}
