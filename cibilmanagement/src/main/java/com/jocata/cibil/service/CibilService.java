package com.jocata.cibil.service;

import com.jocata.cibil.form.CreditReportRequestForm;
import com.jocata.cibil.form.CreditReportResponseForm;

public interface CibilService {
    CreditReportResponseForm createCibil(CreditReportRequestForm request);
    CreditReportResponseForm getCibilByPanNumber(String panNumber);
}
