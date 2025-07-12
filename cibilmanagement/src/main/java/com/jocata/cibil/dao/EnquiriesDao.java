package com.jocata.cibil.dao;

import com.jocata.cibil.entity.CreditReports;
import com.jocata.cibil.entity.Enquiries;

import java.util.List;

public interface EnquiriesDao {
    Enquiries create(Enquiries enquiries);

    List<Enquiries> findByReportId(Integer reportId);
}
