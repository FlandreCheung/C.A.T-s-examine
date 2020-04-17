package com.lpl.event.dao;

import com.lpl.event.dao.BaseDao;
import com.lpl.event.entity.LPLUser;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface UserDao{

    LPLUser login(LPLUser user);

    int register(LPLUser user);

    boolean searchUserName(String userName);

    int update(LPLUser user);

    int updateBalance(LPLUser user);

    boolean bookTicket(int userId, int matchId);
}