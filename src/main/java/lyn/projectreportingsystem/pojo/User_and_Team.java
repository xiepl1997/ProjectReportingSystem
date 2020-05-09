package lyn.projectreportingsystem.pojo;

public class User_and_Team {
    private String email;
    private String name;
    private String password;
    private String phone;
    private String sex;
    private String school;
    private String college;
    private int teamid;
    private String teamname;
    private String teamremark;
    private String establishtime;
    private String leader;
    private int islead;

    public User_and_Team(String email, String name, String password, String phone, String sex, String school, String college, int teamid, String teamname, String teamremark, String establishtime, String leader, int islead) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.sex = sex;
        this.school = school;
        this.college = college;
        this.teamid = teamid;
        this.teamname = teamname;
        this.teamremark = teamremark;
        this.establishtime = establishtime;
        this.leader = leader;
        this.islead = islead;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
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

    public int getIslead() {
        return islead;
    }

    public void setIslead(int islead) {
        this.islead = islead;
    }
}
