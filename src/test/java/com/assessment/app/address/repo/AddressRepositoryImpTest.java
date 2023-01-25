package com.assessment.app.address.repo;

import com.assessment.app.address.model.Address;
import com.assessment.app.address.model.Type;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static com.assessment.app.address.Constants.PHYSICAL_ADDRESS;
import static org.junit.Assert.*;

public class AddressRepositoryImpTest {

    private AddressRepository addressRepository;

    @Before
    public void setUp() {
        addressRepository = new AddressRepositoryImp(new Gson());
    }

    @Test
    public void getAddressById_returnNull_invalidAddressId() {
        assertNull(addressRepository.getAddressById("-1"));
    }

    @Test
    public void getAddressById_returnAddress_validAddressId() {
        assertNotNull(addressRepository.getAddressById("1"));
    }

    @Test
    public void getAddressByType_returnNull_invalidAddressType() {
        Type type = new Type("-1", "INVALID");

        assertNull(addressRepository.getAddressByType(type));
    }

    @Test
    public void getAddressByType_returnAddress_validAddressType() {
        Type type = new Type("1", PHYSICAL_ADDRESS);

        Address address = addressRepository.getAddressByType(type);

        assertNotNull(address);
        assertEquals("Physical Address", address.getType().getName());
    }

    @Test
    public void getAllAddresses_returnAddressList() throws IOException {
        List<Address> allAddresses = addressRepository.getAllAddresses();

        assertNotNull(allAddresses);
        assertEquals(3, allAddresses.size());
    }

}