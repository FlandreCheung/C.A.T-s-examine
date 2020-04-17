package com.lpl.event.dao;


import com.lpl.event.entity.Admin;




public interface AdminDao {

       /** 管理员登录功能
        * @param username 管理员用户名
        * @param password 管理员密码
        */

       Admin login(String username,String password);
}

