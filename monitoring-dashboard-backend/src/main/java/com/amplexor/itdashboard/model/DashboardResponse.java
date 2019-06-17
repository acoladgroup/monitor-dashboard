package com.amplexor.itdashboard.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DashboardResponse {

    private final String STATUS_UP = "Target_Up";
    private final String STATUS_DOWN = "Target_Down";
    private final String STATUS_UNREACHABLE = "Pending_Unknown";

    private long applicationServicesUp;
    private long applicationServicesDown;
    private long applicationServicesPending;
    private long sharedServicesUp;
    private long sharedServicesDown;
    private long sharedServicesPending;

    List<ApplicationServices> applicationServicesList;
    List<SharedServices> sharedServicesList;

    List<NetworkServices> networkServicesList;


    public List<NetworkServices> getNetworkServicesList() {
        if(networkServicesList == null)
            networkServicesList = new ArrayList<NetworkServices>();
        return networkServicesList;
    }

    public void setNetworkServicesList(List<NetworkServices> networkServicesList) {
        this.networkServicesList = networkServicesList;
    }



    public List<ApplicationServices> getApplicationServicesList() {
        if(applicationServicesList == null)
            applicationServicesList = new ArrayList<ApplicationServices>();

        applicationServicesList.sort(Comparator.comparing(ApplicationServices::getStatus));
        return applicationServicesList;
    }

    public void setApplicationServicesList(List<ApplicationServices> applicationServicesList) {
        this.applicationServicesList = applicationServicesList;
    }


    public long getApplicationServicesUp() {
        return getApplicationServicesList().stream().filter(x -> x.getStatus().equals(STATUS_UP)).count();
    }

    public void setApplicationServicesUp(long applicationServicesUp) {
        this.applicationServicesUp = applicationServicesUp;
    }

    public long getApplicationServicesDown() {
        return getApplicationServicesList().stream().filter(x -> x.getStatus().equals(STATUS_DOWN)).count();
    }

    public void setApplicationServicesDown(long applicationServicesDown) {
        this.applicationServicesDown = applicationServicesDown;
    }

    public long getApplicationServicesPending() {
        return getApplicationServicesList().stream().filter(x -> x.getStatus().equals(STATUS_UNREACHABLE)).count();
    }

    public void setApplicationServicesPending(long applicationServicesPending) {
        this.applicationServicesPending = applicationServicesPending;
    }

    public long getSharedServicesUp() {
        return getSharedServicesList().stream().filter(x -> x.getStatus().equals(STATUS_UP)).count();
    }

    public void setSharedServicesUp(long sharedServicesUp) {
        this.sharedServicesUp = sharedServicesUp;
    }

    public long getSharedServicesDown() {
        return getSharedServicesList().stream().filter(x -> x.getStatus().equals(STATUS_DOWN)).count();
    }

    public void setSharedServicesDown(long sharedServicesDown) {
        this.sharedServicesDown = sharedServicesDown;
    }

    public long getSharedServicesPending() {
        return getSharedServicesList().stream().filter(x -> x.getStatus().equals(STATUS_UNREACHABLE)).count();
    }

    public void setSharedServicesPending(long sharedServicesPending) {
        this.sharedServicesPending = sharedServicesPending;
    }

    public List<SharedServices> getSharedServicesList() {
        if(sharedServicesList == null)
            sharedServicesList = new ArrayList<SharedServices>();

        sharedServicesList.sort(Comparator.comparing(SharedServices::getStatus));
        return sharedServicesList;
    }

    public void setSharedServicesList(List<SharedServices> sharedServicesList) {
        this.sharedServicesList = sharedServicesList;
    }

}
