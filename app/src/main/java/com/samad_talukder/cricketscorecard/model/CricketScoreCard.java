package com.samad_talukder.cricketscorecard.model;

public class CricketScoreCard {
    private String daynight,gmt_offset,group,league,live,livecoverage,match_Id,matchfile,matchnumber,matchresult,matchstatus,matchdate_gmt,
            matchdate_ist,matchdate_local,matchtime_gmt,matchtime_ist,matchtime_local,end_matchdate_gmt,end_matchdate_ist,end_matchdate_local,
            end_matchtime_gmt,end_matchtime_ist,end_matchtime_local,matchtype,priority,recent,series_Id,seriesname,series_start_date,series_end_date,
            stage,teama,teama_short,teama_Id,teamb,teamb_short,teamb_Id,tour_Id,tourname,upcoming,venue,venue_Id,winningmargin,winningteam_Id;

    public CricketScoreCard(String match_Id, String matchfile, String matchstatus, String seriesname, String teama, String teama_Id,
                            String teamb, String teamb_Id, String venue, String live, String upcoming, String recent, String matchdate_gmt,
                            String matchtime_gmt, String matchresult,String teama_short, String teamb_short) {
        this.match_Id = match_Id;
        this.matchfile = matchfile;
        this.matchstatus = matchstatus;
        this.seriesname = seriesname;
        this.teama = teama;
        this.teama_Id = teama_Id;
        this.teamb = teamb;
        this.teamb_Id = teamb_Id;
        this.venue = venue;
        this.live = live;
        this.upcoming = upcoming;
        this.recent = recent;
        this.matchdate_gmt = matchdate_gmt;
        this.matchtime_gmt = matchtime_gmt;
        this.matchresult = matchresult;
        this.teama_short = teama_short;
        this.teamb_short = teamb_short;
    }

    public CricketScoreCard(String daynight, String gmt_offset, String group, String league, String live, String livecoverage, String match_Id, String matchfile, String matchnumber, String matchresult, String matchstatus, String matchdate_gmt, String matchdate_ist, String matchdate_local, String matchtime_gmt, String matchtime_ist, String matchtime_local, String end_matchdate_gmt, String end_matchdate_ist, String end_matchdate_local, String end_matchtime_gmt, String end_matchtime_ist, String end_matchtime_local, String matchtype, String priority, String recent, String series_Id, String seriesname, String series_start_date, String series_end_date, String stage, String teama, String teama_short, String teama_Id, String teamb, String teamb_short, String teamb_Id, String tour_Id, String tourname, String upcoming, String venue, String venue_Id, String winningmargin, String winningteam_Id) {
        this.daynight = daynight;
        this.gmt_offset = gmt_offset;
        this.group = group;
        this.league = league;
        this.live = live;
        this.livecoverage = livecoverage;
        this.match_Id = match_Id;
        this.matchfile = matchfile;
        this.matchnumber = matchnumber;
        this.matchresult = matchresult;
        this.matchstatus = matchstatus;
        this.matchdate_gmt = matchdate_gmt;
        this.matchdate_ist = matchdate_ist;
        this.matchdate_local = matchdate_local;
        this.matchtime_gmt = matchtime_gmt;
        this.matchtime_ist = matchtime_ist;
        this.matchtime_local = matchtime_local;
        this.end_matchdate_gmt = end_matchdate_gmt;
        this.end_matchdate_ist = end_matchdate_ist;
        this.end_matchdate_local = end_matchdate_local;
        this.end_matchtime_gmt = end_matchtime_gmt;
        this.end_matchtime_ist = end_matchtime_ist;
        this.end_matchtime_local = end_matchtime_local;
        this.matchtype = matchtype;
        this.priority = priority;
        this.recent = recent;
        this.series_Id = series_Id;
        this.seriesname = seriesname;
        this.series_start_date = series_start_date;
        this.series_end_date = series_end_date;
        this.stage = stage;
        this.teama = teama;
        this.teama_short = teama_short;
        this.teama_Id = teama_Id;
        this.teamb = teamb;
        this.teamb_short = teamb_short;
        this.teamb_Id = teamb_Id;
        this.tour_Id = tour_Id;
        this.tourname = tourname;
        this.upcoming = upcoming;
        this.venue = venue;
        this.venue_Id = venue_Id;
        this.winningmargin = winningmargin;
        this.winningteam_Id = winningteam_Id;
    }



    public String getDaynight() {
        return daynight;
    }

    public String getGmt_offset() {
        return gmt_offset;
    }

    public String getGroup() {
        return group;
    }

    public String getLeague() {
        return league;
    }

    public String getLive() {
        return live;
    }

    public String getLivecoverage() {
        return livecoverage;
    }

    public String getMatch_Id() {
        return match_Id;
    }

    public String getMatchfile() {
        return matchfile;
    }

    public String getMatchnumber() {
        return matchnumber;
    }

    public String getMatchresult() {
        return matchresult;
    }

    public String getMatchstatus() {
        return matchstatus;
    }

    public String getMatchdate_gmt() {
        return matchdate_gmt;
    }

    public String getMatchdate_ist() {
        return matchdate_ist;
    }

    public String getMatchdate_local() {
        return matchdate_local;
    }

    public String getMatchtime_gmt() {
        return matchtime_gmt;
    }

    public String getMatchtime_ist() {
        return matchtime_ist;
    }

    public String getMatchtime_local() {
        return matchtime_local;
    }

    public String getEnd_matchdate_gmt() {
        return end_matchdate_gmt;
    }

    public String getEnd_matchdate_ist() {
        return end_matchdate_ist;
    }

    public String getEnd_matchdate_local() {
        return end_matchdate_local;
    }

    public String getEnd_matchtime_gmt() {
        return end_matchtime_gmt;
    }

    public String getEnd_matchtime_ist() {
        return end_matchtime_ist;
    }

    public String getEnd_matchtime_local() {
        return end_matchtime_local;
    }

    public String getMatchtype() {
        return matchtype;
    }

    public String getPriority() {
        return priority;
    }

    public String getRecent() {
        return recent;
    }

    public String getSeries_Id() {
        return series_Id;
    }

    public String getSeriesname() {
        return seriesname;
    }

    public String getSeries_start_date() {
        return series_start_date;
    }

    public String getSeries_end_date() {
        return series_end_date;
    }

    public String getStage() {
        return stage;
    }

    public String getTeama() {
        return teama;
    }

    public String getTeama_short() {
        return teama_short;
    }

    public String getTeama_Id() {
        return teama_Id;
    }

    public String getTeamb() {
        return teamb;
    }

    public String getTeamb_short() {
        return teamb_short;
    }

    public String getTeamb_Id() {
        return teamb_Id;
    }

    public String getTour_Id() {
        return tour_Id;
    }

    public String getTourname() {
        return tourname;
    }

    public String getUpcoming() {
        return upcoming;
    }

    public String getVenue() {
        return venue;
    }

    public String getVenue_Id() {
        return venue_Id;
    }

    public String getWinningmargin() {
        return winningmargin;
    }

    public String getWinningteam_Id() {
        return winningteam_Id;
    }
}
