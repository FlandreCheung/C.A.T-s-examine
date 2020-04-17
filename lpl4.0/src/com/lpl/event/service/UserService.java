package com.lpl.event.service;

import com.lpl.event.entity.LPLUser;

public interface UserService {
    LPLUser login(LPLUser user);

    int register(LPLUser user);

    int update(LPLUser user);

    int updateBalance(LPLUser user);

    LPLUser searchBalance(LPLUser user);


}
