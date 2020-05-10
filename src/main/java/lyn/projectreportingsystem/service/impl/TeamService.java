package lyn.projectreportingsystem.service.impl;

import lyn.projectreportingsystem.mapper.TeamMapper;
import lyn.projectreportingsystem.pojo.Team;
import lyn.projectreportingsystem.pojo.User;
import lyn.projectreportingsystem.service.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService implements ITeamService {

    @Autowired
    private TeamMapper teamMapper = null;

    @Override
    public List<Team> getTeamsByUserEmail(String email) {
        return teamMapper.getTeamsByUserEmail(email);
    }

    @Override
    public Team getTeamByTeamid(int teamid) {
        return teamMapper.getTeamByTeamid(teamid);
    }

    @Override
    public int getCountOfmembersByTeamid(int teamid) {
        return teamMapper.getCountOfmembersByTeamid(teamid);
    }

    @Override
    public boolean insert_user_team(String email, String teamid, int islead) {
        return teamMapper.insert_user_team(email, teamid, islead);
    }

    @Override
    public void insertteam(Team team) {
        teamMapper.insertteam(team);
    }
}
