package com.lpl.event.dao;

import com.lpl.event.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//创建一个数据库连接对象，在整个项目中都使用这个对象进行数据库的连接，增删改查都由这个对象完成
public interface BaseDao {


    Connection con = new DBUtil().getCon();

    void closeDao();

    int executeIUD(String sql, Object[] params);

    void closeAll(ResultSet rs, PreparedStatement ps, Connection con);
}
