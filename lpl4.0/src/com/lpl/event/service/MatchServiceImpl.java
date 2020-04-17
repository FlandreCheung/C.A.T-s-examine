package com.lpl.event.service;

import com.lpl.event.dao.MatchDao;
import com.lpl.event.dao.MatchDaoImpl;
import com.lpl.event.entity.Match;

import java.util.List;

public class MatchServiceImpl implements MatchService {
    MatchDao matchDao = new MatchDaoImpl();

    @Override
    public int addTicket(Match match) {
        return matchDao.addTicket(match);
    }

    @Override
    public List<Match> getMatchList(Match match) {
        return matchDao.getMatchList(match);
    }

    @Override
    public int updateTicket(Match match) {
        return matchDao.updateTicket(match);
    }

    @Override
    public List<Match> getBookedMatchList(Match match,int userId) {
        return matchDao.getBookedMatchList(match,userId);
    }
}
