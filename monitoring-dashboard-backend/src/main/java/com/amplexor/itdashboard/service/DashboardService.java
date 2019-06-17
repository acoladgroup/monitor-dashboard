package com.amplexor.itdashboard.service;

import com.amplexor.itdashboard.model.BackupStatusResponse;
import com.amplexor.itdashboard.model.DashboardResponse;
import com.amplexor.itdashboard.model.DatabaseStatusResponse;
import com.amplexor.itdashboard.model.TableSpace;

import java.util.List;
import java.util.Optional;

public interface DashboardService {

    Optional<DashboardResponse> getDashboard() throws Exception;

}
