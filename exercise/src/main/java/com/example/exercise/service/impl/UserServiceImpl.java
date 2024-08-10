package com.example.exercise.service.impl;

import com.example.exercise.dto.request.UserRequestDto;
import com.example.exercise.dto.response.UserResponseDto;
import com.example.exercise.entity.Phone;
import com.example.exercise.entity.User;
import com.example.exercise.repository.PhoneRepository;
import com.example.exercise.repository.UserRepository;
import com.example.exercise.service.UserService;
import org.springframework.stereotype.Service;

import java.time.ZoneId;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;

    public UserServiceImpl(UserRepository userRepository, PhoneRepository phoneRepository) {
        this.userRepository = userRepository;
        this.phoneRepository = phoneRepository;
    }

    //TODO: refactorizar
    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = new UserResponseDto();
        User user = new User();
        Phone phone = new Phone();

        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());

        // Saver user
        User savedUser = userRepository.save(user);

        userRequestDto.getPhones().forEach(p -> {
            phone.setNumber(String.valueOf(p.getNumber()));
            phone.setCityCode(p.getCityCode());
            phone.setCountryCode(p.getCountryCode());
            phone.setUser(savedUser);


        });

        Phone savedPhone = phoneRepository.save(phone);

        userResponseDto.setId(savedUser.getId());
        userResponseDto.setCreated(savedUser.getCreatedAt().atZone(ZoneId.systemDefault()).toLocalDateTime());
        userResponseDto.setModified(savedUser.getUpdatedAt().atZone(ZoneId.systemDefault()).toLocalDateTime());
        userResponseDto.setLastLogin(null); // TODO: se podria agregar tabla nueva
        userResponseDto.setToken(null); //TODO: se podria agregar tabla nueva en base a token
        userResponseDto.setActive(true); // TODO: se podria agregar tabla nueva

        return userResponseDto;
    }

}
