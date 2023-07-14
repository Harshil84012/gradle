package com.sungjun.responseDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseLogDto {
    @JsonProperty(value = "headers")
    private Object headers;
    @JsonProperty(value = "body")
    private BodyDto body;
    @JsonProperty(value = "statusCode")
    private String statusCode;
    @JsonProperty(value = "statusCodeValue")
    private String statusCodeValue;
}
