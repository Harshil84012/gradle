package com.sungjun.responseDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * <h1>ApiResponse</h1>
 * <p>
 * This class will be used for ApiResponse
 * </p>
 *
 * @author finpal
 * @version 1.0
 * @since 21-09-2022
 */

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ApiResponse {

    private int status;
    private String message;
    private Object data;
    @JsonIgnore
    private HttpStatus httpStatus;

    public ApiResponse(HttpStatus httpStatus, String message, Object data) {
        this.httpStatus = httpStatus;
        this.status = httpStatus.value();
        this.message = message;
        this.data = data;
    }

    public ApiResponse(String message, Object data, int status) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
