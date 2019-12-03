package com.shahjahan.retrofitandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private ProgressBar pBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pBar = findViewById(R.id.pBar);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        pBar.setVisibility(View.GONE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                params.put("deviceId", "device123");

                ApiRepository apiRepository = new ApiRepository();


                login(params, apiRepository);
            }
        });


    }

    private void login(Map<String, String> params, final ApiRepository apiRepository) {
        pBar.setVisibility(View.VISIBLE);
        apiRepository.login(params, new ApiResponseCallback<AccessToken>() {
            @Override
            public void onResponseSuccess(ApiResponse<AccessToken> response) {
                pBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                App.getContext().setAccessToken(response.getData());
                getUserList(apiRepository);
            }

            @Override
            public void onRequestFailure(Throwable t) {
                pBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onApiError(ApiError apiError) {
                pBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, apiError.message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getUserList(ApiRepository apiRepository) {
        pBar.setVisibility(View.VISIBLE);
        apiRepository.getUserList(new ApiResponseCallback<ArrayList<User>>() {
            @Override
            public void onResponseSuccess(ApiResponse<ArrayList<User>> response) {
                pBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, response.getData().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRequestFailure(Throwable t) {
                pBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onApiError(ApiError apiError) {
                pBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, apiError.message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
