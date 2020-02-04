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

    public void createContact(CreateContactRequest request) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Creating contact" + request);
        contactsRepository.createContact(request);
    }

    public void updateContact (long id, UpdateContactRequest request) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Updating contact: " + ": " + request);
        contactsRepository.updateContact(id, request);
    }

    public void deleteContact (long id) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Delete task" + id);
        contactsRepository.deleteContact(id);
    }

    public List<Contacts> getContact() throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Retrieving all tasks.");
        return contactsRepository.getContacts();
    }
}
