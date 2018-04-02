package lamaatech.com.volleyapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView qMag;
    TextView qPlace;
    TextView qTime;
    private RequestQueue mQueue;
    ArrayList<Data> earthQuakes;
    EarthQuakeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qMag = (TextView) findViewById(R.id.mag);
        qPlace = (TextView) findViewById(R.id.place);
        qTime = (TextView) findViewById(R.id.time);

        mQueue = Volley.newRequestQueue(this);


        earthQuakes = new ArrayList<Data>();

        adapter = new EarthQuakeAdapter(this, earthQuakes);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

    }


    public void parseJson(View view) {

        String url = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/4.5_week.geojson";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            showToast("Response: " + response.toString());

                            int x = 0;
                            JSONArray jsonArray = response.getJSONArray("features");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject feature = jsonArray.getJSONObject(i);

                                if (feature.has("properties")) {

                                    JSONObject objectDetails2 = feature.getJSONObject("properties");

                                    Double quakeMag = objectDetails2.getDouble("mag");
                                    qMag.append(String.valueOf(quakeMag) + "\n\n");

                                    String quakePlace = objectDetails2.getString("place");
                                    qPlace.append(quakePlace + "\n\n");

                                    int quakeTime = objectDetails2.getInt("time");
                                    qTime.append(String.valueOf(quakeTime) + "\n\n");

                                    earthQuakes.add(new Data(quakeMag, quakePlace, quakeTime));
                                }
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showToast("Response: " + error.toString());
            }
        });

        mQueue.add(request);



    }

    void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}