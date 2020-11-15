package ru.education.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class SecurityUserDetailsManager implements UserDetailsManager {
    @Override
    public void createUser(UserDetails userDetails) {

    }

    @Override
    public void updateUser(UserDetails userDetails) {

    }

    @Override
    public void deleteUser(String s) {

    }

    @Override
    public void changePassword(String s, String s1) {

    }

    @Override
    public boolean userExists(String s) {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //тут должна быть реализована логика по поиску пользователя по пришедшему логину в базе
        //должен вернуться логин, хешированный пароль и коллекция прав этого пользователя

        if (!userName.equals("user")) {
            return null;
        }

        List<SecurityPermission> permissions = new ArrayList<>();
        permissions.add(new SecurityPermission("product.read"));
        permissions.add(new SecurityPermission("product.readById"));
        permissions.add(new SecurityPermission("product.create"));
        permissions.add(new SecurityPermission("product.delete"));

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return new SecurityUser(userName, encoder.encode( "123"), permissions);
    }
}
