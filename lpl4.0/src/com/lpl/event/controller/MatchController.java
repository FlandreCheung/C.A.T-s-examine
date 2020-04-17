package com.lpl.event.controller;


import com.lpl.event.entity.Match;
import com.lpl.event.service.MatchService;
import com.lpl.event.service.MatchServiceImpl;

import java.util.List;

/**
 *
 */
public class MatchController {
    public static MatchService matchService = new MatchServiceImpl();

    public static int addTicket(Match match){
        int result = matchService.addTicket(match);
        return result;
    }

    public static List<Match> getMatchList(Match match){
        List<Match> result = matchService.getMatchList(match);
        return result;
    }

    public static int updateTicket(Match match){
        int result = matchService.addTicket(match);
        return result;
    }

    public static List<Match> getBookedMatchList(Match match,int userId){
        List<Match> result = matchService.getBookedMatchList(match,userId);
        return result;
    }
}
