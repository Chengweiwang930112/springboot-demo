package com.demo.coding.domain.service;

import com.demo.coding.domain.model.UserDO;

public interface UserService {
    Long createUser(UserDO user);
    void deleteUser(UserDO user);
}
