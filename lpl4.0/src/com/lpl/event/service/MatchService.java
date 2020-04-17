package com.lpl.event.service;

import com.lpl.event.entity.Match;

import java.util.List;

public interface MatchService {

    int addTicket(Match match);

    List<Match> getMatchList(Match match);

    int updateTicket(Match match);

    List<Match> getBookedMatchList(Match match,int userId);
}
