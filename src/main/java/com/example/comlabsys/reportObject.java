package com.example.comlabsys;

public class reportObject {
    private String reportHeader, reportBody, dateSubmitted, submittedBy;

    public reportObject(String reportHeader, String reportBody, String dateSubmitted, String submittedBy) {
        this.reportHeader = reportHeader;
        this.reportBody = reportBody;
        this.dateSubmitted = dateSubmitted;
        this.submittedBy = submittedBy;
    }

    public String getReportHeader() {
        return reportHeader;
    }

    public String getReportBody() {
        return reportBody;
    }

    public String getDateSubmitted() {
        return dateSubmitted;
    }

    public String getSubmittedBy() {
        return submittedBy;
    }

}
