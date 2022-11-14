package utils;

import entity.Address;

import java.util.HashMap;

public class AddressCounter implements Runnable {

    private static final HashMap<Address, Integer> addressMap = new HashMap<>();
    private final Address address;


    public AddressCounter(Address address) {
        this.address = address;
    }


    public static HashMap<Address, Integer> getAddressMap() {
        return addressMap;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public void run() {
        // count city
        synchronized (addressMap) {
            addressMap.merge(address, 1, Integer::sum);
        }
    }
}