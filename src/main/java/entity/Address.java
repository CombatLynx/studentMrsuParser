package entity;

import java.util.Objects;

public class Address {
    private int id;           // идентификатор
    private String city;      // город
    private String street;    // улица
    private int house;        // дом
    private int floor;        // этаж
    private int flatNumber;   // квартира

    public Address(int id, String city, String street, int house, int floor, int flatNumber) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.house = house;
        this.floor = floor;
        this.flatNumber = flatNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(int flatNumber) {
        this.flatNumber = flatNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return house == address.house && floor == address.floor && flatNumber == address.flatNumber && city.equals(address.city) && street.equals(address.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, house, floor, flatNumber);
    }
}