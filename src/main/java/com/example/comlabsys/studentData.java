package com.example.comlabsys;

public class studentData {
    private String name;
    private String course;
    private String year;
    private String section;
    private String username;
    private String password;

    public studentData(String name, String course, String year, String section, String username, String password) {
        this.name = name;
        this.course = course;
        this.year = year;
        this.section = section;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public String getYear() {
        return year;
    }

    public String getSection() {
        return section;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
