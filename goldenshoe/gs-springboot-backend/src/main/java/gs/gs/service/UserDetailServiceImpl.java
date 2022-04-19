package gs.gs.service;

import gs.gs.model.User;
import gs.gs.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService, UserService{

    @Autowired
    UserRepo userRepo;

    @Override
    public List<User> getAllUsers() {
        List <User> users = (List<User>) userRepo.findAll();
        return users;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username).orElseThrow( () ->
                new UsernameNotFoundException("Username not found " + username));

        return (UserDetails) user;
    }
}
