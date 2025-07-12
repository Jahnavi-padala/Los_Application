package com.jocata.aadhaar.details.Dao.Impl;

import com.jocata.aadhaar.details.Dao.AadhaarDao;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Repository
public class AadhaarDaoImpl implements AadhaarDao {
    public String getAadhaarDetails(){
        try {
            ClassPathResource resource = new ClassPathResource("aadhaarDetails.xml"); // Place XML in resources
            Path path = resource.getFile().toPath();
            return Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read Aadhaar XML file", e);
        }
    }
}
