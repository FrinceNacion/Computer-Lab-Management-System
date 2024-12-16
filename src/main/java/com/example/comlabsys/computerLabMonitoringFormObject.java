package com.example.comlabsys;

import java.io.IOException;

public class computerLabMonitoringFormObject {
    private String roomName, postedBy, date, time, description, status;
    private computerLabUser[] computerLabUserList;

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

    public void addToUserList(computerLabUser[] computerLabUser, computerLabUser newLabUser) {
        computerLabUser[] newArray = new computerLabUser[getUserList().length + 1];

        if (getUserList().length == 0) {
            newArray[0] = newLabUser;
        } else {
            for (int i = 0; i < computerLabUser.length; i++) {
                newArray[i] = computerLabUser[i];
            }
            newArray[getUserList().length + 1] = newLabUser;
        }
        System.out.println("Your submission has been added to the form");
        this.computerLabUserList = newArray;
    }

    public computerLabUser[] getUserList() {
        return computerLabUserList;
    }
}
