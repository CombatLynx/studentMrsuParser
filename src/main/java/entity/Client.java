package entity;

import java.util.Objects;

public class Client {
    public int id;                 // идентификатор
    public String name;            // ФИО
    public String personnelNumber; // персональный номер
    public Address address;        // адресс проживания

    public Client(int id, String name, String personnelNumber, Address address) {
        this.id = id;
        this.name = name;
        this.personnelNumber = personnelNumber;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonnelNumber() {
        return personnelNumber;
    }

    public void setPersonnelNumber(String personnelNumber) {
        this.personnelNumber = personnelNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return name.equals(client.name) && personnelNumber.equals(client.personnelNumber) && address.equals(client.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, personnelNumber, address);
    }
}