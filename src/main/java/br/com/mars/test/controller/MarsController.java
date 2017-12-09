package br.com.mars.test.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mars")
public class MarsController {

    @PostMapping("/{command}")
    public ResponseEntity<String> move(@PathVariable("command") String command) {
        return ResponseEntity.ok("");
    }
}