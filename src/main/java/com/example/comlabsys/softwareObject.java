package com.example.comlabsys;

public class softwareObject {
    private String softwareName, softwareVersion, requestStatus, submittedBy;
    private String isFreeware, isLicensed;

    public softwareObject(String softwareName, String softwareVersion, String submittedBy, String isFreeware,
            String isLicensed) {
        this.softwareName = softwareName;
        this.softwareVersion = softwareVersion;
        this.isFreeware = isFreeware;
        this.isLicensed = isLicensed;
        this.submittedBy = submittedBy;
        this.requestStatus = "N/A";
    }

    public String getSoftwareName() {
        return softwareName;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public String getIsFreeware() {
        return isFreeware;
    }

    public String getIsLicensed() {
        return isLicensed;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String status) {
        this.requestStatus = status;
    }

    public String getSubmittedBy() {
        return submittedBy;
    }

}
