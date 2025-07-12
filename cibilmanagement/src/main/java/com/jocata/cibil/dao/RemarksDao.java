package com.jocata.cibil.dao;

import com.jocata.cibil.entity.CreditReports;
import com.jocata.cibil.entity.Remarks;

import java.util.List;

public interface RemarksDao {
    Remarks create(Remarks remarks);
    List<Remarks> findByReportId(Integer reportId);
}
