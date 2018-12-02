package com.samad_talukder.cricketscorecard;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.samad_talukder.cricketscorecard.adapter.CricketScoreCardAdapter;
import com.samad_talukder.cricketscorecard.api.ConfigApi;
import com.samad_talukder.cricketscorecard.model.CricketScoreCard;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ScorecardDetailsActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private static int int_items = 3;
    public String matchID;
    private TextView scorecardTeamATV, scorecardTeamBTV, scorecardTeamARunTV, scorecardTeamBRunTV, scorecardTeamAOverTV, scorecardTeamBOverTV,
            scorecardMatchResultTV, scorecardVenueTV, playerOfTheMatchTV;
    private ImageView scorecardTeamAIV, scorecardTeamBIV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorecard_details);
        //
        matchID = getIntent().getStringExtra("matchID");
        initViews();

        loadScoreCardData();
    }

    private void initViews() {
        scorecardTeamATV = findViewById(R.id.scorecardTeamATV);
        playerOfTheMatchTV = findViewById(R.id.playerOfTheMatchTV);
        scorecardTeamATV.setText(getIntent().getStringExtra("teamAName"));
        scorecardTeamBTV = findViewById(R.id.scorecardTeamBTV);
        scorecardTeamBTV.setText(getIntent().getStringExtra("teamBName"));
        scorecardTeamARunTV = findViewById(R.id.scorecardTeamARunTV);
        scorecardTeamBRunTV = findViewById(R.id.scorecardTeamBRunTV);
        scorecardTeamAOverTV = findViewById(R.id.scorecardTeamAOverTV);
        scorecardTeamBOverTV = findViewById(R.id.scorecardTeamBOverTV);
        scorecardMatchResultTV = findViewById(R.id.scorecardMatchResultTV);
        scorecardVenueTV = findViewById(R.id.scorecardVenueTV);
        scorecardVenueTV.setText(getIntent().getStringExtra("venue"));
        //scorecardMatchResultTV.setText(getIntent().getStringExtra("matchResult"));
        scorecardTeamAIV = findViewById(R.id.scorecardTeamAIV);
        Glide.with(getApplicationContext()).
                load(ConfigApi.IMAGE_API_URL + getIntent().getStringExtra("teamAFlag") + ConfigApi.IMAGE_API_URL_END)
                .apply(new RequestOptions().placeholder(R.drawable.md_background))
                .into(scorecardTeamAIV);
        scorecardTeamBIV = findViewById(R.id.scorecardTeamBIV);
        Glide.with(getApplicationContext()).
                load(ConfigApi.IMAGE_API_URL + getIntent().getStringExtra("teamBFlag") + ConfigApi.IMAGE_API_URL_END)
                .apply(new RequestOptions().placeholder(R.drawable.md_background))
                .into(scorecardTeamBIV);
        tabLayout = findViewById(R.id.cricketLiveDetailsTabs);
        viewPager = findViewById(R.id.cricketLiveDetailsViewpager);
        viewPager.setAdapter(new MatchDetailsTabAdapter(getSupportFragmentManager(), ScorecardDetailsActivity.this));
        viewPager.setSaveFromParentEnabled(false);

        tabLayout.post(() -> tabLayout.setupWithViewPager(viewPager));
    }

    private void loadScoreCardData() {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        JSONObject jsObject = new JSONObject();

        @SuppressLint("SetTextI18n") JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.GET, ConfigApi.SCORECARD_DETAILS_API + matchID + ConfigApi.SCORECARD_DETAILS_API_END, jsObject,
                response -> {
                    Log.e("ScoreCardData: ", ConfigApi.SCORECARD_DETAILS_API + matchID + ConfigApi.SCORECARD_DETAILS_API_END);
                    Log.e("ScoreCardData: ", response.toString());
                    try {
                        JSONObject scoreCardObject = new JSONObject(String.valueOf(response));
                        JSONObject matchdetailObject = scoreCardObject.getJSONObject("Matchdetail");
                        scorecardMatchResultTV.setText(matchdetailObject.getString("Result"));
                        playerOfTheMatchTV.setText("Man Of The Match: " + matchdetailObject.getString("Player_Match"));
                        JSONArray inningsArray = scoreCardObject.getJSONArray("Innings");
                        for (int i = 0; i < inningsArray.length(); i++) {
                            JSONObject inningsObject = inningsArray.getJSONObject(i);
                            if (inningsObject.getString("Number").equals("First")) {
                                scorecardTeamARunTV.setText(inningsObject.getString("Total") + "/" + inningsObject.getString("Wickets"));
                                scorecardTeamAOverTV.setText(inningsObject.getString("Overs"));
                            } else if (inningsObject.getString("Number").equals("Second")) {
                                scorecardTeamBRunTV.setText(inningsObject.getString("Total") + "/" + inningsObject.getString("Wickets"));
                                scorecardTeamBOverTV.setText(inningsObject.getString("Overs"));
                            }

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error ->
                VolleyLog.e("Error: ", error.getMessage()));
        jsonObjRequest.setShouldCache(false);
        queue.add(jsonObjRequest);
    }

    static class MatchDetailsTabAdapter extends FragmentPagerAdapter {
        Context context;

        public MatchDetailsTabAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new ScorecardFragment();
                case 1:
                    return new TeamXIFragment();
                case 2:
                    return new MatchInfoFragment();
                /*case 3:
                    return new MatchFactFragment();*/
            }
            return null;

        }

        @Override
        public int getCount() {
            return int_items;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Score Card";
                case 1:
                    return "Team XI";
                case 2:
                    return "Match Center";
               /* case 3:
                    return "Match Fact";*/
            }
            return null;
        }


    }
}
