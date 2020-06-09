package lyn.projectreportingsystem.pojo;

import lyn.projectreportingsystem.util.StringUtil;

import java.util.Date;

/**
 * 区块
 */
public class Block {
    private String email;
    private String projectid;
    private long timestamp;
    private String projectname;
    private String starttime;
    private String endtime;
    private float money;
    private String type;
    private String tertiarydiscipline;
    private String projectremark;
    private String file;
    public String previous_hash;
    public String hash;
    private int nonce;

    public Block(String email, String projectid, long timestamp, String projectname, String starttime, String endtime, float money, String type, String tertiarydiscipline, String projectremark, String file, String previous_hash, String hash, int nonce) {
        this.email = email;
        this.projectid = projectid;
        this.timestamp = new Date().getTime();
        this.projectname = projectname;
        this.starttime = starttime;
        this.endtime = endtime;
        this.money = money;
        this.type = type;
        this.tertiarydiscipline = tertiarydiscipline;
        this.projectremark = projectremark;
        this.file = file;
        this.previous_hash = previous_hash;
        this.hash = hash;
        this.nonce = 0;
    }

    public Block(String email, String projectid, String projectname, String starttime, String endtime, float money, String type, String tertiarydiscipline, String projectremark, String file,String previous_hash) {
        this.email = email;
        this.projectid = projectid;
        this.timestamp = new Date().getTime();
        this.projectname = projectname;
        this.starttime = starttime;
        this.endtime = endtime;
        this.money = money;
        this.type = type;
        this.tertiarydiscipline = tertiarydiscipline;
        this.projectremark = projectremark;
        this.file = file;
        this.previous_hash = previous_hash;
        this.nonce = 0;
        this.hash = calculateHash();
    }

    /**
     * 计算hash值
     * @return
     */
    public String calculateHash() {
        String calculatedhash = StringUtil.applySha256(
                previous_hash +
                        Long.toString(timestamp) +
                        email+
                        projectid+
                        projectname+
                        starttime+
                        endtime+
                        money+
                        type+
                        tertiarydiscipline+
                        projectremark+
                        file+
                        String.valueOf(nonce)
        );
        return calculatedhash;
    }

    /**
     * 挖矿，工作量证明
     * @param difficulty
     */
    public void mineBlock(int difficulty) {
        nonce = 0;
        String target = new String(new char[difficulty]).replace('\0', '0');
        while(!hash.substring( 0, difficulty).equals(target)) {
            nonce ++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getEmail() {
        return email;
    }

    public String getProjectid() {
        return projectid;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getProjectname() {
        return projectname;
    }

    public String getStarttime() {
        return starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public float getMoney() {
        return money;
    }

    public String getType() {
        return type;
    }

    public String getTertiarydiscipline() {
        return tertiarydiscipline;
    }

    public String getProjectremark() {
        return projectremark;
    }

    public String getPrevious_hash() {
        return previous_hash;
    }

    public String getHash() {
        return hash;
    }

    public int getNonce() {
        return nonce;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTertiarydiscipline(String tertiarydiscipline) {
        this.tertiarydiscipline = tertiarydiscipline;
    }

    public void setProjectremark(String projectremark) {
        this.projectremark = projectremark;
    }

    public void setPrevious_hash(String previous_hash) {
        this.previous_hash = previous_hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

}
