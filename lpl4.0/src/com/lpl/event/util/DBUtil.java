package com.lpl.event.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//提供连接数据库的工具类
public class DBUtil {
    private String dbUrl="jdbc:mysql://localhost:3306/lpl?useUnicode=true&characterEncoding=utf8";//数据库链接地址
    private String dbUserName="root";
    private String dbPassword="1234";
    private String jdbcName="com.mysql.jdbc.Driver";


    /** 获取数据库连接的对象
     * @return con
     */
//获取数据库连接的对象
    public Connection getCon(){
        try{
            Class.forName(jdbcName);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    Connection con = null;
        try{
            con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        }catch(SQLException e){
            e.printStackTrace();
        }
    return con;
    }


    /** 关闭数据库连接的方法
     * @param con
     * @throws Exception
     */
//关闭数据库连接的方法
    public void closeCon(Connection con)throws Exception{
        if(con !=null){
            con.close();
        }
    }

    public static void main(String[] args){

        try{
            new DBUtil().getCon();
            System.out.println("数据库连接成功！");
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("数据库连接异常！请检查");
        }
    }
}
