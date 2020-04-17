package com.lpl.event.dao;

import com.lpl.event.entity.LPLUser;
import com.lpl.event.entity.Match;
import com.lpl.event.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    /** 召唤师玩家登录功能
     * @param user
     * @return
     */
    //召唤师玩家登陆功能
    public LPLUser login(LPLUser user){
        String sql = "SELECT * FROM lpl_user WHERE username=? and password=?";

        LPLUser userRst = null;
        try{
            //把SQL语句传给数据库操作对象
            PreparedStatement ps = con.prepareStatement(sql);
            //设置占位符参数
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getUserPassword());
            //执行SQL语句
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                userRst = new LPLUser();
                userRst.setUserID(rs.getInt("id"));
                userRst.setUserName(rs.getString("username"));
                userRst.setUserPassword(rs.getString("password"));
                userRst.setUserBalance(rs.getInt("balance"));
                userRst.setUserStatus(rs.getInt("status"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return userRst;
    }


    /** 用户注册
     * @param user
     * @return
     */
    //添加用户信息的方法
    public int register(LPLUser user){

        String sql = "insert into lpl_user(username,password) values (?,?)";

        Object[] params = {
                user.getUserName(),
                user.getUserPassword()
        };
        return executeIUD(sql,params);
    }


    /** 判断用户名是否存在，在注册用户和修改用户的时候防止用户名重复
     * @param userName
     * @return
     */
    @Override
    //判断用户名是否已存在的方法，这个方法在注册用户和修改用户时都会用到，以避免出现用户名重复的现象
    public boolean searchUserName(String userName) {

        String sql = "select * from lpl_user where username=?";

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            con = new DBUtil().getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1,userName);
            rs = ps.executeQuery();
            if (rs.next()){
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, ps, con);
        }

        return true;
    }
    /** 修改用户信息
     * @param user
     * @return
     */
    @Override
    public int update(LPLUser user) {
        String sql = "update lpl_user set username=?, password=? where id=?";

        Object[] params = {
                user.getUserName(),
                user.getUserPassword(),
                user.getUserID()
        };


    return executeIUD(sql,params);
    }
    /** 充值余额
     * @param user
     * @return
     */
    @Override
    public int updateBalance(LPLUser user) {
        String sql = "update lpl_user set balance=? where id=?";

        Object[] params = {
                user.getUserBalance(),
                user.getUserID()
        };

        return executeIUD(sql,params);


    }
    /** 用户注册
     * @param userId
     * @param matchId
     * @return
     */
    @Override
    public boolean bookTicket(int userId, int matchId) {
        String sql = "insert into user_match(user_id,match_id) values(?,?)";

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,userId);
            ps.setInt(2,matchId);

            if(ps.executeUpdate()>0){
                return true;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    return false;
    }
}
