package com.lpl.event.service;

import com.lpl.event.dao.UserDao;
import com.lpl.event.dao.UserDaoImpl;
import com.lpl.event.entity.LPLUser;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();

    @Override
    public LPLUser login(LPLUser user) {

        user = userDao.login(user);
        if(user != null){
            return user;
        }

        return null;
    }

    @Override
    public int register(LPLUser user) {
        if(userDao.searchUserName(user.getUserName())){
            return userDao.register(user);
        }
        return 0;
    }

    @Override
    public int update(LPLUser user) {
        if(userDao.searchUserName(user.getUserName())){
            return userDao.update(user);
        }

        return 0;
    }

    @Override
    public int updateBalance(LPLUser user) {
        return userDao.updateBalance(user);
    }

    @Override
    public LPLUser searchBalance(LPLUser user) {
        return user;
    }
}
