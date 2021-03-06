package com.lendlease.web.Shop.Service;

import com.lendlease.web.Shop.Models.User;
import com.lendlease.web.Shop.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException {

        User user=userRepo.findByUsername(username);

//		System.out.println("inside impl "+user);

        if(user == null) {
            System.out.println("exception thrown");
            throw new UsernameNotFoundException(username + "not found");
        }
        return new MyUserDetails(user);
    }
}
