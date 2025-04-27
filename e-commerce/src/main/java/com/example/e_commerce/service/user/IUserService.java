package com.example.e_commerce.service.user;

import com.example.e_commerce.dto.UserDto;
import com.example.e_commerce.model.User;
import com.example.e_commerce.request.CreateUserRequest;
import com.example.e_commerce.request.UserUpdateRequest;

public interface IUserService {
    User getUserById(Long id);
    User CreateUser(CreateUserRequest request);
    User updateUser(UserUpdateRequest request, Long userId);
    void deleteUser(Long userId);

    UserDto convertUserToDto(User user);

    User getAuthenticatedUser();
}
