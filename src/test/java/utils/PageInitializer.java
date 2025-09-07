package utils;

import pages.DashBoardPage_HRMS;
import pages.LoginPage_HRMS;

public class PageInitializer {

    public static LoginPage_HRMS loginPageHrms;
    public static DashBoardPage_HRMS dashBoardPageHrms;

    public static void initializerPageObjects(){

        loginPageHrms=new LoginPage_HRMS();
        dashBoardPageHrms=new DashBoardPage_HRMS();
    }
}
