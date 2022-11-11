package com.empresax.security.persistence.config;

import org.springframework.stereotype.Component;

import com.empresax.security.domain.User;
import com.empresax.security.persistence.entity.UserEntity;

@Component
public class Mapper {
    
    public static UserEntity toUserEntity(User user) {
        return new UserEntity(
                user.getId_user(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getFist_name(),
                user.getLast_name(),
                user.getPhone_number(),
                null,
                null,
                null);
    }

    public static User toUser(UserEntity user) {
        return new User(
                user.getId_user(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getFist_name(),
                user.getLast_name(),
                user.getPhone_number());
    }

}
