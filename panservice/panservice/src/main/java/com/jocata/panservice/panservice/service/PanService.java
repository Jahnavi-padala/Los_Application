package com.jocata.panservice.panservice.service;

import com.jocata.panservice.panservice.entity.PanDetails;

public interface PanService {
    PanDetails getPanDetails(String panNumber);
}
