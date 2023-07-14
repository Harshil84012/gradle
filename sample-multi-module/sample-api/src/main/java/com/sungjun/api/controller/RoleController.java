package com.sungjun.api.controller;


import com.sungjun.api.enums.ApiResponsesEnum;
import com.sungjun.api.service.RoleService;
import com.sungjun.requestDto.RoleRequestDto;
import com.sungjun.responseDto.ApiResponse;
import com.sungjun.responseDto.RoleResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;


    @PostMapping("/createRole")
    public ResponseEntity<ApiResponse> createRole(@RequestBody RoleRequestDto roleRequestDto) {

        RoleResponseDto responseDto = roleService.createRole(roleRequestDto);

        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, ApiResponsesEnum.ROLE_CREATED_SUCCESSFULLY.getValue(), responseDto), HttpStatus.OK);
    }

    @GetMapping("/getRole")
    public ResponseEntity<ApiResponse> getRole(@RequestParam(value = "pageNo", required = false, defaultValue = "0") Integer pageNo,
                                               @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                               @RequestParam(value = "searchValue", required = false, defaultValue = "") String searchValue,
                                               @RequestParam(value = "sortBy", required = false, defaultValue = "Id") String sortBy,
                                               @RequestParam(value = "sortAs", required = false, defaultValue = "ASC") Sort.Direction sortAs) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortAs, sortBy));
        Page<RoleResponseDto> responseDto = roleService.getRole(searchValue, pageable);

        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, ApiResponsesEnum.ROLE_FETCHED_SUCCESSFULLY.getValue(), responseDto), HttpStatus.OK);
    }
}
