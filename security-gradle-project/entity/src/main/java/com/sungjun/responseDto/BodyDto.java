package com.sungjun.responseDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BodyDto {
    @JsonProperty(value = "message")

    private String message;
    @JsonProperty(value = "data")

    private Object data;
    @JsonProperty(value = "status")

    private String status;
}
