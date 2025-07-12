package com.jocata.panservice.panservice.dao.Impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jocata.panservice.panservice.dao.PanDao;
import com.jocata.panservice.panservice.entity.PanDetails;
import com.jocata.panservice.panservice.service.PanService;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository

public class PanDaoImpl implements PanDao {
    @Override
    public String getPanDetails() {

        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("panservice.json");
            if (is == null) return "File not found";

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String jsonString = reader.lines().collect(Collectors.joining("\n"));
            return jsonString;

        } catch (Exception e) {
            e.printStackTrace();
            return "Error reading file";
}
}

}
