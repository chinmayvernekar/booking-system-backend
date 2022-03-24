package com.parkingbookingsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    ApplicationUserRepository applicationUserRepository;



    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        ApplicationUsers user = applicationUserRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User Is Not Register With Us.Please Register To Uses Our Service");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
      /*  for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }*/

        user.getRoles().forEach(role -> {
            authorities.add( new SimpleGrantedAuthority(role.getName()));
        });
        return new User(user.getEmail(), user.getPassword(), authorities);
    }
}

