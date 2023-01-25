package com.assessment.app.address.repo;

import com.assessment.app.address.model.Address;
import com.assessment.app.address.model.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Objects;

@Singleton
public class AddressRepositoryImp implements AddressRepository {

    private final static String ADDRESSES_FILE = "/addresses.json";

    private final Gson gson;

    @Inject
    public AddressRepositoryImp(Gson gson) {
        this.gson = gson;
    }

    @Override
    public Address getAddressById(@NonNull String id) {
        return getAllAddresses().stream()
                .filter(address -> id.equals(address.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Address getAddressByType(@NonNull Type type) {
        return getAllAddresses().stream()
                .filter(address -> type.getCode().equals(address.getType().getCode()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Address> getAllAddresses() {
        try (Reader reader = new InputStreamReader(
                Objects.requireNonNull(this.getClass().getResourceAsStream(ADDRESSES_FILE)))) {
            return gson.fromJson(reader, new TypeToken<List<Address>>(){}.getType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
