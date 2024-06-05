package com.NE.demo.productManagement.Services;


import ch.qos.logback.classic.encoder.JsonEncoder;
import com.NE.demo.Exception.UserExistsException;
import com.NE.demo.productManagement.Models.Customer;
import com.NE.demo.productManagement.Models.LoginModel;
import com.NE.demo.productManagement.Models.RegistrationModel;
import com.NE.demo.productManagement.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class CustomerService {
    private final EncryptionService encryptionService;
    private JWTService jwtService;


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepository, EncryptionService encryptionService,JWTService jwtService) {
        this.customerRepository = customerRepository;
        this.encryptionService = encryptionService;
        this.jwtService = jwtService;

    }

    public String loginUser(LoginModel loginModel) {

        Optional<Customer> opUser = customerRepository.findByEmailIgnoreCase(loginModel.getEmail());

        if (opUser.isPresent()) {
            Customer user = opUser.get();

            if (encryptionService.verifyPassword(loginModel.getPassword(), user.getPassword())) {
                return jwtService.generateJWT(user);
            }
        }

        return null;

    }


    public boolean isEmailAlreadyRegistered(String email) {
        // Check if the email is already registered in the database
        Optional<Customer> existingCustomer = customerRepository.findByEmailIgnoreCase(email);

        // If a customer with the specified email exists, return true (email is already registered)
        if (existingCustomer.isPresent()) {
            return true;
        } else {
            // If no customer with the specified email exists, return false (email is not registered)
            return false;
        }
    }

    public Customer registerUser( RegistrationModel registrationModel)  throws UserExistsException {

        if(customerRepository.findByEmailIgnoreCase(registrationModel.getEmail()).isPresent()){
            throw new UserExistsException();
        };
        Customer user= new Customer();
        user.setFirstName(registrationModel.getFirstname());
        user.setEmail(registrationModel.getEmail());
        user.setTelephone(Long.valueOf(registrationModel.getPhone()));
        user.setPassword(encryptionService.encryptPassword(registrationModel.getPassword()));

        return customerRepository.save(user);

    }
}
