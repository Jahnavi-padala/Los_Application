package com.jocata.aadhaar.details.Service.Impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.jocata.aadhaar.details.Dao.AadhaarDao;
import com.jocata.aadhaar.details.Service.AadhaarService;
import com.jocata.aadhaar.details.form.AadhaarDetails;
import com.jocata.aadhaar.details.form.AadhaarResponse;
import com.jocata.aadhaar.details.form.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
@Service
public class AadhaarServiceImpl implements AadhaarService {

    @Autowired
    private AadhaarDao dao;
    @Override
    public AadhaarDetails getAadhaarDetails(String aadhaarNumber) {
        for (AadhaarDetails details : getAllAadhaarDetails()) {
            if (details.getAadhaarNumber().equals(aadhaarNumber)) {
                return details;
            }
        }
        return null;
    }


    public List<AadhaarDetails> getAllAadhaarDetails() {
        List<AadhaarDetails> result = new ArrayList<>();
        try {
            String xml = dao.getAadhaarDetails();
            XmlMapper mapper = new XmlMapper();
            JsonNode root = mapper.readTree(xml);
            JsonNode persons = root.get( "person");

            if (persons != null && persons.isArray()) {
                for (JsonNode node : persons) {
                    AadhaarDetails details = new AadhaarDetails();
                    details.setAadhaarNumber(node.get("aadhaarNumber").asText());
                    details.setName(node.get("name").asText());
                    details.setGender(node.get("gender").asText());
                    details.setDob(node.get("dob").asText());
                    details.setFatherName(node.get("fatherName").asText());

                    JsonNode addr = node.get("address");
                    Address address = new Address();
                    address.setStreet(addr.get("street").asText());
                    address.setCity(addr.get("city").asText());
                    address.setState(addr.get("state").asText());
                    address.setPinCode(addr.get("pincode").asText());

                    details.setAddress(address);
                    result.add(details);
                }
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return result;
    }



}
