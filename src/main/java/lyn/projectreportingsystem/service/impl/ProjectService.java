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

    @Override
    public boolean insertproject(Project project) {
        return projectMapper.insertproject(project);
    }

    @Override
    public Project getprojectbyprojectid(int projectid) {
        return projectMapper.getprojectbyprojectid(projectid);
    }

    @Override
    public boolean updatesubmitproject(int projectid, String projectname, String starttime, String endtime, float money, String type, String tertiarydiscipline, String projectremark) {
        return projectMapper.updatesubmitproject(projectid,projectname,starttime,endtime,money,type,tertiarydiscipline,projectremark);
    }

    @Override
    public boolean updatefile(int projectid, String filepath) {
        return projectMapper.updatefile(projectid, filepath);
    }

    @Override
    public int getHorizontalproject(int teamid, String type) {
        return projectMapper.getHorizontalproject(teamid, type);
    }

}
