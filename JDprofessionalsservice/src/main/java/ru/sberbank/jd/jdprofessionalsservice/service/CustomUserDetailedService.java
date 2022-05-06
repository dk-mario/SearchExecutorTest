package ru.sberbank.jd.jdprofessionalsservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.sberbank.jd.jdprofessionalsservice.model.UsersEntity;
import ru.sberbank.jd.jdprofessionalsservice.repository.UsersRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Component
public class CustomUserDetailedService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Autowired
    public CustomUserDetailedService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UsersEntity user = usersRepository.getById(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("Invalid user name");
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        return new User(user.getLogin(),user.getPassword(), authorities);
    }

    public String getInitials(String login){
        UsersEntity user = usersRepository.getById(login);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("Invalid user name");
        }
        return user.getLastName() + " " + user.getFirstName();
    }

    public UsersEntity saveUser(UsersEntity user){
        return usersRepository.save(user);
    }
}