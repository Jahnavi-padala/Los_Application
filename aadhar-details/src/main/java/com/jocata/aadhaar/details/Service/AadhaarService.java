package com.jocata.aadhaar.details.Service;

import com.jocata.aadhaar.details.form.AadhaarDetails;
import com.jocata.aadhaar.details.form.AadhaarResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public interface AadhaarService {
    AadhaarDetails getAadhaarDetails(String aadhaarNumber);

}
