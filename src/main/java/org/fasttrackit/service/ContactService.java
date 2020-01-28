package org.fasttrackit.service;

import org.fasttrackit.domain.Contacts;
import org.fasttrackit.persistance.ContactsRepository;
import org.fasttrackit.transfer.CreateContactRequest;
import org.fasttrackit.transfer.UpdateContactRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ContactService {

    private ContactsRepository contactsRepository = new ContactsRepository();

    public void createContact(CreateContactRequest request) throws IOException, SQLException {
        System.out.println("Creating contact" + request);
        contactsRepository.createContact(request);
    }

    public void updateContact (long user_id, UpdateContactRequest request) throws IOException, SQLException {
        System.out.println("Updating contact: " + ": " + request);
        contactsRepository.updateContact(user_id, request);
    }

    public void deleteContact (long user_id) throws IOException, SQLException {
        System.out.println("Delete task" + user_id);
        contactsRepository.deleteContact(user_id);
    }

    public List<Contacts> getContact() throws IOException, SQLException {
        System.out.println("Retrieving all tasks.");
        return contactsRepository.getContacts();
    }
}
