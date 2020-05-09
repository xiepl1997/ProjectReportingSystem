package lyn.projectreportingsystem.pojo;


public class Team_and_Project {
    private int teamid;
    private String teamname;
    private String teamremark;
    private String establishtime;
    private String leader;
    private int projectid;
    private String projectname;
    private String starttime;
    private String endtime;
    private float money;
    private String tertiarydiscipline;
    private String projectremark;
    private String settime;
    private String filepath;

    public Team_and_Project(int teamid, String teamname, String teamremark, String establishtime, String leader, int projectid, String projectname, String starttime, String endtime, float money, String tertiarydiscipline, String projectremark, String settime, String filepath) {
        this.teamid = teamid;
        this.teamname = teamname;
        this.teamremark = teamremark;
        this.establishtime = establishtime;
        this.leader = leader;
        this.projectid = projectid;
        this.projectname = projectname;
        this.starttime = starttime;
        this.endtime = endtime;
        this.money = money;
        this.tertiarydiscipline = tertiarydiscipline;
        this.projectremark = projectremark;
        this.settime = settime;
        this.filepath = filepath;
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

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public int getProjectid() {
        return projectid;
    }

    public void setProjectid(int projectid) {
        this.projectid = projectid;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getTertiarydiscipline() {
        return tertiarydiscipline;
    }

    public void setTertiarydiscipline(String tertiarydiscipline) {
        this.tertiarydiscipline = tertiarydiscipline;
    }

    public String getProjectremark() {
        return projectremark;
    }

    public void setProjectremark(String projectremark) {
        this.projectremark = projectremark;
    }

    public String getSettime() {
        return settime;
    }

    public void setSettime(String settime) {
        this.settime = settime;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
}
