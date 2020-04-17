package com.lpl.event.controller;

import com.lpl.event.entity.Admin;
import com.lpl.event.service.AdminService;
import com.lpl.event.service.AdminServiceImpl;

/**
 *
 */
public class AdminController {

    private static AdminService adminService = new AdminServiceImpl();

    public Admin login(Admin admin){
        String userName = admin.getAdminUserName();
        String password = admin.getAdminPassword();

        Admin result = adminService.login(userName, password);

        return result;
    }
}
