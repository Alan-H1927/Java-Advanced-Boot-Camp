package com.study.javacourse.week3.bean;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-05-23
 */
public class UrlBean {
    private String ip;

    private int port;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "UrlBean{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                '}';
    }
}
