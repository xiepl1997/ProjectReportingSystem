package lyn.projectreportingsystem.service.impl;

import lyn.projectreportingsystem.mapper.UserMapper;
import lyn.projectreportingsystem.pojo.User;
import lyn.projectreportingsystem.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User SelectUserByEmailPassword(String email, String password) {
        return userMapper.SelectUserByEmailPassword(email, password);
    }

    @Override
    public User SelectUserByEmail(String email) {
        return userMapper.SelectUserByEmail(email);
    }

    @Override
    public int InsertUser(User user) {
        return userMapper.InsertUser(user);
    }

    @Override
    public List<User> getMembersInSameTeamByTeamID(int teamid) {
        return userMapper.getMembersInSameTeamByTeamID(teamid);
    }

    @Override
    public String selectLeader(int teamid) {
        return userMapper.selectLeader(teamid);
    }

    @Override
    public int deleteMember(String email, int teamid) {
        return userMapper.deleteMember(email, teamid);
    }

    @Override
    public boolean updateuser(String email, String name, String phone, String sex, String school, String college) {
        return userMapper.updateuser(email, name,phone,sex,school,college);
    }
}
