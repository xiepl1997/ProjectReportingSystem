package lyn.projectreportingsystem.pojo;

public class User_Team {
    private String email;
    private int teamid;
    private int islead;

    public User_Team(String email, int teamid, int islead) {
        this.email = email;
        this.teamid = teamid;
        this.islead = islead;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTeamid() {
        return teamid;
    }

    public void setTeamid(int teamid) {
        this.teamid = teamid;
    }

    public int getIslead() {
        return islead;
    }

    public void setIslead(int islead) {
        this.islead = islead;
    }
}
