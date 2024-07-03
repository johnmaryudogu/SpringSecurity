package com.johnmary.SprongSecurityDemo.Service;

import com.johnmary.SprongSecurityDemo.Dto.EmailDetails;

public interface EmailService {

    void sendEmailAlert(EmailDetails emailDetails);
}
