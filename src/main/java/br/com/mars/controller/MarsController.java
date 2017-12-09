package br.com.mars.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mars.validation.ValidateCommands;

@RestController
@RequestMapping("/mars")
@Validated
public class MarsController {

    @PostMapping("/{commands}")
    public ResponseEntity<String> move(@PathVariable("commands") @ValidateCommands String commands) {
        return ResponseEntity.ok("");
    }
}