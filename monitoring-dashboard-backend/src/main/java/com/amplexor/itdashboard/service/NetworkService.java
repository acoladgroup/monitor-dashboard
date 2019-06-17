package com.amplexor.itdashboard.service;

import com.amplexor.itdashboard.model.*;
import java.util.Optional;

public interface NetworkService {

    Optional<NetworkInfoResponse> getNetworkInfo() throws  Exception;
    Optional<NetworkInfoResponse> getRealTimeNetworkInfo() throws  Exception;

    Optional<NetworkInfoResponse> retrieveNetworkInfo() throws  Exception;

    void retrieveAndSaveNetworkInfo() throws  Exception;
}
