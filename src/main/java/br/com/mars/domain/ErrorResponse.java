package br.com.mars.domain;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class ErrorResponse implements Serializable {

    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }
}