package com.test.userauthservice.service.impl;

import com.test.userauthservice.repository.AddressesRepo;
import com.test.userauthservice.service.IAddresses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressesServiceImpl implements IAddresses {
    private final AddressesRepo addressesRepo;

}
