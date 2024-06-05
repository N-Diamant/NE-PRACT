package com.NE.demo.productManagement.Controllers;

import com.NE.demo.Exception.UserExistsException;
import com.NE.demo.productManagement.Models.Customer;
import com.NE.demo.productManagement.Models.LoginModel;
import com.NE.demo.productManagement.Models.RegistrationModel;
import com.NE.demo.productManagement.Repositories.CustomerRepository;
import com.NE.demo.productManagement.Services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegistrationModel registrationModel) {

        try {
            customerService.registerUser(registrationModel);
            return ResponseEntity.ok().build();
        }catch (UserExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginModel loginModel) {
        try {
            String token = customerService.loginUser(loginModel);
            if (token != null) {
                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login");
        }
    }



}

