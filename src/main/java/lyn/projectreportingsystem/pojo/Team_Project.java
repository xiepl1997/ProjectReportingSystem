package lyn.projectreportingsystem.pojo;

public class Team_Project {
    private int teamid;
    private int projectid;
    private String mainperson;

    public Team_Project(int teamid, int projectid, String mainperson) {
        this.teamid = teamid;
        this.projectid = projectid;
        this.mainperson = mainperson;
    }

    public int getTeamid() {
        return teamid;
    }

    public void setTeamid(int teamid) {
        this.teamid = teamid;
    }

    public int getProjectid() {
        return projectid;
    }

    public void setProjectid(int projectid) {
        this.projectid = projectid;
    }

    public String getMainperson() {
        return mainperson;
    }

    public void setMainperson(String mainperson) {
        this.mainperson = mainperson;
    }
}
