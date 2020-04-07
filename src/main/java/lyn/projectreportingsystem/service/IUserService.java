package lyn.projectreportingsystem.service;

import lyn.projectreportingsystem.pojo.User;

public interface IUserService {

    User SelectUser(String email, String password);
}
