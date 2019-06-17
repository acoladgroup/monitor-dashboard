package com.amplexor.itdashboard.model.rest;

import java.util.ArrayList;
import java.util.List;

public class CountryNetworkInfo {

    private String country;

    private List<SubsidiaryNetworkInfo> subsidiaries;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<SubsidiaryNetworkInfo> getSubsidiaries() {
        if(subsidiaries == null)
            subsidiaries = new ArrayList<SubsidiaryNetworkInfo>();
        return subsidiaries;
    }

    public void setSubsidiaries(List<SubsidiaryNetworkInfo> subsidiaries) {
        this.subsidiaries = subsidiaries;
    }
}
