package lyn.projectreportingsystem.pojo;


public class User_and_Commit_and_Projece {
    private String email;
    private String name;
    private String password;
    private String phone;
    private String sex;
    private String school;
    private String college;
    private int projectid;
    private String projectname;
    private String starttime;
    private String endtime;
    private float money;
    private String tertiarydiscipline;
    private String projectremark;
    private String settime;
    private String file;
    private String changetime;
    private String hash;

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

    public String getChangetime() {
        return changetime;
    }

    public void setChangetime(String changetime) {
        this.changetime = changetime;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public User_and_Commit_and_Projece(String email, String name, String password, String phone, String sex, String school, String college, int projectid, String projectname, String starttime, String endtime, float money, String tertiarydiscipline, String projectremark, String settime, String file, String changetime, String hash) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.sex = sex;
        this.school = school;
        this.college = college;
        this.projectid = projectid;
        this.projectname = projectname;
        this.starttime = starttime;
        this.endtime = endtime;
        this.money = money;
        this.tertiarydiscipline = tertiarydiscipline;
        this.projectremark = projectremark;
        this.settime = settime;
        this.file = file;
        this.changetime = changetime;
        this.hash = hash;
    }
}
