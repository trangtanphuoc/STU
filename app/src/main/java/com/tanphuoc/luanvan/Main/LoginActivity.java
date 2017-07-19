package com.tanphuoc.luanvan.Main;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.tanphuoc.luanvan.R;

public class LoginActivity extends AppCompatActivity {
    ProgressBar progressBar;
    Button btnStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        addControl();
        addEvent();
    }

    private void addEvent() {
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            progressBar.setVisibility(View.VISIBLE);
                btnStart.setVisibility(View.GONE);
                CountDownTimer countDownTimer =new CountDownTimer(5000,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                        int current =progressBar.getProgress();
                        progressBar.setProgress(current + 10);
                    }

                    @Override
                    public void onFinish() {
                        Intent intent =new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                };
                countDownTimer.start();
            }
        });
    }

    private void addControl() {
        progressBar= (ProgressBar) findViewById(R.id.pbLogin);
        btnStart = (Button) findViewById(R.id.btnStart);
    }
}
