package br.com.mars.domain.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class ErrorResponse implements Serializable {

    @JsonProperty
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }
}