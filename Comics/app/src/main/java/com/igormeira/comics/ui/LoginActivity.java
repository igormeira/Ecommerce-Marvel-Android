package com.igormeira.comics.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.igormeira.comics.R;

/**
 * Activity que realiza login
 */
public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    public TextInputLayout emailField, passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        configLoginButton();
        configFields();
    }

    private void configFields() {
        emailField = findViewById(R.id.email_editText);
        passwordField = findViewById(R.id.password_editText);
    }

    private void configLoginButton() {
        loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(this::onButtonClick);
    }

    /**
     * Chama ComicsActivity
     *
     * @param v
     */
    public void onButtonClick(View v){
        Intent myIntent = new Intent(LoginActivity.this, ComicsActivity.class);
        startActivity(myIntent);
    }

    @Override
    public void onBackPressed() {

    }
}
