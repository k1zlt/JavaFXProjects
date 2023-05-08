package uca.org.javafx.ContactsApp;

public class Contact {
    private String name;
    private String surname;
    private String phoneNumber;

    private String email;

    public Contact(String name, String surname, String phoneNumber, String email) throws Exception {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        if (!checkName() || !checkPhonenumber()) {
            throw new Exception("Wrong Name or Phonenumber");
        }
    }

    public boolean checkName() {
        return name.length() != 0;
    }

    public boolean checkPhonenumber() {
        return phoneNumber.matches("\\d{9}");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return this.name + " " + this.surname;
    }
}
