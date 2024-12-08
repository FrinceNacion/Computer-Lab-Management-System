package com.example.comlabsys;

public class feedbackObject {

    private String submittedBy;
    private String dateSubmitted;
    private String feedbackHeader;
    private String feedbackBody;

    public feedbackObject(String submittedBy, String dateSubmitted, String feedbackHeader,
            String feedbackBody) {
        this.submittedBy = submittedBy;
        this.dateSubmitted = dateSubmitted;
        this.feedbackHeader = feedbackHeader;
        this.feedbackBody = feedbackBody;
    }

    public String getSubmittedBy() {
        return submittedBy;
    }

    public String getDateSubmitted() {
        return dateSubmitted;
    }

    public String getFeedbackHeader() {
        return feedbackHeader;
    }

    public String getFeedbackBody() {
        return feedbackBody;
    }
}
