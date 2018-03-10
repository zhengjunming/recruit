package com.qg.recruit.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author 小铭
 * Date: 2018/3/9
 * No struggle, talent how to match the willfulness.
 * Description: Ip黑名单实体类
 */
@Entity
@Table(name = "ip_black_list")
public class IpBlackList {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    /**
     * ip
     */
    @Column(name = "ip", nullable = false)
    private String ip;

    /**
     * 何时被封禁
     */
    @Column(name = "ip_time", nullable = false)
    private Date ipTime;

    public IpBlackList() {
    }

    public IpBlackList(String ip, Date ipTime) {
        this.ip = ip;
        this.ipTime = ipTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getIpTime() {
        return ipTime;
    }

    public void setIpTime(Date ipTime) {
        this.ipTime = ipTime;
    }

    @Override
    public String toString() {
        return "IpBlackList{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", ipTime=" + ipTime +
                '}';
    }
}
