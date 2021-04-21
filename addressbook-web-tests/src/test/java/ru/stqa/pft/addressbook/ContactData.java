package ru.stqa.pft.addressbook;

public class ContactData {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String email;
    private final String address;
    private final String mobilePhoneNumber;

    public ContactData(String firstName, String middleName, String lastName, String email, String address, String mobilePhoneNumber) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }
}
