package com.amplexor.itdashboard.model.rest;

import javax.xml.ws.Service;
import java.util.ArrayList;
import java.util.List;

public class SubsidiaryNetworkInfo {

    private String name;
    private List<ServiceNetworkInfo> services;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ServiceNetworkInfo> getServices() {
       if(services == null)
           services = new ArrayList<ServiceNetworkInfo>();
       return services;
    }

    public void setServices(List<ServiceNetworkInfo> services) {
        this.services = services;
    }
}
