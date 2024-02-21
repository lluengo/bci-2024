package com.lluengo.bci.entity.mapper;

import com.lluengo.bci.entity.Phone;
import com.lluengo.bci.entity.Role;
import com.lluengo.bci.entity.User;
import com.lluengo.bci.entity.dto.auth.RegisterRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RegisterRequestToUserMapper {

    @Autowired
    PasswordEncoder passwordEncoder;

    public User toUser (RegisterRequestDto registerRequestDto){

        List<Phone> phoneList = registerRequestDto.getPhones().stream()
                .map(phoneDto -> {
                    Phone phone = new Phone();
                    phone.setId(phoneDto.getId());
                    phone.setNumber(phoneDto.getNumber());
                    phone.setCityCode(phoneDto.getCityCode());
                    phone.setCountryCode(phoneDto.getCountryCode());
                    return phone;
                })
                .collect(Collectors.toList());

        User user = User.builder()
                .username(registerRequestDto.getEmail())
                .password(passwordEncoder.encode( registerRequestDto.getPassword()))
                .modified(new Date())
                .created(new Date())
                .phones(phoneList)
                .role(Role.USER)
                .build();

        return user;
    }

}
