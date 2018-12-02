package com.samad_talukder.cricketscorecard.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.samad_talukder.cricketscorecard.R;
import com.samad_talukder.cricketscorecard.ScorecardDetailsActivity;
import com.samad_talukder.cricketscorecard.api.ConfigApi;
import com.samad_talukder.cricketscorecard.model.CricketScoreCard;

import java.util.List;

public class CricketScoreCardAdapter extends RecyclerView.Adapter<CricketScoreCardAdapter.CricketScoreCardHolder> {
    private Context context;
    private List<CricketScoreCard> scoreCardList;
    private int lastPosition;

    public CricketScoreCardAdapter(Context context, List<CricketScoreCard> scoreCardList) {
        this.context = context;
        this.scoreCardList = scoreCardList;
    }

    @NonNull
    @Override
    public CricketScoreCardHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View scoreCardView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.design_cricket_scorecard, viewGroup, false);
        return new CricketScoreCardHolder(scoreCardView);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull CricketScoreCardHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    @SuppressLint({"CheckResult", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull CricketScoreCardHolder scoreHolder, int i) {
        CricketScoreCard cricketScoreCard = scoreCardList.get(i);
        Animation animation = AnimationUtils.loadAnimation(context, (i > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        scoreHolder.itemView.startAnimation(animation);
        lastPosition = i;
        scoreHolder.seriesNameTV.setText(cricketScoreCard.getSeriesname());
        scoreHolder.venueTV.setText("Venue: " + cricketScoreCard.getVenue());
        scoreHolder.matchResultTV.setText(cricketScoreCard.getMatchresult());
        scoreHolder.teamATV.setText(cricketScoreCard.getTeama());
        scoreHolder.teamBTV.setText(cricketScoreCard.getTeamb());
        scoreHolder.matchDateTV.setText(cricketScoreCard.getMatchdate_gmt() + " GMT: " + cricketScoreCard.getMatchtime_gmt());
        Glide.with(context).
                load(ConfigApi.IMAGE_API_URL + cricketScoreCard.getTeama_Id() + ConfigApi.IMAGE_API_URL_END)
                .apply(new RequestOptions().placeholder(R.drawable.bangladesh))
                .into(scoreHolder.teamAIV);
        Glide.with(context).
                load(ConfigApi.IMAGE_API_URL + cricketScoreCard.getTeamb_Id() + ConfigApi.IMAGE_API_URL_END)
                .apply(new RequestOptions().placeholder(R.drawable.india))
                .into(scoreHolder.teamBIV);
        Log.e("Imagepath:", ConfigApi.IMAGE_API_URL + cricketScoreCard.getTeamb_Id() + ConfigApi.IMAGE_API_URL_END);
        if (!cricketScoreCard.getLive().equals("0")) {
            scoreHolder.liveBtn.setVisibility(View.VISIBLE);
            scoreHolder.scoreCardBtn.setVisibility(View.VISIBLE);
            scoreHolder.statusTV.setText("Live");
        } else if (cricketScoreCard.getRecent().equals("yes")) {
            //scoreHolder.statusTV.setText(cricketScoreCard.getMatchresult());
            scoreHolder.scoreCardBtn.setVisibility(View.VISIBLE);
            scoreHolder.liveBtn.setVisibility(View.GONE);
        } else if (!cricketScoreCard.getUpcoming().equals("0")) {
            scoreHolder.statusTV.setText("Upcoming");
            scoreHolder.scoreCardBtn.setVisibility(View.VISIBLE);
            scoreHolder.liveBtn.setVisibility(View.GONE);
        }
        scoreHolder.scoreCardBtn.setOnClickListener(v -> {
            Intent intent = new Intent(context, ScorecardDetailsActivity.class);
            intent.putExtra("teamA", cricketScoreCard.getTeama());
            intent.putExtra("teamAName", cricketScoreCard.getTeama_short());
            intent.putExtra("teamAFlag", cricketScoreCard.getTeama_Id());
            intent.putExtra("teamB", cricketScoreCard.getTeamb());
            intent.putExtra("teamBName", cricketScoreCard.getTeamb_short());
            intent.putExtra("teamBFlag", cricketScoreCard.getTeamb_Id());
            intent.putExtra("venue", cricketScoreCard.getVenue());
            intent.putExtra("matchResult", cricketScoreCard.getMatchresult());
            intent.putExtra("matchID", cricketScoreCard.getMatchfile());
            context.startActivity(intent);
            //Toast.makeText(context, ""+cricketScoreCard.getMatchfile(), Toast.LENGTH_SHORT).show();
        });
        scoreHolder.liveBtn.setOnClickListener(v ->
                Toast.makeText(context, "Live", Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        return scoreCardList.size();
    }

    public class CricketScoreCardHolder extends RecyclerView.ViewHolder {
        private CardView scoreCardCV;
        private ImageView teamAIV, teamBIV;
        private TextView seriesNameTV, venueTV, teamATV, teamBTV, statusTV, matchDateTV, matchResultTV;
        private Button liveBtn, scoreCardBtn;

        public CricketScoreCardHolder(@NonNull View itemView) {
            super(itemView);
            teamAIV = itemView.findViewById(R.id.teamAIV);
            teamBIV = itemView.findViewById(R.id.teamBIV);
            seriesNameTV = itemView.findViewById(R.id.seriesNameTV);
            matchDateTV = itemView.findViewById(R.id.matchDateTV);
            venueTV = itemView.findViewById(R.id.venueTV);
            matchResultTV = itemView.findViewById(R.id.matchResultTV);
            teamATV = itemView.findViewById(R.id.teamATV);
            teamBTV = itemView.findViewById(R.id.teamBTV);
            statusTV = itemView.findViewById(R.id.statusTV);
            liveBtn = itemView.findViewById(R.id.liveBtn);
            scoreCardBtn = itemView.findViewById(R.id.scoreCardBtn);
        }
    }
}
