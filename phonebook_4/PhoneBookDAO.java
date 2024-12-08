package com.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhoneBookDAO {
    private final String url = "jdbc:mysql://localhost:3306/phonebook_db";
    private final String user = "root";
    private final String password = "password";

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void addPerson(Person person) throws SQLException {
        String query = "INSERT INTO phonebook (first_name, last_name, phone1, phone2, phone3) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, person.getFirstName());
            stmt.setString(2, person.getLastName());
            stmt.setString(3, person.getPhone1());
            stmt.setString(4, person.getPhone2());
            stmt.setString(5, person.getPhone3());
            stmt.executeUpdate();
        }
    }

    public List<Person> getAllPersons() throws SQLException {
        String query = "SELECT * FROM phonebook ORDER BY last_name, first_name, phone1";
        List<Person> persons = new ArrayList<>();
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                persons.add(new Person(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("phone1"),
                    rs.getString("phone2"),
                    rs.getString("phone3")
                ));
            }
        }
        return persons;
    }

    public Person searchByLastName(String lastName) throws SQLException {
        String query = "SELECT * FROM phonebook WHERE last_name = ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, lastName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Person(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("phone1"),
                        rs.getString("phone2"),
                        rs.getString("phone3")
                    );
                }
            }
        }
        return null;
    }

    public Person searchByPhone(String phone) throws SQLException {
        String query = "SELECT * FROM phonebook WHERE phone1 = ? OR phone2 = ? OR phone3 = ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, phone);
            stmt.setString(2, phone);
            stmt.setString(3, phone);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Person(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("phone1"),
                        rs.getString("phone2"),
                        rs.getString("phone3")
                    );
                }
            }
        }
        return null;
    }

    public void updatePerson(int id, Person person) throws SQLException {
        String query = "UPDATE phonebook SET first_name = ?, last_name = ?, phone1 = ?, phone2 = ?, phone3 = ? WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, person.getFirstName());
            stmt.setString(2, person.getLastName());
            stmt.setString(3, person.getPhone1());
            stmt.setString(4, person.getPhone2());
            stmt.setString(5, person.getPhone3());
            stmt.setInt(6, id);
            stmt.executeUpdate();
        }
    }

    public void deletePerson(int id) throws SQLException {
        String query = "DELETE FROM phonebook WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
