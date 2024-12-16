package com.example.comlabsys;

public class softwareObject {
    private String softwareName, softwareVersion, requestStatus, submittedBy;
    private boolean isFreeware, isLicensed;

    public softwareObject(String softwareName, String softwareVersion, boolean isFreeware, boolean isLicensed,
            String requestStatus, String submittedBy) {
        this.softwareName = softwareName;
        this.softwareVersion = softwareVersion;
        this.isFreeware = isFreeware;
        this.isLicensed = isLicensed;
        this.requestStatus = requestStatus;
        this.submittedBy = submittedBy;
    }

    public String getSoftwareName() {
        return softwareName;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public boolean getIsFreeware() {
        return isFreeware;
    }

    public boolean getIsLicensed() {
        return isLicensed;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public String getSubmittedBy() {
        return submittedBy;
    }

    public void setRequestStatus(String status) {
        this.requestStatus = status;
    }

}
