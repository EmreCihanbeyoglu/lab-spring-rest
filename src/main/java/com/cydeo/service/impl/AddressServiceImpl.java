package com.cydeo.service.impl;

import com.cydeo.client.country.CountryClientImpl;
import com.cydeo.client.weather.WeatherClientImpl;
import com.cydeo.dto.AddressDTO;
import com.cydeo.entity.Address;
import com.cydeo.exception.NotFoundException;
import com.cydeo.repository.AddressRepository;
import com.cydeo.service.AddressService;
import com.cydeo.util.MapperUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service

public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final MapperUtil mapperUtil;
    private final WeatherClientImpl weatherClient;
    private final CountryClientImpl countryClient;

    @Value("${integration.weatherClient.accessKey}")
    private String accessKey;

    public AddressServiceImpl(AddressRepository addressRepository, MapperUtil mapperUtil, WeatherClientImpl weatherClient, CountryClientImpl countryClient) {
        this.addressRepository = addressRepository;
        this.mapperUtil = mapperUtil;
        this.weatherClient = weatherClient;
        this.countryClient = countryClient;
    }


    @Override
    public AddressDTO findByAddressNo(String addressNo) {

        Address foundAddress = addressRepository.findByAddressNo(addressNo)
                .orElseThrow(() -> new NotFoundException("No Address Found!"));

        AddressDTO addressDTO = mapperUtil.convert(foundAddress, new AddressDTO());
        addressDTO.setCurrentTemperature(weatherClient.retrieveTemperatureByCity(addressDTO.getCity()));
        addressDTO.setFlag(countryClient.retrieveFlagByCountryName(addressDTO.getCountry()));
        return addressDTO;
    }



    @Override
    public AddressDTO update(String addressNo, AddressDTO address) {

        Address foundAddress = addressRepository.findByAddressNo(addressNo)
                .orElseThrow(() -> new NotFoundException("No Address Found!"));

        Address addressToUpdate = mapperUtil.convert(address, new Address());

        addressToUpdate.setAddressNo(addressNo);
        addressToUpdate.setId(foundAddress.getId());

        Address updatedAddress = addressRepository.save(addressToUpdate);

        return mapperUtil.convert(updatedAddress, new AddressDTO());

    }
}
