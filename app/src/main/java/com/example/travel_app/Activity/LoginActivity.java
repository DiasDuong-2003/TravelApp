package com.example.travel_app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.travel_app.R;
import com.example.travel_app.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends BaseActivity {
    ActivityLoginBinding binding;
    FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        binding.registerNow.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
            startActivity(intent);
            finish();
        });

        binding.btnLogin.setOnClickListener(view -> {
            binding.progressBar.setVisibility(View.VISIBLE);
            String email, password;
            email = String.valueOf(binding.email.getText());
            password = String.valueOf(binding.password.getText());

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this, "Nhập địa chỉ email!", Toast.LENGTH_SHORT).show();
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Nhập mật khẩu!", Toast.LENGTH_SHORT).show();
            }

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            binding.progressBar.setVisibility(View.GONE);
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Đăng nhập thành công!",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {

                                Toast.makeText(LoginActivity.this, "Xác thực thất bại!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });

        binding.backBtn.setOnClickListener(view -> finish());

        binding.facebookBtn.setOnClickListener(view -> {

        });

        binding.twitterBtn.setOnClickListener(view -> {

        });
    }
}