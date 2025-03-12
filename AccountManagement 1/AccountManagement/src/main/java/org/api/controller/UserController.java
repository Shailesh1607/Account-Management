package org.api.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.api.dto.UserDTO;
import org.api.exception.NoDataFoundException;
import org.api.response.ApiResponse;
import org.api.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@Api(value = "Users", description = "User Operations")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    @ApiOperation(value = "Get User by User Id", notes = "Retrieve user details")
    public ResponseEntity<ApiResponse<UserDTO>> getUserDetails(@PathVariable Long userId) {
        UserDTO user = userService.getUserbyUserId(userId)
                .orElseThrow(() -> new NoDataFoundException("User not found"));
        return ResponseEntity.ok(new ApiResponse<>("SUCCESS", "User fetched successfully", user));
    }
}

