package com.app.user.mapper;

import com.app.user.domain.User;
import com.app.user.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName());
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName());
    }

    public List<User> mapToUserList(final List<UserDto> userDtoList) {
        return userDtoList.stream()
                .map(userDto -> new User(userDto.getId(), userDto.getFirstName(), userDto.getLastName()))
                .collect(Collectors.toList());
    }

    public List<UserDto> mapToUserDtoList(final List<User> userList) {
        return userList.stream()
                .map(user -> new UserDto(user.getId(), user.getFirstName(), user.getLastName()))
                .collect(Collectors.toList());
    }
}
