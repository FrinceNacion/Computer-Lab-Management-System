package com.example.comlabsys;

import java.io.IOException;

public class equipmentObject {
    private String borrowerName, dateBorrowed, dateReturn, borrowedStatus;
    private String equipmentID, equipmentName, equipmentDescription, equipmentStatus;

    public equipmentObject(String equipmentID, String equipmentName, String equipmentDescription,
            String equipmentStatus)
            throws IOException {
        this.equipmentID = equipmentID;
        this.equipmentName = equipmentName;
        this.equipmentDescription = equipmentDescription;
        this.equipmentStatus = equipmentStatus;
        this.borrowedStatus = "N/A";
        this.borrowerName = "N/A";
        this.dateBorrowed = "N/A";
        this.dateReturn = "N/A";
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public String getEquipmentID() {
        return equipmentID;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public String getEquipmentDescription() {
        return equipmentDescription;
    }

    public void setEquipmentDescription(String equipmentDescription) {
        this.equipmentDescription = equipmentDescription;
    }

    public String getEquipmentStatus() {
        return equipmentStatus;
    }

    public void setEquipmentStatus(String equipmentStatus) {
        this.equipmentStatus = equipmentStatus;
    }

    public String getDateBorrowed() {
        return dateBorrowed;
    }

    public void setDateBorrowed(String dateBorrowed) {
        this.dateBorrowed = dateBorrowed;
    }

    public String getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(String dateReturn) {
        this.dateReturn = dateReturn;
    }

    public String getBorrowedStatus() {
        return borrowedStatus;
    }

    public void setBorrowedStatus(String status) {
        this.borrowedStatus = status;
    }
}
