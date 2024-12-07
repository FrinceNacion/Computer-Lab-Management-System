package com.example.comlabsys;

public class userAccount extends user {
    private String course;
    private String Id;
    private String section;

    public userAccount(String name, String course, String Id, String section, String username, String password,
            String accountType) {
        super(name, username, password, accountType);
        this.course = course;
        this.Id = Id;
        this.section = section;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

}
