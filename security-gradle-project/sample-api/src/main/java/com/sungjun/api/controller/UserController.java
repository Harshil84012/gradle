package com.sungjun.api.controller;


import com.sungjun.api.service.UserService;
import com.sungjun.enums.ApiResponsesEnum;
import com.sungjun.requestDto.UserRequestDto;
import com.sungjun.responseDto.ApiResponse;
import com.sungjun.responseDto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {
    @Autowired
    private  UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerNewUser(@RequestBody UserRequestDto dto) {
        userService.registerNewUser(dto);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, ApiResponsesEnum.USER_CREATED_SUCCESSFULLY.getValue(), dto), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPER')")
    @GetMapping("/getAllUser")
    public ResponseEntity<ApiResponse> getAllPrivilege(@RequestParam(value = "pageNo", required = false, defaultValue = "0") Integer pageNo,
                                                       @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                       @RequestParam(value = "searchValue", required = false, defaultValue = "") String searchValue,
                                                       @RequestParam(value = "sortBy", required = false, defaultValue = "firstName") String sortBy,
                                                       @RequestParam(value = "sortAs", required = false, defaultValue = "ASC") Sort.Direction sortAs) {

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortAs, sortBy));
        Page<UserResponseDto> responseDto = userService.getAllUsers(searchValue, pageable);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, ApiResponsesEnum.USER_FETCHED_SUCCESSFULLY.getValue(), responseDto), HttpStatus.OK);
    }


}
