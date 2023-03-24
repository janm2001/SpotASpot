package com.codeninjas.spotaspot.auth.controller.dto;

import com.codeninjas.spotaspot.users.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstName;
    private String lastName;
    private String email;
    private RoleRequest role;
    private String username;
    private String password;

    public RegisterRequest(User user) {
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
        role = RoleRequest.valueOf(user.getRole().toString());
        username = user.getUsername();
        password = user.getPassword();
    }
}
