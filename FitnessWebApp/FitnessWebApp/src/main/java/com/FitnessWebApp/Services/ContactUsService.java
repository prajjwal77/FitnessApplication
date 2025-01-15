package com.FitnessWebApp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FitnessWebApp.Entities.ContactUs;
import com.FitnessWebApp.Repositories.ContactUsRepository;

@Service
public class ContactUsService {
    @Autowired
    private ContactUsRepository contactUsRepository;

    public void saveContactMessage(ContactUs contactUs) {
        contactUsRepository.save(contactUs);
    }
}
