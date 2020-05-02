package lyn.projectreportingsystem.pojo;

import java.util.Date;

public class Team {
    private int teamid;
    private String teamremark;
    private Date establishtime;

    public Team(int teamid, String teamremark, Date establishtime) {
        this.teamid = teamid;
        this.teamremark = teamremark;
        this.establishtime = establishtime;
    }

    public int getTeamid() {
        return teamid;
    }

    public void setTeamid(int teamid) {
        this.teamid = teamid;
    }

    public String getTeamremark() {
        return teamremark;
    }

    public void setTeamremark(String teamremark) {
        this.teamremark = teamremark;
    }

    public Date getEstablishtime() {
        return establishtime;
    }

    public void setEstablishtime(Date establishtime) {
        this.establishtime = establishtime;
    }
}
