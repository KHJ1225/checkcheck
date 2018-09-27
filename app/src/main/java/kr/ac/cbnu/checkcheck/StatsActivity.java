package kr.ac.cbnu.checkcheck;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class StatsActivity extends AppCompatActivity {

    private List<String> list;
    private ListView listView;
    private EditText editSearch;
    private SearchAdapter adapter;
    private ArrayList<String> arraylist;
    private Spinner sortSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        spinner_event();

        editSearch = (EditText) findViewById(R.id.editSearch);
        listView = (ListView) findViewById(R.id.listView);

        list = new ArrayList<String>();

        settingList();

        arraylist = new ArrayList<String>();
        arraylist.addAll(list);

        adapter = new SearchAdapter(list, this);

        listView.setAdapter(adapter);

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editSearch.getText().toString();
                search(text);
            }
        });


    }

    public void search(String charText) {

        list.clear();

        if (charText.length() == 0) {
            list.addAll(arraylist);
        }
        else
        {
            for(int i = 0;i < arraylist.size(); i++)
            {
                if (arraylist.get(i).toLowerCase().contains(charText))
                {
                    list.add(arraylist.get(i));
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void settingList(){
        list.add("    이름             학번           출   결      01   02   03");
        list.add("  곽지민   2014040005    12   0       O     O     O");
        list.add("  김지영   2014040001    11   1       X     O     O");
        list.add("  김혜진   2014040004    11   1       X     O     O");
        list.add("  이준혁   2014017025    12   0       O     O     O");
        list.add("  성진옥   2014041234    12   0       O     O     O");
        list.add("  김인아   2014014567    11   1       O     X     O");
        list.add("  조하은   2014017896    12   0       O     O     O");
        list.add("  김용욱   2014011654    10   2       O     O     X");
    }

    private void spinner_event() {
        sortSpinner = (Spinner) findViewById(R.id.sort_spinner);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.sortType, R.layout.spinner_target);
        sortSpinner.setAdapter(spinnerAdapter);
    }

}