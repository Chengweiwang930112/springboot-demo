package com.check24.coding.domain.service;

import com.check24.coding.domain.model.UserDO;

public interface UserService {
    Long createUser(UserDO user);
    void deleteUser(UserDO user);
}
