package com.jaewon.sideproject1.service;

import com.jaewon.sideproject1.domain.user.User;
import com.jaewon.sideproject1.domain.user.UserConfirm;
import com.jaewon.sideproject1.domain.user.UserRepository;
import com.jaewon.sideproject1.dto.user.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public boolean create(UserRequestDto requestDto) {
        if (userRepository.findByAccount(requestDto.getAccount()).isPresent()) {
            return false;
        } else {
            userRepository.save(requestDto.toEntity());

            return true;
        }
    }

    public UserConfirm userConfirm(UserRequestDto requestDto) {
        User user;

        if (userRepository.findByAccount(requestDto.getAccount()).isPresent()) {
            user = userRepository.findByAccount(requestDto.getAccount()).get();

            if (user.getPassword().equals(requestDto.getPassword())) {
                return UserConfirm.USER_CORRECT;
            } else {
                return UserConfirm.PASSWORD_WRONG;
            }
        } else {
            return UserConfirm.ACCOUNT_WRONG;
        }
    }
}
