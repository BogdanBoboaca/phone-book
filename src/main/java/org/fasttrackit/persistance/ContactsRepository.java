package org.fasttrackit.persistance;

import org.fasttrackit.domain.Contacts;
import org.fasttrackit.transfer.CreateContactRequest;
import org.fasttrackit.transfer.UpdateContactRequest;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactsRepository {

    public void createContact(CreateContactRequest request) throws IOException, SQLException {

        String sql = "INSERT INTO contacts (name, first_name, phone_number) VALUES (?, ?,?)";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1,request.getName());
            preparedStatement.setString(2,request.getFirst_name());
            preparedStatement.setString(3,request.getPhone_number());

            preparedStatement.executeUpdate();
        }
    }

    public void updateContact(long contact_id, UpdateContactRequest request) throws IOException, SQLException {

        String sql = "UPDATE contacts SET name = ?, first_name = ?, phone_number = ? WHERE contact_id = ?";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1,request.getName());
            preparedStatement.setString(2,request.getFirst_name());
            preparedStatement.setString(3,request.getPhone_number());
            preparedStatement.setLong(4, contact_id);

            preparedStatement.executeUpdate();
        }
    }

    public void deleteContact(long contact_id) throws IOException, SQLException {

        String sql = "DELETE FROM contacts WHERE contact_id = ?";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setLong(1, contact_id);

            preparedStatement.executeUpdate();
        }
    }

    // read statement
    public List<Contacts> getContacts() throws IOException, SQLException {
        String sql = "SELECT contact_id, name, first_name, phone_number FROM contacts";

        try (Connection connection = DatabaseConfiguration.getConnection();
             //Statement should be used only for no parameters queries
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){

            List<Contacts> contacts = new ArrayList<>();

            while (resultSet.next()){
                Contacts contact = new Contacts();

                contact.setUser_id(resultSet.getLong("id"));
                contact.setName(resultSet.getString("name"));
                contact.setFirst_name(resultSet.getString("first_name"));
                contact.setPhone_number(resultSet.getString("phone_number"));

                contacts.add(contact);
            }

            return contacts;
        }
    }
}
