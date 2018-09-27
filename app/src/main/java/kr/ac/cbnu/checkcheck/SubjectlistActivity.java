package kr.ac.cbnu.checkcheck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class SubjectlistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjectlist);

        ListView listView=(ListView)findViewById(R.id.listview);

        ArrayList<Listviewitem> data=new ArrayList<>();
        Listviewitem c1=new Listviewitem(R.drawable.icon_subjectlist,"운영체제");
        Listviewitem c2=new Listviewitem(R.drawable.icon_subjectlist,"캡스톤디자인1");
        Listviewitem c3=new Listviewitem(R.drawable.icon_subjectlist,"설계포트폴리오7");
        Listviewitem c4=new Listviewitem(R.drawable.icon_subjectlist,"첨단기기실험1");

        data.add(c1);
        data.add(c2);
        data.add(c3);
        data.add(c4);

        ListviewAdapter adapter=new ListviewAdapter(this,R.layout.listviewitem,data);
        listView.setAdapter(adapter);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, SelectActivity.class);
        startActivity(intent);
    }

}