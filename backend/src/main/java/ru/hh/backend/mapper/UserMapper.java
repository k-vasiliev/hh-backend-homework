package ru.hh.backend.mapper;

import ru.hh.backend.dto.UserDtoRequest;
import ru.hh.backend.dto.UserDtoResponse;
import ru.hh.backend.entity.User;

import javax.inject.Singleton;
import java.time.LocalDate;

@Singleton
public class UserMapper {
    public UserDtoResponse map(User user) {
        return new UserDtoResponse(user.getUserId(), user.getName());
    }

    public User map(UserDtoRequest userDtoRequest) {
        return new User(userDtoRequest.getName(), userDtoRequest.getType().toLowerCase(), LocalDate.now());
    }
}
