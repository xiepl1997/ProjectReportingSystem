package lyn.projectreportingsystem.service;

import lyn.projectreportingsystem.pojo.User;

public interface IUserService {

    User SelectUserByEmailPassword(String email, String password);

    User SelectUserByEmail(String email);

    int InsertUser(User user);
}
