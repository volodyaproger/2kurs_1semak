package com.example;

import java.util.List;
import java.util.Scanner;

public class PhoneBookApp {
    private static final PhoneBookDAO dao = new PhoneBookDAO();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nPhone Book Application");
            System.out.println("1. Add Person");
            System.out.println("2. View All Persons");
            System.out.println("3. Search by Last Name");
            System.out.println("4. Search by Phone");
            System.out.println("5. Update Person");
            System.out.println("6. Delete Person");
            System.out.println("7. Exit");

            int choice = getUserChoice();

            try {
                switch (choice) {
                    case 1 -> addPerson();
                    case 2 -> viewAllPersons();
                    case 3 -> searchByLastName();
                    case 4 -> searchByPhone();
                    case 5 -> updatePerson();
                    case 6 -> deletePerson();
                    case 7 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid option!");
                }
            } catch (Exception e) {
                System.err.println("Error: ");
                e.printStackTrace();
            }
        }
    }

    private static int getUserChoice() {
        while (true) {
            System.out.print("Choose an option: ");
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static void addPerson() throws Exception {
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Phone 1: ");
        String phone1 = scanner.nextLine();
        System.out.print("Phone 2 (optional): ");
        String phone2 = scanner.nextLine();
        System.out.print("Phone 3 (optional): ");
        String phone3 = scanner.nextLine();
        dao.addPerson(new Person(firstName, lastName, phone1, phone2, phone3));
        System.out.println("Person added successfully!");
    }

    private static void viewAllPersons() throws Exception {
        List<Person> persons = dao.getAllPersons();
        if (persons.isEmpty()) {
            System.out.println("No persons found.");
        } else {
            persons.forEach(System.out::println);
        }
    }

    private static void searchByLastName() throws Exception {
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        List<Person> persons = dao.searchByLastName(lastName);
        if (persons.isEmpty()) {
            System.out.println("No persons found.");
        } else {
            persons.forEach(System.out::println);
        }
    }

    private static void searchByPhone() throws Exception {
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();
        Person person = dao.searchByPhone(phone);
        if (person != null) {
            System.out.println(person);
        } else {
            System.out.println("Person not found.");
        }
    }

    private static void updatePerson() throws Exception {
        System.out.print("Enter ID of person to update: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

System.out.print("Phone 1: ");
        String phone1 = scanner.nextLine();
        System.out.print("Phone 2 (optional): ");
        String phone2 = scanner.nextLine();
        System.out.print("Phone 3 (optional): ");
        String phone3 = scanner.nextLine();

        dao.updatePerson(id, new Person(firstName, lastName, phone1, phone2, phone3));
        System.out.println("Person updated successfully!");
    }

    private static void deletePerson() throws Exception {
        System.out.print("Enter ID of person to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        dao.deletePerson(id);
        System.out.println("Person deleted successfully!");
    }
}
