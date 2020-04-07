package lyn.projectreportingsystem.service.impl;

import lyn.projectreportingsystem.mapper.UserMapper;
import lyn.projectreportingsystem.pojo.User;
import lyn.projectreportingsystem.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User SelectUser(String email, String password) {
        return userMapper.SelectUser(email, password);
    }
}
