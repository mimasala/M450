
import ch.tbz.m450.repository.Address;
import ch.tbz.m450.repository.AddressRepository;
import ch.tbz.m450.service.AddressService;
import ch.tbz.m450.util.AddressComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        Address newAddress = new Address();
        newAddress.setFirstname("John");
        newAddress.setLastname("Doe");

        when(addressRepository.save(any(Address.class))).thenReturn(newAddress);
        Address savedAddress = addressService.save(newAddress);

        assertNotNull(savedAddress);
        assertEquals(newAddress.getFirstname(), savedAddress.getFirstname());
        assertEquals(newAddress.getLastname(), savedAddress.getLastname());

        verify(addressRepository).save(any(Address.class));
    }

    @Test
    void getAll() {
        List<Address> addressList = Arrays.asList(
                new Address(1, "John", "Doe", "123456789", new Date()),
                new Address(2, "Sandra", "Doe", "324782288", new Date())
        );

        when(addressRepository.findAll()).thenReturn(addressList);
        List<Address> result = addressService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.containsAll(addressList) && addressList.containsAll(result));

        verify(addressRepository).findAll();
    }


    @Test
    void getAddress() {
        int addressId = 1;
        Address mockAddress = new Address();
        mockAddress.setId(addressId);
        mockAddress.setFirstname("John");
        mockAddress.setLastname("Doe");

        when(addressRepository.findById(addressId)).thenReturn(Optional.of(mockAddress));
        Optional<Address> result = addressService.getAddress(addressId);

        assertTrue(result.isPresent());
        assertEquals(mockAddress, result.get());

        verify(addressRepository).findById(addressId);
    }
    @Test
    void addressComparatorTest() {
        Address address1 = new Address(1, "John", "Doe", "123456789", new Date(1000000000000L));
        Address address2 = new Address(2, "Alice", "Doe", "987654321", new Date(1000000000000L));
        Address address3 = new Address(3, "John", "Smith", "192837465", new Date(1100000000000L));

        List<Address> addresses = Arrays.asList(address1, address2, address3);
        addresses.sort(new AddressComparator());

        assertEquals(address2, addresses.get(0));
        assertEquals(address1, addresses.get(1));
        assertEquals(address3, addresses.get(2));
    }
}