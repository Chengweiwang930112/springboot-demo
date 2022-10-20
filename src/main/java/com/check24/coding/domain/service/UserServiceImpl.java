package com.check24.coding.domain.service;

import com.check24.coding.domain.model.UserDO;
import com.check24.coding.infrastructure.model.UserDAO;
import com.check24.coding.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    private UserRepository userRepo;

    @Override
    public Long createUser(UserDO user) {
        UserDAO userDAO = new UserDAO(user.getName(), user.getEmail());
        return userRepo.save(userDAO).getUserId();
    }

    @Override
    public void deleteUser(UserDO user) {

    }
}
