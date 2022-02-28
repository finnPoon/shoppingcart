package com.lendlease.web.Shop.Service;

import com.lendlease.web.Shop.Models.User;
import com.lendlease.web.Shop.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepo;

    public User findUserByUsername(String username){
        User user = userRepo.findByUsername(username);
        return user;
    }

    public void registerUser(User user){
        encodePassword(user);
        /*user.setCreatedTime(new Date());*/

        userRepo.save(user);
    }

    public void createNewUser(String username){
        User user = new User();
        user.setUsername(username);
        /*user.setCreatedTime(new Date());*/

        userRepo.save(user);
    }

    public void updateUser(User user){
        User existingUser = userRepo.findById(user.getId()).get();
        if(user.getPassword().isEmpty()){
            user.setPassword(existingUser.getPassword());
        }
        else{
            encodePassword(user);
        }

        userRepo.save(user);
    }

    private void encodePassword(User user){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    public boolean isUsernameUnique(String username){
        User existingUser = userRepo.findByUsername(username);
        return existingUser == null;
    }
}
