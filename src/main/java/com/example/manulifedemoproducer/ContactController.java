package com.example.manulifedemoproducer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;


@RestController
@Slf4j
public class ContactController{

    @Autowired
    ContactRepository contactRepository;

    @PostMapping("/killme")
    public void killme(){
        System.exit(0);
    }

    @PreAuthorize("#oauth2.hasScope('contact.read')")
    @GetMapping("/contacts")
    public List<Contact> getAll(Principal principal){
        log.info("Principal Name: " + principal.getName());
        return contactRepository.findAll();
    }

    @PreAuthorize("#oauth2.hasScope('contact.admin')")
    @PostMapping("/contact")
    public Contact newContact(@RequestBody Contact contact){
        return contactRepository.save(contact);
    }
}


