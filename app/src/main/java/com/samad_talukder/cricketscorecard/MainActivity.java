package com.samad_talukder.cricketscorecard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.samad_talukder.cricketscorecard.adapter.CricketScoreCardAdapter;
import com.samad_talukder.cricketscorecard.api.ConfigApi;
import com.samad_talukder.cricketscorecard.model.CricketScoreCard;
import com.samad_talukder.cricketscorecard.util.CheckNetworkConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private CheckNetworkConnection checkNetworkConnection;
    private ShimmerFrameLayout shimmerFrameLayout;
    private RecyclerView cricketRV;
    private List<CricketScoreCard> scoreCardList;
    private LinearLayout chechInternetConncetionLayout;
    private Button retryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkNetworkConnection = new CheckNetworkConnection(getApplicationContext());
        chechInternetConncetionLayout = findViewById(R.id.checkInternetConnectionLL);
        shimmerFrameLayout = (ShimmerFrameLayout) findViewById(R.id.shimmerLayout);
        cricketRV = findViewById(R.id.cricketRV);
        retryBtn = findViewById(R.id.retryButton);
        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkNetworkConnection.isInternetAvailable()){
                    Toast.makeText(MainActivity.this, "Connect", Toast.LENGTH_SHORT).show();
                    chechInternetConncetionLayout.setVisibility(View.GONE);
                    shimmerFrameLayout.setVisibility(View.VISIBLE);
                    cricketRV.setVisibility(View.VISIBLE);
                    shimmerFrameLayout.startShimmerAnimation();
                    //
                    loadCricketData();
                }
            }
        });

        if (checkNetworkConnection.isInternetAvailable()){
            chechInternetConncetionLayout.setVisibility(View.GONE);
            shimmerFrameLayout.setVisibility(View.VISIBLE);
            cricketRV.setVisibility(View.VISIBLE);
            shimmerFrameLayout.startShimmerAnimation();
            //
            loadCricketData();
        }else {
            chechInternetConncetionLayout.setVisibility(View.VISIBLE);
            shimmerFrameLayout.setVisibility(View.GONE);
            cricketRV.setVisibility(View.GONE);
        }

    }

    public void loadCricketData() {
        scoreCardList = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        JSONObject jsObject = new JSONObject();
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.GET, ConfigApi.API_URL_CALENDER, jsObject,
                response -> {
                    //Log.e("CricketURL: ", ConfigApi.API_URL_CALENDER);
                    Log.e("CricketData: ", response.toString());
                    try {
                        JSONObject cricketDataJsonObject = new JSONObject(String.valueOf(response));
                        JSONObject data = cricketDataJsonObject.getJSONObject("data");
                        JSONArray cricketMatchesArray = data.getJSONArray("matches");
                        for (int i = 0; i < cricketMatchesArray.length(); i++) {
                            JSONObject cricketMatchObject = cricketMatchesArray.getJSONObject(i);
                            scoreCardList.add(new CricketScoreCard(
                                    cricketMatchObject.getString("match_Id"),
                                    cricketMatchObject.getString("matchfile"),
                                    cricketMatchObject.getString("matchstatus"),
                                    cricketMatchObject.getString("seriesname"),
                                    cricketMatchObject.getString("teama"),
                                    cricketMatchObject.getString("teama_Id"),
                                    cricketMatchObject.getString("teamb"),
                                    cricketMatchObject.getString("teamb_Id"),
                                    cricketMatchObject.getString("venue"),
                                    cricketMatchObject.getString("live"),
                                    cricketMatchObject.getString("upcoming"),
                                    cricketMatchObject.getString("recent"),
                                    cricketMatchObject.getString("matchdate_gmt"),
                                    cricketMatchObject.getString("matchtime_gmt"),
                                    cricketMatchObject.getString("matchresult"),
                                    cricketMatchObject.getString("teama_short"),
                                    cricketMatchObject.getString("teamb_short")
                            ));
                        }
                        shimmerFrameLayout.stopShimmerAnimation();
                        shimmerFrameLayout.setVisibility(View.GONE);
                        cricketRV.setLayoutManager(new LinearLayoutManager(this));
                        cricketRV.setAdapter(new CricketScoreCardAdapter(this, scoreCardList));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error ->
                VolleyLog.e("Error: ", error.getMessage()));
        jsonObjRequest.setShouldCache(false);
        queue.add(jsonObjRequest);
    }
    @Override
    public void onResume() {
        super.onResume();
        //shimmerFrameLayout.startShimmerAnimation();
    }

    @Override
    public void onPause() {
        //shimmerFrameLayout.stopShimmerAnimation();
        super.onPause();
    }

}
