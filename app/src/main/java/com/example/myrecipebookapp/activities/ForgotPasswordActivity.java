package com.example.myrecipebookapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myrecipebookapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.rpc.context.AttributeContext;

public class ForgotPasswordActivity extends AppCompatActivity {
    private EditText emailInput;
    private Button sendResetEmailButton,
            backToLoginButton;
    private FirebaseAuth auth;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        auth = FirebaseAuth.getInstance();
// Initialize UI elements
        emailInput = findViewById(R.id.email_input);
        sendResetEmailButton = findViewById(R.id.send_reset_email_button);
        backToLoginButton = findViewById(R.id.back_to_login_button);
        // Handle Reset Email Sending
        sendResetEmailButton.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();
            if (TextUtils.isEmpty(email)) { emailInput.setError("Email is required");
                return;
            } // Send reset email
            auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(ForgotPasswordActivity.this, "Reset email sent. Check your inbox.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class));
                            finish(); }
                        else {
                            Toast.makeText(ForgotPasswordActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show(); }

                    });
        });
        // Navigate back to Login Activity
        backToLoginButton.setOnClickListener(v ->
        { startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class));
            finish();
        });
    }
}


