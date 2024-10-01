package com.cydeo.controller;

import com.cydeo.dto.AddressDTO;
import com.cydeo.dto.ResponseDTO;
import com.cydeo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    /*
     Endpoint: /api/v1/address/{addressNo}
     HTTP Status Code: 200

     JSON Response Body:
     "success": true
     "message": "Address <addressNo> is successfully retrieved."
     "code":200
     "data":<address data>
    */

    @GetMapping("/{addressNo}")
    public ResponseEntity<ResponseDTO> getAddressByAddressNo(@PathVariable String addressNo) {
        ResponseDTO responseDTO = ResponseDTO
                .builder()
                .success(true)
                .message("Address " + addressNo +  " is successfully retrieved.")
                .code(200)
                .data(addressService.findByAddressNo(addressNo))
                .build();


        return ResponseEntity
                .status(HttpStatus.OK.value())
                .body(responseDTO);
    }



    /*
      Endpoint: /api/v1/address/{addressNo}

      JSON Response Body:
      <updated address data>
     */

    @PutMapping("/{addressNo}")
    public AddressDTO updateAddressByAddressNo(@PathVariable String addressNo, @RequestBody AddressDTO addressDTO) {
        return addressService.update(addressNo, addressDTO);
    }

}
