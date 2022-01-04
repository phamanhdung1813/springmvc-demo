package com.anhdungpham.service.imple;

import com.anhdungpham.constant.SystemConstant;
import com.anhdungpham.dto.MyUser;
import com.anhdungpham.entity.RoleEntity;
import com.anhdungpham.entity.UserEntity;
import com.anhdungpham.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Authentication when login and maintain user information
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findOneByUserNameAndStatus(username, SystemConstant.ACTIVE_STATUS);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User Not Found, try again !!");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (RoleEntity role : userEntity.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getCode()));
        }

        // Put user information into the spring security to maintain system when user login.
        MyUser myUser = new MyUser(userEntity.getUserName(), userEntity.getPassword(), true, true,
                true, true, authorities);
        myUser.setFullName(userEntity.getFullName());
        return myUser;
    }
}
