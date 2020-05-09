package lyn.projectreportingsystem.service.impl;

import lyn.projectreportingsystem.mapper.ProjectMapper;
import lyn.projectreportingsystem.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService implements IProjectService {

    @Autowired
    private ProjectMapper projectMapper = null;

    @Override
    public int getProjectCountByTeamid(int teamid) {
        return projectMapper.getProjectCountByTeamid(teamid);
    }
}
