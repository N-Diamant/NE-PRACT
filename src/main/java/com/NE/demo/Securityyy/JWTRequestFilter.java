package com.NE.demo.Securityyy;


import com.NE.demo.productManagement.Models.Customer;
import com.NE.demo.productManagement.Repositories.CustomerRepository;
import com.NE.demo.productManagement.Services.JWTService;
import com.auth0.jwt.exceptions.JWTDecodeException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    private final CustomerRepository CustomerRepository;
    private final JWTService jwtService;
    private CustomerRepository localUserDAO;

    public JWTRequestFilter(JWTService jwtService, CustomerRepository CustomerRepository){
        this.jwtService =jwtService;
        this.CustomerRepository = CustomerRepository;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{

        String tokenHeader= request.getHeader("authorization");
        if(tokenHeader !=null && tokenHeader.startsWith("Bearer ")){
            String token= tokenHeader.substring(7);
            try{
                String email =jwtService.getEmail(token);
                Optional<Customer> opUser = CustomerRepository.findByEmailIgnoreCase(email);

                if(opUser.isPresent()){
                    Customer user = opUser.get();
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

            }catch(JWTDecodeException ex){


            }
        }
        filterChain.doFilter(request,response);

    }

}
