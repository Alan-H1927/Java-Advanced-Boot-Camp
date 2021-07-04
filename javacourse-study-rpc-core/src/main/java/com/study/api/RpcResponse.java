package com.study.api;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-07-03
 */
public class RpcResponse {
    /**
     * 响应结果
     */
    private Object result;

    /**
     * 函数是否执行成功
     */
    private Boolean status;

    /**
     * 执行失败的异常信息
     */
    private Exception exception;

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    @Override
    public String toString() {
        return "RpcResponse{" +
                "result=" + result +
                ", status=" + status +
                ", exception=" + exception +
                '}';
    }
}
