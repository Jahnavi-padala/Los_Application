package com.jocata.panservice.panservice.controller;

import com.jocata.panservice.panservice.entity.PanDetails;
import com.jocata.panservice.panservice.entity.PanResponse;
import com.jocata.panservice.panservice.service.PanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class PanController {

    @Autowired
    private PanService panService;
//
//    @PostMapping("/getPanDetails")
//    public Object getPanDetails(@RequestBody PanDetails panDetails) {
//        return service.getPanDetails(panDetails.getPanNumber().trim());
//    }
@GetMapping("/getPanDetails")
public PanResponse getPanDetails(@RequestParam String panNumber) {
    PanDetails details = null;
    try {
        details = panService.getPanDetails(panNumber);
        if (details != null) {
            return new PanResponse(details, "success", "pan details found");
        } else {
            return new PanResponse(null, "error", "PAN not found");
        }
    } catch (Exception e) {
        return new PanResponse(null, "success", "Something went wrong");
}
}

    // Optionally keep path variable version if needed in future
}
