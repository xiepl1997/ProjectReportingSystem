package lyn.projectreportingsystem.service;

import lyn.projectreportingsystem.pojo.Project;

import java.util.List;

public interface IProjectService {

    int getProjectCountByTeamid(int teamid);

    List<Project> getProjectByTeamidandEmail(int teamid, String email);

    List<Project> getProjectByEmail(String email);

    List<Project> getProjectBytype(String type, String email);

    boolean insertproject(Project project);

    Project getprojectbyprojectid(int projectid);

    boolean updatesubmitproject(int projectid,
                                String projectname,
                                String starttime,
                                String endtime,
                                float money,
                                String type,
                                String tertiarydiscipline,
                                String projectremark);

}
