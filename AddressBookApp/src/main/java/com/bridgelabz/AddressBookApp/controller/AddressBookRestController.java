package com.bridgelabz.AddressBookApp.controller;

/*
   Use Case : 11
   Provide User Friendly Error Response in case validation fails.
   - Created Custom GlobalExceptionHandler class using @RestControllerAdvice Annotation so that Spring
     Framework can call this class to handle Exceptions thrown during processing REST API Request.
   - Added @ExceptionHandler for MethodArgumentNotValidException.
*/

import com.bridgelabz.AddressBookApp.dto.ContactDTO;
import com.bridgelabz.AddressBookApp.model.ContactData;
import com.bridgelabz.AddressBookApp.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addressbookservice")
public class AddressBookRestController {

    @Autowired
    AddressService addressService;

    @GetMapping("")
    public ResponseEntity<List<ContactData>> getAllAddresses() {
        List<ContactData> contactDataList = addressService.getAllAddress();
        return new ResponseEntity<>(contactDataList, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ContactData> getAddressById(@PathVariable("id") long id) {
        ContactData contactData = addressService.getAddressByID(id);
        return new ResponseEntity<>(contactData, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ContactData> creatingUser(@Valid @RequestBody ContactDTO contactDTO) {
       ContactData contactData=addressService.creatingUser(contactDTO);
        return new ResponseEntity<>(contactData, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ContactData> updatingUserDetails(@Valid @PathVariable long id,@RequestBody ContactDTO contactDTO) {
        ContactData contactData=addressService.updatingUserData(id,contactDTO);
        return new ResponseEntity<>(contactData, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletingUser(@PathVariable long id) {
        String message = addressService.deleteUser(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}