package lyn.projectreportingsystem.service;

import lyn.projectreportingsystem.pojo.User;

import java.util.List;

public interface IUserService {

    User SelectUserByEmailPassword(String email, String password);

    User SelectUserByEmail(String email);

    int InsertUser(User user);

    List<User> getMembersInSameTeamByTeamID(int teamid);

    String selectLeader(int teamid);

    int deleteMember(String email, int teamid);

    boolean updateuser(String email,
                       String name,
                       String phone,
                       String sex,
                       String school,
                       String college);
}
