package com.lpl.event.dao;

import com.lpl.event.dao.BaseDao;
import com.lpl.event.entity.Match;
import com.lpl.event.util.StringUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//实现赛事信息与数据库的交互
public interface MatchDao {

    int addTicket(Match match);

    List<Match> getMatchList(Match match);

    boolean deleteById(int id);

    int updateTicket(Match match);

    List<Match> getBookedMatchList(Match match,int userId);

    boolean cancelMatchById(int matchId);
}
