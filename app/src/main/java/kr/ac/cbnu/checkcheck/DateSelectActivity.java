package kr.ac.cbnu.checkcheck;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import kr.ac.cbnu.checkcheck.ui.IdentificationActivity;

public class DateSelectActivity extends AppCompatActivity{
    private Spinner dateSpinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_select);

        spinner_event();
    }

    private void spinner_event() {
        Global.getInstance().setAttend_date(0);

       // final TextView tv = (TextView)findViewById(R.id.spinner_position);
        dateSpinner = (Spinner) findViewById(R.id.date_spinner);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.dateType, R.layout.spinner_target);
        dateSpinner.setAdapter(spinnerAdapter);
        dateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Global.getInstance().setAttend_date(position);
               // tv.setText("position : " + Global.getInstance().getAttend_date() + "class" + Global.getInstance().getClassnumber() + "stringposition" + Global.getInstance().getAttend_date_string());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void attendCheck(View view) {
        if(Global.getInstance().getAttend_date() == 0){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage("날짜를 선택해주세요")
                    .setNegativeButton("확인", null)
                    .create()
                    .show();
        }
        else {
            Intent intent = new Intent(this, IdentificationActivity.class);
            startActivity(intent);
        }
    }
}