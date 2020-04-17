package com.lpl.event.dao;

import com.lpl.event.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDaoImpl implements BaseDao {
    public Connection con = new DBUtil().getCon();

    /**
     * 关闭连接
     */
    @Override
    public void closeDao(){

        try{
            con.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /** 更改操作
     * @param sql
     * @param params
     * @return count
     */
    @Override
    public int executeIUD(String sql, Object[] params){
        int count = 1;
        Connection con = BaseDao.con;
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);

            for(int i=0; i<params.length; i++){
                ps.setObject(i+1, params[i]);
            }
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }


    /** 关闭结果集、准备、连接
     * @param rs
     * @param ps
     * @param con
     */
    //关闭结果集、连接的方法
    @Override
    public void closeAll(ResultSet rs, PreparedStatement ps, Connection con){
        try{
            if(rs!=null){
                rs.close();
            }

            if(ps!=null){
                ps.close();
            }

            if(con!=null){
                con.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
