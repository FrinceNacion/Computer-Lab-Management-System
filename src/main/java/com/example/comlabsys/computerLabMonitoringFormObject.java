package com.example.comlabsys;

import java.io.IOException;

public class computerLabMonitoringFormObject {
    private String roomName, postedBy, date, time, description, status;
    private computerLabUser[] computerLabUserList = {};

    public computerLabMonitoringFormObject(String roomName, String description, String status) throws IOException {
        userAccount currentUser = accountFunctions.getCurrentUserByUid(accountFunctions.getUid());
        this.roomName = roomName;
        this.postedBy = currentUser.getName();
        this.date = utilityClass.getCurrentDate();
        this.time = utilityClass.getCurrentTime();
        this.description = description;
        this.status = status;
    }

    // for faculty page

    public String getRoomName() {
        return roomName;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addToUserList(computerLabUser[] computerLabUser) {
        this.computerLabUserList = computerLabUser;
    }

    public computerLabUser[] getUserList() {
        return computerLabUserList;
    }
}
