package kr.ac.cbnu.checkcheck;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import kr.ac.cbnu.checkcheck.persongroupmanagement.PersonGroupListActivity;
import kr.ac.cbnu.checkcheck.ui.IdentificationActivity;

public class SelectActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            /*case  R.id.btn_attend:
                Intent intent0 = new Intent(this, AttendActivity.class);
                startActivity(intent0);
                break;*/

            case R.id.btn_attend:
                Intent intent0 = new Intent(this, DateSelectActivity.class);
                startActivity(intent0);
                break;

            case R.id.btn_stats:
                Intent intent1 = new Intent(this, StatsActivity.class);
                startActivity(intent1);
                break;

            case R.id.btn_student:
                Intent intent2 = new Intent(this, PersonGroupListActivity.class);
                startActivity(intent2);
                break;

        }
    }
}