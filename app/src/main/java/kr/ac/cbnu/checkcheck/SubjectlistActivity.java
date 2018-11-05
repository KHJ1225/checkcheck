package kr.ac.cbnu.checkcheck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class SubjectlistActivity extends AppCompatActivity {

    JSONArray subjects = null;
    ArrayList<HashMap<String, String>> subjectsList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjectlist);

        listView = (ListView)findViewById(R.id.listview);
        subjectsList = new ArrayList<HashMap<String, String>>();

        TextView greeting = (TextView)findViewById(R.id.textView_greeting);
        greeting.setText(Global.getInstance().getProfmajor() + " " + Global.getInstance().getProfname() + " 교수님, 안녕하세요!");

        DB();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String classnum_string = subjectsList.get(position).get("classnumber");
                int classnumber = Integer.parseInt(classnum_string);

                Global.getInstance().setClassnumber(classnumber);

                Intent intent = new Intent(SubjectlistActivity.this, SelectActivity.class);
                startActivity(intent);
            }
        });
    }

    public void selectSubject(View view) {
       // Global.getInstance().setClassnumber();
        Intent intent = new Intent(this, SelectActivity.class);
        startActivity(intent);
    }

    private void DB(){
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    subjects = jsonResponse.getJSONArray("response");
                    for (int i = 0; i < subjects.length(); i++) {
                        JSONObject c = subjects.getJSONObject(i);
                        String success = c.getString("success");

                        //서버에서 보내준 값이 true이면?
                        if (success.equals("true")) {
                            int classnumber = c.getInt("classnumber");
                            String classname = c.getString("classname");
                            String classroom = c.getString("classroom");
                            String classtime = c.getString("time");

                            HashMap<String, String> subject = new HashMap<String, String>();

                            subject.put("classnumber", Integer.toString(classnumber));
                            subject.put("classname", classname);
                            subject.put("classroom", classroom);
                            subject.put("classtime", classtime);

                            subjectsList.add(subject);


                        } else {//로그인 실패시
                            /*AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);

                            builder.setMessage("로그인에 실패했습니다.\n아이디와 비밀번호를 확인해주세요")
                                    .setNegativeButton("확인", null)
                                    .create()
                                    .show();*/
                        }
                    }
                    ListAdapter adapter = new SimpleAdapter(
                            SubjectlistActivity.this, subjectsList, R.layout.subjectlistview_item,
                            new String[]{"classname", "classroom", "classtime"},
                            new int[]{R.id.textView_subjectlist_item1,R.id.textView_subjectlist_item2, R.id.textView_subjectlist_item3}
                    );

                    listView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };


        SubjectlistRequest subjectlistRequest = new SubjectlistRequest(Integer.toString(Global.getInstance().getProfnumber()), responseListener);
        RequestQueue queue = Volley.newRequestQueue(SubjectlistActivity.this);
        queue.add(subjectlistRequest);
    }

}