package com.sungjun.responseDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * <h1>ErrorDTO</h1>
 * <p>
 * This class will be used for ErrorDTO
 * </p>
 *
 * @author finpal
 * @version 1.0
 * @since 21-09-2022
 */

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ErrorDTO {

    private Long timestamp;
    private int status;
    private Object error;
    private String message;
    private String path;

    @JsonIgnore
    private HttpStatus httpStatus;

    public ErrorDTO(HttpStatus httpStatus, Long timestamp, String message, String path) {
        this.timestamp = timestamp;
        this.httpStatus = httpStatus;
        this.status = httpStatus.value();
        this.error = httpStatus.name();
        this.message = message;
        this.path = path;
    }

    public ErrorDTO(HttpStatus httpStatus, Long timestamp, String message, String path, List<String> error) {
        this.timestamp = timestamp;
        this.httpStatus = httpStatus;
        this.status = httpStatus.value();
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
