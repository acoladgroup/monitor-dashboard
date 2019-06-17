package com.amplexor.itdashboard.model;

import java.util.ArrayList;
import java.util.List;

public class NetworkInfoResponse {

    private List<NetworkInfo> networkInfoList;

    public List<NetworkInfo> getNetworkInfoList() {
        if(networkInfoList == null)
            networkInfoList = new ArrayList<NetworkInfo>();
        return networkInfoList;
    }

    public void setNetworkInfoList(List<NetworkInfo> networkInfoList) {
        this.networkInfoList = networkInfoList;
    }
}
