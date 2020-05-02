package lyn.projectreportingsystem.pojo;

import java.util.Date;

public class Project {
    private int projectid;
    private String projectname;
    private Date starttime;
    private Date endtime;
    private float money;
    private String tertiarydiscipline;
    private String projectremark;
    private Date settime;
    private String file;

    public Project(int projectid, String projectname, Date starttime, Date endtime, float money, String tertiarydiscipline, String projectremark, Date settime, String file) {
        this.projectid = projectid;
        this.projectname = projectname;
        this.starttime = starttime;
        this.endtime = endtime;
        this.money = money;
        this.tertiarydiscipline = tertiarydiscipline;
        this.projectremark = projectremark;
        this.settime = settime;
        this.file = file;
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

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
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

    public Date getSettime() {
        return settime;
    }

    public void setSettime(Date settime) {
        this.settime = settime;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
