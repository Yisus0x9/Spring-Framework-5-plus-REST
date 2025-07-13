package org.yisus.spring.aop.example.models;

import java.util.Arrays;
import java.util.Date;

public class AuditLog {
    private String action;
    private Object[] params;
    private Date timestamp;
    private Object result;

    public AuditLog(){}

    public AuditLog( String type, String action, Object[] params, Date timestamp) {
        this.action = action;
        this.params = params;
        this.timestamp = timestamp;
    }



    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Object getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String toString() {
        return "AuditLog{" +
                "action='" + action + '\'' +
                ", params=" + Arrays.toString(params) +
                ", timestamp='" + timestamp + '\'' +
                ", result=" + result +
                '}';
    }
}
