package com.example.securityjwt.service;

import com.example.securityjwt.common.exception.CustomException;
import com.example.securityjwt.common.exception.ServerExceptionCode;
import com.example.securityjwt.controller.dto.UserDto;
import com.example.securityjwt.model.User;
import com.example.securityjwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserDto getUserById(Long id) {
        // todo if(loginUser.id.equals(id).then throw!) 가 먼저되어야 함
        User user = userRepository.findById(id)
            .orElseThrow(() -> new CustomException(ServerExceptionCode.USER_NOT_FOUND));

        return new UserDto(user);
    }
}
