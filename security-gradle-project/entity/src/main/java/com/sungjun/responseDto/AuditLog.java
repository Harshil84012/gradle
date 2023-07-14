package com.sungjun.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class AuditLog {

    private String sessionId;
    private String traceId;
    private String spanId;
    private Object request;
    private Object response;
    private String apiName;

    public AuditLog(String sessionId, String traceId, String spanId, Object request, Object response, String apiName) {
        this.sessionId = sessionId;
        this.traceId = traceId;
        this.spanId = spanId;
        this.request = request;
        this.response = response;
        this.apiName = apiName;
    }

    public AuditLog() {

    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getSpanId() {
        return spanId;
    }

    public void setSpanId(String spanId) {
        this.spanId = spanId;
    }

    public Object getRequest() {
        return request;
    }

    public void setRequest(Object request) {
        this.request = request;
    }

    public Object getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return "{" +
                "sessionId='" + sessionId + '\'' +
                ", traceId='" + traceId + '\'' +
                ", spanId='" + spanId + '\'' +
                ", request=" + request +
                ", response=" + response +
                ", apiName='" + apiName + '\'' +
                '}';
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }
}
