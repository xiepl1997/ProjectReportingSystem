package lyn.projectreportingsystem.service;

import lyn.projectreportingsystem.pojo.Team;

import java.util.List;

public interface ITeamService {

    List<Team> getTeamsByUserEmail(String email);

    Team getTeamByTeamid(int teamid);

    int getCountOfmembersByTeamid(int teamid);
}
