package nus.edu.sg.workshop13.controller;

import static nus.edu.sg.workshop13.util.Constants.DATA_DIR_ENV_VAR_KEY;

import java.util.List;

// import third party library for logging
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import nus.edu.sg.workshop13.model.Contact;
import nus.edu.sg.workshop13.util.Contacts;

@Controller
public class AddressBookController {
    private static final Logger logger = LoggerFactory.getLogger(AddressBookController.class);
    @Autowired
    private Environment env;

    @GetMapping("/addcontact")
    public String showAddContactForm(Model model) {
        Contact contact = new Contact();
        model.addAttribute("contact", contact);
        return "form";
    }

    @GetMapping("/")
    public String showAddressBook(Model model) {
        Contacts utilContacts = new Contacts(env.getProperty(DATA_DIR_ENV_VAR_KEY));
        List<Contact> contactsList = utilContacts.getContacts();
        model.addAttribute("contactsList", contactsList);
        return "addressbook";
    }

}
