package com.lpl.event.dao;

import com.lpl.event.entity.LPLUser;
import com.lpl.event.entity.Match;
import com.lpl.event.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MatchDaoImpl extends BaseDaoImpl implements MatchDao {


    /** 添加赛事信息
     * @param match 比赛
     * @return
     */
    //添加赛事信息的方法
    public int addTicket(Match match){
        //把SQL语句传给数据库操作对象
        String sql = "insert into lpl_match(team1,team2,price) values(?,?,?)";

        Object[] params = {
                match.getTeam1(),
                match.getTeam2(),
                match.getPrice()

        };
        return executeIUD(sql, params);
    }

    /** 使用ArrayList实现数据库中的表与前端的交互
     * @param match 比赛
     * @return
     */
    @Override
    public List<Match> getMatchList(Match match){
        List<Match> matchList = new ArrayList<>();
        String sql = "select id,team1,team2,price from lpl_match";
        if(!StringUtil.StringIsEmpty(match.getTeam1())||!StringUtil.StringIsEmpty(match.getTeam2())){
            sql += " where team1 like '%"+match.getTeam1()+"%' or team2 like '%"+match.getTeam1()+"%'" ;
        }
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Match m = new Match();
                m.setMatchID(rs.getInt("id"));
                m.setTeam1(rs.getString("team1"));
                m.setTeam2(rs.getString("team2"));
                m.setPrice(rs.getInt("price"));
                matchList.add(m);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return matchList;
    }

    /** 通过赛事id删除赛事
     * @param id 赛事Id
     */
    @Override
    public boolean deleteById(int id) {
        String sql = "DELETE FROM lpl_match where id=?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            if(ps.executeUpdate()>0){
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    return false;
    }

    /** 更改赛事信息
     * @param match 比赛
     */
    @Override
    public int updateTicket(Match match) {
        String sql ="update lpl_match set team1=?, team2=?, price=? where id=?";
        Object[] params = {
                match.getTeam1(),
                match.getTeam2(),
                match.getPrice(),
                match.getMatchID()

        };
        return executeIUD(sql, params);

    }

    /** 通过ArrayList获取已经预定的赛事
     * @param match 比赛
     * @param userId 用户id，通过用户id来获取已预订的赛事
     */
    @Override
    public List<Match> getBookedMatchList(Match match,int userId) {
        List<Match> matchList = new ArrayList<>();
        String sql = "select lpl_match.id,lpl_match.team1,lpl_match.team2,lpl_match.price from lpl_user,lpl_match,user_match where lpl_user.id=user_match.user_id and lpl_match.id=user_match.match_id and lpl_user.id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,userId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Match m = new Match();
                m.setMatchID(rs.getInt("id"));
                m.setTeam1(rs.getString("team1"));
                m.setTeam2(rs.getString("team2"));
                m.setPrice(rs.getInt("price"));
                matchList.add(m);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return matchList;
    }
    /** 退订赛事
     * @param matchId 赛事id
     */
    @Override
    public boolean cancelMatchById(int matchId) {
        String sql = "DELETE FROM user_match where match_id=?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,matchId);
            if(ps.executeUpdate()>0){
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
