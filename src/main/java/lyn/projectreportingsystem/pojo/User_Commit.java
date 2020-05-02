package lyn.projectreportingsystem.pojo;

import java.util.Date;

public class User_Commit {
    private String email;
    private int projectid;
    private Date changetime;
    private String hash;

    public User_Commit(String email, int projectid, Date changetime, String hash) {
        this.email = email;
        this.projectid = projectid;
        this.changetime = changetime;
        this.hash = hash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getProjectid() {
        return projectid;
    }

    public void setProjectid(int projectid) {
        this.projectid = projectid;
    }

    public Date getChangetime() {
        return changetime;
    }

    public void setChangetime(Date changetime) {
        this.changetime = changetime;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
