package com.xiaoi.shMonitor.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class SimReq {
    @org.springframework.data.annotation.Id
    private String id;
    private String title;
    private String path;
    private String method;
    private String[] conditions;
    private String[] results;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String[] getConditions() {
        return conditions;
    }

    public void setConditions(String[] conditions) {
        this.conditions = conditions;
    }

    public String[] getResults() {
        return results;
    }

    public void setResults(String[] results) {
        this.results = results;
    }
}
