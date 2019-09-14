package ir.demisco.cloud.auth.impl;

import ir.demisco.cloud.auth.api.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import ir.demisco.cloud.auth.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by m.jokar on 9/12/2019.
 */

@Service
public class DefaultUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userOptional=userRepository.findByUsername(username).stream().findFirst();
        if(userOptional.isPresent()){
            return userOptional.get();
        }
        throw new UsernameNotFoundException("no user found by "+ username );
    }


}
