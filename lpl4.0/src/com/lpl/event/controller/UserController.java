package com.lpl.event.controller;

import com.lpl.event.entity.LPLUser;
import com.lpl.event.service.UserService;
import com.lpl.event.service.UserServiceImpl;
import com.lpl.event.view.LoginFrame;

public class UserController extends LoginFrame {
    public static UserService userService = new UserServiceImpl();
    public static LPLUser login(LPLUser user){

        LPLUser result = userService.login(user);

        return result;
    }

    public static int register(LPLUser user){

        int result = userService.register(user);

        return result;
    }

    public static int update(LPLUser user){

        int result = userService.update(user);

        return result;
    }

    public static int updateBalance(LPLUser user){

        int result = userService.updateBalance(user);

        return result;
    }

    public static LPLUser searchBalance(LPLUser user){

        LPLUser result = userService.searchBalance(user);

        return result;
    }
}
