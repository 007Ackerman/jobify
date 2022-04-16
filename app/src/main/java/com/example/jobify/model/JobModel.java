package com.example.jobify.model;

public class JobModel {
    String jobTitle,company,location,jobtype,date,uid,url;
    long timestamp;

    public JobModel() {
    }

    public JobModel(String jobTitle, String company, String location, String jobtype, String date, String uid, String url, long timestamp) {
        this.jobTitle = jobTitle;
        this.company = company;
        this.location = location;
        this.jobtype = jobtype;
        this.date = date;
        this.uid = uid;
        this.url = url;
        this.timestamp = timestamp;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getJobtype() {
        return jobtype;
    }

    public void setJobtype(String jobtype) {
        this.jobtype = jobtype;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
