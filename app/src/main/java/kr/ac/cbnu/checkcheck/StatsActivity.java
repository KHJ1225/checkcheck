package kr.ac.cbnu.checkcheck;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class StatsActivity extends AppCompatActivity {

    private List<String> list;
    private ListView listView;
    private ListView listViewTitle;
    private EditText editSearch;
    private ArrayList<HashMap<String, String>> searchList;
    private SearchAdapter adapter_search;
    private ArrayList<String> arraylist;
    private Spinner sortSpinner;
    private JSONArray attendances = null;
    private ArrayList<HashMap<String, String>> attendancesList;
    private ArrayList<HashMap<String, String>> attendancesTitleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        spinner_event();

        editSearch = (EditText) findViewById(R.id.editSearch);
        searchList = new ArrayList<HashMap<String, String>>();

        listView = (ListView) findViewById(R.id.listView);
        listViewTitle = (ListView) findViewById(R.id.listViewTitle);

        attendancesList = new ArrayList<HashMap<String, String>>();
        attendancesTitleList = new ArrayList<HashMap<String, String>>();
        settingListTitle();
        settingList();

   /*     list = new ArrayList<String>();



        arraylist = new ArrayList<String>();
        arraylist.addAll(list);

        adapter_search = new SearchAdapter(list, this);

        listView.setAdapter(adapter_search);
*/
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

        searchList.clear();

        if (charText.length() == 0) {
            set_listView(0);
        }
        else
        {
            for(int i = 0;i < attendancesList.size(); i++)
            {
                if (attendancesList.get(i).get("studentname").toLowerCase().contains(charText))
                {
                    searchList.add(attendancesList.get(i));
                }
            }
            set_listView(1);
        }
    }

    private void settingList(){

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    attendances = jsonResponse.getJSONArray("response");

                    for (int i = 0; i < attendances.length(); i++) {
                        JSONObject c = attendances.getJSONObject(i);
                        String success = c.getString("success");
                        //Toast.makeText(getApplicationContext(), c.getString("2nd"), Toast.LENGTH_LONG).show();

                        //서버에서 보내준 값이 true이면?
                        if (success.equals("true")) {
                            String studentname = c.getString("studname");
                            String studentnumber = c.getString("studentnumber");
                            String studentmajor = c.getString("major");
                            String res_1st = (c.getString("1st").equals("null")?".":c.getString("1st"));
                            String res_2nd = (c.getString("2nd").equals("null")?".":c.getString("2nd"));
                            String res_3rd = (c.getString("3rd").equals("null")?".":c.getString("3rd"));
                            String res_4st = (c.getString("4st").equals("null")?".":c.getString("4st"));
                            String res_5st = (c.getString("5st").equals("null")?".":c.getString("5st"));
                            String res_6st = (c.getString("6st").equals("null")?".":c.getString("6st"));
                            String res_7st = (c.getString("7st").equals("null")?".":c.getString("7st"));
                            String res_8st = (c.getString("8st").equals("null")?".":c.getString("8st"));
                            String res_9st = (c.getString("9st").equals("null")?".":c.getString("9st"));
                            String res_10st = (c.getString("10st").equals("null")?".":c.getString("10st"));
                            String res_11st = (c.getString("11st").equals("null")?".":c.getString("11st"));
                            String res_12st = (c.getString("12st").equals("null")?".":c.getString("12st"));
                            String res_13st = (c.getString("13st").equals("null")?".":c.getString("13st"));
                            String res_14st = (c.getString("14st").equals("null")?".":c.getString("14st"));
                            String res_15st = (c.getString("15st").equals("null")?".":c.getString("15st"));
                            String res_16st = (c.getString("16st").equals("null")?".":c.getString("16st"));

                            HashMap<String, String> attendance = new HashMap<String, String>();

                            attendance.put("studentname", studentname);
                            attendance.put("studentnumber", studentnumber);
                            attendance.put("studentmajor", studentmajor);
                            attendance.put("1st", res_1st);
                            attendance.put("2nd", res_2nd);
                            attendance.put("3rd", res_3rd);
                            attendance.put("4st", res_4st);
                            attendance.put("5st", res_5st);
                            attendance.put("6st", res_6st);
                            attendance.put("7st", res_7st);
                            attendance.put("8st", res_8st);
                            attendance.put("9st", res_9st);
                            attendance.put("10st", res_10st);
                            attendance.put("11st", res_11st);
                            attendance.put("12st", res_12st);
                            attendance.put("13st", res_13st);
                            attendance.put("14st", res_14st);
                            attendance.put("15st", res_15st);
                            attendance.put("16st", res_16st);

                            int cnt_O = 0, cnt_X = 0;
                            for(String key : attendance.keySet()){
                                if(attendance.get(key).equals("O")){ cnt_O++; }
                                else if(attendance.get(key).equals("X")) { cnt_X++; }
                            }
                            attendance.put("cnt_O", Integer.toString(cnt_O));
                            attendance.put("cnt_X", Integer.toString(cnt_X));

                            attendancesList.add(attendance);
                        } else {//로그인 실패시
                            /*    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);

                                builder.setMessage("로그인에 실패했습니다.\n아이디와 비밀번호를 확인해주세요")
                                        .setNegativeButton("확인", null)
                                        .create()
                                        .show();*/
                        }
                    }

                    set_listView(0);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        };

        String id = Integer.toString(Global.getInstance().getClassnumber());
        StatsRequest statsRequest = new StatsRequest(id, responseListener);
        RequestQueue queue = Volley.newRequestQueue(StatsActivity.this);
        queue.add(statsRequest);
    }

    private void settingListTitle(){
        HashMap<String, String> title = new HashMap<String, String>();

        title.put("studentname", "이름");
        title.put("studentnumber", "학번");
        title.put("studentmajor", "전공");
        title.put("cnt_O", "출");
        title.put("cnt_X", "결");
        title.put("1st", "1주차");
        title.put("2nd", "2주차");
        title.put("3rd", "3주차");
        title.put("4st", "4주차");
        title.put("5st", "5주차");
        title.put("6st", "6주차");
        title.put("7st", "7주차");
        title.put("8st", "8주차");
        title.put("9st", "9주차");
        title.put("10st", "10주차");
        title.put("11st", "11주차");
        title.put("12st", "12주차");
        title.put("13st", "13주차");
        title.put("14st", "14주차");
        title.put("15st", "15주차");
        title.put("16st", "16주차");

        attendancesTitleList.add(title);

        ListAdapter adapter = new SimpleAdapter(
                StatsActivity.this, attendancesTitleList, R.layout.statslistview_item,
                new String[]{"studentname", "studentnumber", "studentmajor", "cnt_O","cnt_X","1st", "2nd", "3rd", "4st", "5st", "6st", "7st", "8st", "9st", "10st", "11st", "12st", "13st", "14st", "15st", "16st"},
                new int[]{R.id.textview_studentname, R.id.textview_studentnumber, R.id.textview_studentmajor, R.id.textview_cnt_O, R.id.textView_cnt_X, R.id.textView_1st, R.id.textView_2nd, R.id.textView_3rd, R.id.textView_4st, R.id.textView_5st,
                        R.id.textView_6st, R.id.textView_7st, R.id.textView_8st, R.id.textView_9st, R.id.textView_10st,
                        R.id.textView_11st, R.id.textView_12st, R.id.textView_13st, R.id.textView_14st, R.id.textView_15st, R.id.textView_16st}
        );

        listViewTitle.setAdapter(adapter);
    }

    private void spinner_event() {
        Global.getInstance().setSortType(0);

        //final TextView tv = (TextView)findViewById(R.id.spinner_position);
        sortSpinner = (Spinner) findViewById(R.id.sort_spinner);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.sortType, R.layout.spinner_target);
        sortSpinner.setAdapter(spinnerAdapter);
        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Global.getInstance().setSortType(position);
                //tv.setText("position : " + Global.getInstance().getSortType());
                if(editSearch.getText().toString().length() == 0){
                    set_listView(0);
                }else { set_listView(1); }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void set_listView(int setType){
        ArrayList<HashMap<String, String>> tempList = new ArrayList<HashMap<String, String>>();
        if(setType == 0){
            tempList.addAll(attendancesList);
        }else if(setType == 1){
            tempList.addAll(searchList);
        }

        int spinner_position = Global.getInstance().getSortType();
        if(Global.getInstance().getSortType() == 0){
            MapComparator comp = new MapComparator("studentname", 1);
            Collections.sort(tempList, comp);
        }else if(spinner_position == 1){
            MapComparator comp = new MapComparator("studentname", 1);
            Collections.sort(tempList, comp);
        }
        else if(spinner_position == 2){
            MapComparator comp = new MapComparator("studentname", -1);
            Collections.sort(tempList, comp);
        }
        else if(spinner_position == 3){
            MapComparator comp = new MapComparator("studentnumber", 1);
            Collections.sort(tempList, comp);
        }else if(spinner_position == 4){
            MapComparator comp = new MapComparator("studentnumber", -1);
            Collections.sort(tempList, comp);
        }

        ListAdapter adapter = new SimpleAdapter(
                StatsActivity.this, tempList, R.layout.statslistview_item,
                new String[]{"studentname", "studentnumber", "studentmajor", "cnt_O","cnt_X","1st", "2nd", "3rd", "4st", "5st", "6st", "7st", "8st", "9st", "10st", "11st", "12st", "13st", "14st", "15st", "16st"},
                new int[]{R.id.textview_studentname, R.id.textview_studentnumber, R.id.textview_studentmajor, R.id.textview_cnt_O, R.id.textView_cnt_X, R.id.textView_1st, R.id.textView_2nd, R.id.textView_3rd, R.id.textView_4st, R.id.textView_5st,
                        R.id.textView_6st, R.id.textView_7st, R.id.textView_8st, R.id.textView_9st, R.id.textView_10st,
                        R.id.textView_11st, R.id.textView_12st, R.id.textView_13st, R.id.textView_14st, R.id.textView_15st, R.id.textView_16st}
        );

        listView.setAdapter(adapter);
    }

    class MapComparator implements Comparator<HashMap<String, String>> {

        private final String key;
        private final int orderType;

        public MapComparator(String key, int orderType) {
            this.key = key;
            this.orderType = orderType;
        }

        @Override
        public int compare(HashMap<String, String> first, HashMap<String, String> second) {
            int result = first.get(key).compareTo(second.get(key));
            if(orderType == 1)  return result;
            else return result*-1;
        }
    }


}