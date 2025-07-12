package com.jocata.panservice.panservice.service.Impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jocata.panservice.panservice.dao.PanDao;
import com.jocata.panservice.panservice.entity.PanDetails;
import com.jocata.panservice.panservice.service.PanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PanServiceImpl implements PanService {
    @Autowired
    private PanDao panDao;

    @Override
    public PanDetails getPanDetails(String panNumber) {
        try {
            String json = panDao.getPanDetails();
            if (json == null) return null;

            ObjectMapper mapper = new ObjectMapper();
            List<PanDetails> panList = mapper.readValue(json, new TypeReference<List<PanDetails>>() {});

            for (PanDetails pan : panList) {
                if (pan.getPanNumber().equalsIgnoreCase(panNumber)) {
                    return pan;
                }
            }
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
 }
}

}
