package com.example.e_commerce.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<OrderDto> orders;
    private CartDto cart;
}
