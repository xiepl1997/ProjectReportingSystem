package lyn.projectreportingsystem.pojo;

public class User {
    private String email;
    private String name;
    private String password;
    private String phone;
    private String sex;
    private String school;
    private String college;

    public User(String email, String name, String password, String phone, String sex, String school, String college) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.sex = sex;
        this.school = school;
        this.college = college;
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
}
