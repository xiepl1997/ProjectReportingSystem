package lyn.projectreportingsystem.pojo;


public class Team {
    private int teamid;
    private String teamname;
    private String teamremark;
    private String establishtime;
    private String leader;

    public Team(int teamid, String teamname, String teamremark, String establishtime, String leader) {
        this.teamid = teamid;
        this.teamname = teamname;
        this.teamremark = teamremark;
        this.establishtime = establishtime;
        this.leader = leader;
    }
    public Team(String teamname, String teamremark, String establishtime, String leader) {
        this.teamname = teamname;
        this.teamremark = teamremark;
        this.establishtime = establishtime;
        this.leader = leader;
    }

    public String getLeader(){
        return leader;
    }

    public void setLeader(String leader){
        this.leader = leader;
    }

    public int getTeamid() {
        return teamid;
    }

    public void setTeamid(int teamid) {
        this.teamid = teamid;
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public String getTeamremark() {
        return teamremark;
    }

    public void setTeamremark(String teamremark) {
        this.teamremark = teamremark;
    }

    public String getEstablishtime() {
        return establishtime;
    }

    public void setEstablishtime(String establishtime) {
        this.establishtime = establishtime;
    }
}
