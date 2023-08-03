package com.example.lab8.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.lab8.MainActivity;
import com.example.lab8.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText eedEmail;
    private EditText edtPassword;
    private Button btnLogin;
    private Button btnGoToResetPass;
    private Button btngoToSignup;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        auth = FirebaseAuth.getInstance();
//        if (auth.getCurrentUser() != null) {
//            startActivity(new Intent(LoginActivity.this, MainActivity.class));
//            finish();
//        }
        setContentView(R.layout.activity_login);

        eedEmail = (EditText) findViewById(R.id.email);
        edtPassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnGoToResetPass = (Button) findViewById(R.id.btn_goToResetPass);
        btngoToSignup = (Button) findViewById(R.id.btn_goToSignUp);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(this);
        btngoToSignup.setOnClickListener(this);
        btnGoToResetPass.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_login){
            String email = eedEmail.getText().toString().trim();
            final String password = edtPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin để Đăng nhập tài khoản!", Toast.LENGTH_SHORT).show();
            }

            progressBar.setVisibility(View.VISIBLE);
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressBar.setVisibility(View.GONE);
                    if (!task.isSuccessful()) {
                        if (password.length() < 6) {
                            edtPassword.setError(getString(R.string.minimum_password));
                        } else {
                            Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        } else if (view.getId() == R.id.btn_goToSignUp) {
            startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            finish();
        } else if (view.getId() == R.id.btn_goToResetPass) {
            startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
            finish();
        }
    }
}