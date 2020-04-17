package com.lpl.event.dao;

import com.lpl.event.entity.Admin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDaoImpl extends BaseDaoImpl implements AdminDao {
    /** 管理员登录功能
     * @param username
     * @param password
     * @return adminRst 管理员结果集
     */

    @Override
    public Admin login(String username, String password){
        String sql = "SELECT * FROM lpl_admin WHERE username=? and password=?";
        Admin adminRst = null;
        try{
            PreparedStatement ps = con.prepareStatement(sql);//把SQL语句传给数据库操作对象
            //设置占位符参数
            ps.setString(1, username);
            ps.setString(2, password);
            //执行SQL语句
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                adminRst = new Admin();
                adminRst.setAdminID(rs.getInt("id"));
                adminRst.setAdminUserName(rs.getString("username"));
                adminRst.setAdminPassword(rs.getString("password"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        try {
            con.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return adminRst;
    }
}
