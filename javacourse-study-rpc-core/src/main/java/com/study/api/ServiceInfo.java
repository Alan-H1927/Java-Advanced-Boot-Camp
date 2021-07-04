package com.study.api;

/**
 * ZK注册信息
 *
 * @author me-ht
 * @date 2021-07-02
 */
public class ServiceInfo {
    /**
     * id
     */
    private String id;

    /**
     * 服务信息
     */
    private String url;

    /**
     * 分组
     */
    private String group;

    /**
     * 版本
     */
    private String version;

    /**
     * 权重
     */
    private Integer weight;

    public ServiceInfo() {
    }

    public ServiceInfo(String id, String url, String group, String version, Integer weight) {
        this.id = id;
        this.url = url;
        this.group = group;
        this.version = version;
        this.weight = weight;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "ServiceInfo{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", group='" + group + '\'' +
                ", weight=" + weight +
                '}';
    }
}
