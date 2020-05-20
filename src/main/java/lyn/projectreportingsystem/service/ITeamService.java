package lyn.projectreportingsystem.service;

import lyn.projectreportingsystem.pojo.Team;
import lyn.projectreportingsystem.pojo.User;

import java.util.List;

public interface ITeamService {

    List<Team> getTeamsByUserEmail(String email);

    Team getTeamByTeamid(int teamid);

    int getCountOfmembersByTeamid(int teamid);

    boolean insert_user_team(String email, String teamid, int islead);

    void insertteam(Team team);

    List<Team> selectteamofleader(String email);

    int deleteTeam(int teamid);

    int deleteUser_Team(int teamid);
}
