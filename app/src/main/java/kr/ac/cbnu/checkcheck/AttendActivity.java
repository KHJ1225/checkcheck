package kr.ac.cbnu.checkcheck;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class AttendActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attend);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case  R.id.btn_start:
                Intent intent0 = new Intent(this, Class_startActivity.class);
                startActivity(intent0);
                break;

            case R.id.btn_stop:
                Intent intent1 = new Intent(this, Class_stopActivity.class);
                startActivity(intent1);
                break;
        }
    }
}