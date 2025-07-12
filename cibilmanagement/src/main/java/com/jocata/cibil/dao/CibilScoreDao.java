package com.jocata.cibil.dao;

import com.jocata.cibil.entity.CibilScores;
import com.jocata.cibil.entity.CreditReports;

public interface CibilScoreDao {
    CibilScores create(CibilScores score);
    CibilScores findByReportId(Integer reportId);

}
