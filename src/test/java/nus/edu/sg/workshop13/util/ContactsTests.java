package nus.edu.sg.workshop13.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import nus.edu.sg.workshop13.model.Contact;

@SpringBootTest
class ContactsTests {
    String TEST_DATA_DIR = "./testDataDir";
    String TEST_NAME = "Test Name";
    String TEST_EMAIL = "Test Email";
    String TEST_PHONE = "Test Phone";

    @Test
    void CreateClass_WithDataDirArgument_ShouldSetDataDir() {
        Contacts contacts = new Contacts(TEST_DATA_DIR);

        assertEquals(TEST_DATA_DIR, contacts.getDataDir());
    }

    @Test
    void Save_WithValidContact_ShouldSaveContactFile() {
        Contact contact = new Contact();
        contact.setName(TEST_NAME);
        contact.setEmail(TEST_EMAIL);
        contact.setPhone(TEST_PHONE);
        Contacts contacts = new Contacts(TEST_DATA_DIR);
        Path contactFile = contacts.getContactFile(contact);
        if (contactFile.toFile().exists()) {
            try {
                Files.delete(contactFile);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (!contactFile.getParent().toFile().exists()) {
            try {
                Files.createDirectories(contactFile.getParent());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        contacts.save(contact);
        assertTrue(contactFile.toFile().exists());
    }
}
