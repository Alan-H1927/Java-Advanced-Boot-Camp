package com.study.api;

import java.util.Arrays;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-07-03
 */
public class RpcRequest {
    /**
     * 请求地址
     */
    private String url;
    /**
     * 接口类名称
     */
    private String serviceClass;
    /**
     * 方法名
     */
    private String method;
    /**
     * 参数
     */
    private Object[] argv;

    private String group;

    private String version;

    private Integer weight;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getServiceClass() {
        return serviceClass;
    }

    public void setServiceClass(String serviceClass) {
        this.serviceClass = serviceClass;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object[] getArgv() {
        return argv;
    }

    public void setArgv(Object[] argv) {
        this.argv = argv;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "RpcRequest{" +
                "url='" + url + '\'' +
                ", serviceClass='" + serviceClass + '\'' +
                ", method='" + method + '\'' +
                ", argv=" + Arrays.toString(argv) +
                ", group='" + group + '\'' +
                ", version='" + version + '\'' +
                ", weight=" + weight +
                '}';
    }
}
