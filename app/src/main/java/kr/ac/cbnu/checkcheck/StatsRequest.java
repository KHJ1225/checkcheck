package kr.ac.cbnu.checkcheck;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class StatsRequest extends StringRequest {

    private static final String LOGIN_REQUEST_URL = "http://18.234.170.219/stats.php";
    private Map<String, String> params;

    public StatsRequest(String classnumber, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<String, String>();
        params.put("classnumber", classnumber);
    }

    public Map<String, String> getParams() {
        return params;
    }

}