package com.example.comlabsys;

import java.io.IOException;

public class computerLabUser {
    private String pcId, pcUserName, section, loggedTime;

    public computerLabUser(String pcId) throws IOException {
        userAccount currentUser = accountFunctions.getCurrentUserByUid(accountFunctions.getUid());
        this.pcId = pcId;
        this.pcUserName = currentUser.getName();
        this.section = currentUser.getSection();
        this.loggedTime = utilityClass.getCurrentTime();
    }

    public String getPcId() {
        return pcId;
    }

    public String getPcUserName() {
        return pcUserName;
    }

    public String getSection() {
        return section;
    }

    public String getLoggedTime() {
        return loggedTime;
    }
}
