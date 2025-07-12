package com.jocata.controller;

import com.jocata.form.EmiForm;
import com.jocata.service.EmiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmiController {
    @Autowired
    private EmiService emiService;

    @GetMapping("/calculateEmis/{applicationId}")
    public List<EmiForm> calculateEmis(@PathVariable String applicationId) {
        return emiService.calculateEmisByApplicationId(applicationId);
    }
}
