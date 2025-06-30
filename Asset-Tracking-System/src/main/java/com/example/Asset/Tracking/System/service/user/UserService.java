package com.example.Asset.Tracking.System.service.user;

import com.example.Asset.Tracking.System.exceptions.ResourceNotFound;
import com.example.Asset.Tracking.System.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.example.Asset.Tracking.System.entity.User;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    private final UserRepository userRepository;

    @Override
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByUserName(email).orElseThrow(()->new ResourceNotFound("user Not found"));
        return user;
    }
}
