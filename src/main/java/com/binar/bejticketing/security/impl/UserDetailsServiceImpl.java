package com.binar.bejticketing.security.impl;

import com.binar.bejticketing.entity.User;
import com.binar.bejticketing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String displayName) throws UsernameNotFoundException {
        User user = userRepository.findByDisplayName(displayName);
        return UserDetailsImpl.build(user);
    }

}