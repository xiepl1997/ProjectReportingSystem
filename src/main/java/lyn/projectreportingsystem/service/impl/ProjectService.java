package lyn.projectreportingsystem.service.impl;

import lyn.projectreportingsystem.mapper.ProjectMapper;
import lyn.projectreportingsystem.pojo.Project;
import lyn.projectreportingsystem.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService implements IProjectService {

    @Autowired
    private ProjectMapper projectMapper = null;

    @Override
    public int getProjectCountByTeamid(int teamid) {
        return projectMapper.getProjectCountByTeamid(teamid);
    }

    @Override
    public List<Project> getProjectByTeamidandEmail(int teamid, String email) {
        return projectMapper.getProjectByTeamidandEmail(teamid, email);
    }

    @Override
    public List<Project> getProjectByEmail(String email) {
        return projectMapper.getProjectByEmail(email);
    }

    @Override
    public List<Project> getProjectBytype(String type, String email) {
        return projectMapper.getProjectBytype(type, email);
    }

}
