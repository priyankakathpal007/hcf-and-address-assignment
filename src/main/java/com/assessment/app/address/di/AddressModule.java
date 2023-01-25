package com.assessment.app.address.di;

import com.assessment.app.address.repo.AddressRepository;
import com.assessment.app.address.repo.AddressRepositoryImp;
import com.assessment.app.address.viewmodel.AddressViewModel;
import com.google.inject.AbstractModule;

public class AddressModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(AddressRepository.class).to(AddressRepositoryImp.class);
        bind(AddressViewModel.class);
    }

}
