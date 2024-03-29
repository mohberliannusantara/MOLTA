package com.example.molta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;

public class RegisterActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    TextView login;
    Button btnRegister;

    private FirebaseAuth firebaseAuth;

    String TAG = "Register";

//    SignInButton googleLoginButton;
//    LoginButton facebookLoginButton;
//
//    private final static int RC_SIGN_IN = 2;

//    GoogleApiClient googleApiClient;
//
//    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);

        login = findViewById(R.id.link_login);

//        googleLoginButton = findViewById(R.id.googleLogin);
//        facebookLoginButton = findViewById(R.id.facebookLogin);
//
        firebaseAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                try {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                   if (task.isSuccessful()) {
                                       Log.d(TAG, "createUserWithEmail:success");
                                       FirebaseUser user = firebaseAuth.getCurrentUser();

                                       Toast.makeText(RegisterActivity.this, "Authentication Succes.", Toast.LENGTH_SHORT).show();

                                       Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                       startActivity(intent);
                                   } else {
                                       Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                       Toast.makeText(RegisterActivity.this, "Authentication failed." + task.getException(),
                                               Toast.LENGTH_SHORT).show();
                                   }
                                }
                            });

                } catch (Exception e) {
                    Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
