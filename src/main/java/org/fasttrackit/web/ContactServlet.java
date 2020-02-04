package org.fasttrackit.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.config.ObjectMapperConfiguration;
import org.fasttrackit.domain.Contacts;
import org.fasttrackit.service.ContactService;
import org.fasttrackit.transfer.CreateContactRequest;
import org.fasttrackit.transfer.UpdateContactRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/contacts")
public class ContactServlet extends HttpServlet{

        private ContactService contactService = new ContactService();

        //endpoint
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            CreateContactRequest request = ObjectMapperConfiguration.objectMapper.readValue(req.getReader(), CreateContactRequest.class);
            try {
               contactService.createContact(request);
            } catch (SQLException | ClassNotFoundException e) {
                resp.sendError(500, "Internal server error: " + e.getMessage());
            }
        }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        try {
            contactService.deleteContact(Long.parseLong(id));
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal server error: " + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        UpdateContactRequest request = ObjectMapperConfiguration.objectMapper.readValue(req.getReader(), UpdateContactRequest.class);

        try {
            contactService.updateContact(Long.parseLong(id), request);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal server error: " + e.getMessage());
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            List<Contacts> contacts = contactService.getContact();
            String response = ObjectMapperConfiguration.objectMapper.writeValueAsString(contacts);

            resp.getWriter().println(response);

        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal server error: " + e.getMessage());
        }
    }
}
