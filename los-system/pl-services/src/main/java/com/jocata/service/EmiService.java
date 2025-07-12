package com.jocata.service;

import com.jocata.form.EmiForm;

import java.util.List;

public interface EmiService {
    List<EmiForm> calculateEmisByApplicationId(String applicationId);}
