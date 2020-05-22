package lyn.projectreportingsystem.pojo;


public class Project {
    private int projectid;
    private String projectname;
    private String starttime;
    private String endtime;
    private float money;
    private String type;
    private String tertiarydiscipline;
    private String projectremark;
    private String settime;
    private String file;

    public Project(int projectid, String projectname, String starttime, String endtime, float money, String type,String tertiarydiscipline, String projectremark, String settime, String file) {
        this.projectid = projectid;
        this.projectname = projectname;
        this.starttime = starttime;
        this.endtime = endtime;
        this.money = money;
        this.type = type;
        this.tertiarydiscipline = tertiarydiscipline;
        this.projectremark = projectremark;
        this.settime = settime;
        this.file = file;
    }

    public Project(String projectname, String type, String projectremark){
        this.projectname = projectname;
        this.type = type;
        this.projectremark = projectremark;
        this.money = 0;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
