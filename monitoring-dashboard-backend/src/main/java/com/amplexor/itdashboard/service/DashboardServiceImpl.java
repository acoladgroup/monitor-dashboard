package com.amplexor.itdashboard.service;

import com.amplexor.itdashboard.dao.BackupStatusDao;
import com.amplexor.itdashboard.dao.ApplicationServicesDao;
import com.amplexor.itdashboard.dao.SharedServicesDao;
import com.amplexor.itdashboard.dao.TableSpaceDao;
import com.amplexor.itdashboard.model.*;
import com.amplexor.itdashboard.model.rest.CountryNetworkInfo;
import com.amplexor.itdashboard.model.rest.ServiceNetworkInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private ApplicationServicesDao applicationServicesDao;

    @Autowired
    private SharedServicesDao sharedServicesDao;

    @Autowired
    private NetworkService networkService;

    @Override
    public Optional<DashboardResponse> getDashboard() throws  Exception{

        DashboardResponse dashboardResponse = new DashboardResponse();

        Optional<NetworkInfoResponse> networkInfoResponse = networkService.getNetworkInfo();

        if(networkInfoResponse.isPresent()) {
            dashboardResponse.setNetworkServicesList(NetworkServiceImpl.convertOutputToNetworkServices(networkInfoResponse.get().getNetworkInfoList()));
        }

        List<ApplicationServices> applicationServicesList = applicationServicesDao.findAll();
        dashboardResponse.setApplicationServicesList(applicationServicesList);

        List<SharedServices> sharedServicesList = sharedServicesDao.findAll();
        dashboardResponse.setSharedServicesList(sharedServicesList);

        return Optional.ofNullable(dashboardResponse);
    }
}
