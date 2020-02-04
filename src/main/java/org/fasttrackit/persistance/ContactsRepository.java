package org.fasttrackit.persistance;

import org.fasttrackit.domain.Contacts;
import org.fasttrackit.transfer.CreateContactRequest;
import org.fasttrackit.transfer.UpdateContactRequest;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactsRepository {

    public void createContact(CreateContactRequest request) throws IOException, SQLException, ClassNotFoundException {

        String sql = "INSERT INTO contacts (surName, firstName, phoneNumber) VALUES (?, ?,?)";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1,request.getSurName());
            preparedStatement.setString(2,request.getFirstName());
            preparedStatement.setString(3,request.getPhoneNumber());

            preparedStatement.executeUpdate();
        }
    }

    public void updateContact(long id, UpdateContactRequest request) throws IOException, SQLException, ClassNotFoundException {

        String sql = "UPDATE contacts SET surName = ?, firstName = ?, phoneNumber = ? WHERE id = ?";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1,request.getSurName());
            preparedStatement.setString(2,request.getFirstName());
            preparedStatement.setString(3,request.getPhoneNumber());
            preparedStatement.setLong(4, id);

            preparedStatement.executeUpdate();
        }
    }

    public void deleteContact(long id) throws IOException, SQLException, ClassNotFoundException {

        String sql = "DELETE FROM contacts WHERE id = ?";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        }
    }

    // read statement
    public List<Contacts> getContacts() throws IOException, SQLException, ClassNotFoundException {
        String sql = "SELECT id, surName, firstName, phoneNumber FROM contacts";

        try (Connection connection = DatabaseConfiguration.getConnection();
             //Statement should be used only for no parameters queries
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){

            List<Contacts> contacts = new ArrayList<>();

            while (resultSet.next()){
                Contacts contact = new Contacts();

                contact.setUser_id(resultSet.getLong("id"));
                contact.setName(resultSet.getString("surName"));
                contact.setFirst_name(resultSet.getString("firstName"));
                contact.setPhone_number(resultSet.getString("phoneNumber"));

                contacts.add(contact);
            }

            return contacts;
        }
    }
}
