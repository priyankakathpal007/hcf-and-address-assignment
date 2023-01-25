package com.assessment.app.address.viewmodel;

import com.assessment.app.address.model.*;
import com.assessment.app.address.repo.AddressRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static com.assessment.app.address.Constants.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AddressViewModelTest {

    String ADDRESS_FORMAT_PHYSICAL = "Physical Address: Address 1 - Line 2 - City 1 - Eastern Cape - 1234 - South Africa";
    String ADDRESS_FORMAT_POSTAL = "Postal Address: City 2 - 2345 - Lebanon";
    String ADDRESS_FORMAT_BUSINESS = "Business Address: Address 3 - City 3 - 3456 - South Africa";

    private AddressViewModel viewModel;

    @Mock
    AddressRepository mockAddressRepository;
    @Mock
    Type mockType;
    @Mock
    Address mockAddress;

    @Before
    public void setUp() {
        initMocks(this);

        viewModel = new AddressViewModel(mockAddressRepository);
    }

    @Test
    public void fetchAddressById_returnNull_invalidAddressId() {
        assertNull(viewModel.fetchAddressById("-1"));
    }

    @Test
    public void fetchAddressById_returnAddress_validAddressId() {
        assertNull(viewModel.fetchAddressById("1"));
    }

    @Test
    public void fetchAddressByType_returnNull_invalidAddressType() {
        assertNull(viewModel.fetchAddressByType("INVALID ADDRESS TYPE"));
    }

    @Test
    public void fetchAddressByType_returnPhysicalAddress_validAddressType() {
        when(mockAddress.getType()).thenReturn(mockType);
        when(mockType.getCode()).thenReturn(PHYSICAL_ADDRESS_CODE);
        when(mockType.getName()).thenReturn(PHYSICAL_ADDRESS);
        when(mockAddressRepository.getAddressByType(any())).thenReturn(mockAddress);

        Address address = viewModel.fetchAddressByType(PHYSICAL_ADDRESS);

        assertNotNull(address);
        assertEquals(PHYSICAL_ADDRESS_CODE, address.getType().getCode());
        assertEquals(PHYSICAL_ADDRESS, address.getType().getName());
    }

    @Test
    public void fetchAddressByType_returnBusinessAddress_validAddressType() {
        when(mockAddress.getType()).thenReturn(mockType);
        when(mockType.getCode()).thenReturn(BUSINESS_ADDRESS_CODE);
        when(mockType.getName()).thenReturn(BUSINESS_ADDRESS);
        when(mockAddressRepository.getAddressByType(any())).thenReturn(mockAddress);

        Address address = viewModel.fetchAddressByType(BUSINESS_ADDRESS);

        assertNotNull(address);
        assertEquals(BUSINESS_ADDRESS_CODE, address.getType().getCode());
        assertEquals(BUSINESS_ADDRESS, address.getType().getName());
    }

    @Test
    public void fetchAddressByType_returnPostalAddress_validAddressType() {
        when(mockAddress.getType()).thenReturn(mockType);
        when(mockType.getCode()).thenReturn(POSTAL_ADDRESS_CODE);
        when(mockType.getName()).thenReturn(POSTAL_ADDRESS);
        when(mockAddressRepository.getAddressByType(any())).thenReturn(mockAddress);

        Address address = viewModel.fetchAddressByType(POSTAL_ADDRESS);

        assertNotNull(address);
        assertEquals(POSTAL_ADDRESS_CODE, address.getType().getCode());
        assertEquals(POSTAL_ADDRESS, address.getType().getName());
    }

    @Test
    public void fetchAllAddresses_returnAddressList() {
        ArrayList<Address> addresses = new ArrayList<>();
        addresses.add(mockAddress);

        when(mockAddressRepository.getAllAddresses()).thenReturn(addresses);

        List<Address> allAddresses = viewModel.fetchAllAddresses();

        assertNotNull(allAddresses);
        assertEquals(1, allAddresses.size());
    }

    @Test
    public void prettyAddress_returnCorrectAddressFormat() {
        String expectedFormat = "Physical Address: Address 1 - Line 2 - City 1 - Eastern Cape - 1234 - South Africa";

        assertEquals(expectedFormat, viewModel.prettyAddress(physicalAddress()));
    }

    @Test
    public void printAddressByType_returnNull_invalidAddressCode() {
        assertNull(viewModel.printAddressByType("INVALID ADDRESS TYPE"));
    }

    @Test
    public void printAddressByType_returnPhysicalAddressString_validAddressCode() {
        when(mockAddressRepository.getAddressByType(any())).thenReturn(physicalAddress());

        assertEquals(ADDRESS_FORMAT_PHYSICAL, viewModel.printAddressByType(PHYSICAL_ADDRESS));
    }

    @Test
    public void printAddressByType_returnPostalAddressString_validAddressCode() {
        when(mockAddressRepository.getAddressByType(any())).thenReturn(postalAddress());

        assertEquals(ADDRESS_FORMAT_POSTAL, viewModel.printAddressByType(POSTAL_ADDRESS));
    }

    @Test
    public void printAddressByType_returnBusinessAddressString_validAddressCode() {
        when(mockAddressRepository.getAddressByType(any())).thenReturn(businessAddress());

        assertEquals(ADDRESS_FORMAT_BUSINESS, viewModel.printAddressByType(BUSINESS_ADDRESS));
    }

    @Test
    public void prettifiedAddresses_displaysCorrectAddressFormats() {
        List<String> expectedAddressFormats = new ArrayList<>();
        expectedAddressFormats.add(ADDRESS_FORMAT_PHYSICAL);
        expectedAddressFormats.add(ADDRESS_FORMAT_POSTAL);
        expectedAddressFormats.add(ADDRESS_FORMAT_BUSINESS);

        ArrayList<Address> addresses = new ArrayList<>();
        addresses.add(physicalAddress());
        addresses.add(postalAddress());
        addresses.add(businessAddress());

        when(mockAddressRepository.getAllAddresses()).thenReturn(addresses);

        List<String> prettifiedAddresses = viewModel.prettifiedAddresses();

        for (int i = 0; i < prettifiedAddresses.size(); i++) {
            assertEquals(expectedAddressFormats.get(i), prettifiedAddresses.get(i));
        }
    }

    @Test
    public void validateAddress_returnFalse_invalidAddress() {
        assertFalse(viewModel.validateAddress(postalAddress()).isValid());
    }

    @Test
    public void validateAddress_returnTrue_validAddress() {
        assertTrue(viewModel.validateAddress(physicalAddress()).isValid());
    }

    private Address physicalAddress() {
        Type type = new Type("1", "Physical Address");

        AddressLineDetail addressLineDetail = new AddressLineDetail();
        addressLineDetail.setLine1("Address 1");
        addressLineDetail.setLine2("Line 2");

        ProvinceOrState provinceOrState = new ProvinceOrState();
        provinceOrState.setCode("5");
        provinceOrState.setName("Eastern Cape");

        Country country = new Country();
        country.setCode("ZA");
        country.setName("South Africa");

        Address address = new Address();
        address.setType(type);
        address.setAddressLineDetail(addressLineDetail);
        address.setCityOrTown("City 1");
        address.setProvinceOrState(provinceOrState);
        address.setPostalCode("1234");
        address.setCountry(country);

        return address;
    }

    private Address postalAddress() {
        Type type = new Type("2", "Postal Address");

        Country country = new Country();
        country.setCode("LB");
        country.setName("Lebanon");

        Address address = new Address();
        address.setType(type);
        address.setCityOrTown("City 2");
        address.setPostalCode("2345");
        address.setCountry(country);

        return address;
    }

    private Address businessAddress() {
        Type type = new Type("5", "Business Address");

        AddressLineDetail addressLineDetail = new AddressLineDetail();
        addressLineDetail.setLine1("Address 3");
        addressLineDetail.setLine2("");

        Country country = new Country();
        country.setCode("ZA");
        country.setName("South Africa");

        Address address = new Address();
        address.setType(type);
        address.setAddressLineDetail(addressLineDetail);
        address.setCityOrTown("City 3");
        address.setPostalCode("3456");
        address.setCountry(country);

        return address;
    }
}