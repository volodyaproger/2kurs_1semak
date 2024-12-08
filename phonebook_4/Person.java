public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String phone1;
    private String phone2;
    private String phone3;

    // Конструкторы
    public Person(String firstName, String lastName, String phone1, String phone2, String phone3) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.phone3 = phone3;
    }

    public Person(int id, String firstName, String lastName, String phone1, String phone2, String phone3) {
        this(firstName, lastName, phone1, phone2, phone3);
        this.id = id;
    }

    // Геттеры и сеттеры
    public int getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPhone1() { return phone1; }
    public String getPhone2() { return phone2; }
    public String getPhone3() { return phone3; }

    @Override
    public String toString() {
        return String.format("%s %s: %s, %s, %s", firstName, lastName, phone1, phone2, phone3);
    }
}
