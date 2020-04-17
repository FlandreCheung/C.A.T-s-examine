package com.lpl.event.service;

import com.lpl.event.dao.AdminDao;
import com.lpl.event.dao.AdminDaoImpl;
import com.lpl.event.entity.Admin;

public class AdminServiceImpl implements AdminService{

    private static AdminDao adminDao = new AdminDaoImpl();
        @Override
        public Admin login(String username, String password) {
            Admin admin = adminDao.login(username,password);

            if(admin != null){
                return admin;
            }

            return null;

        }


}
