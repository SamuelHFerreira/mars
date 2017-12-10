package br.com.mars.controller;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mars.domain.constant.Command;
import br.com.mars.domain.data.Position;
import br.com.mars.service.WalkService;
import br.com.mars.validation.ValidateCommands;

@RestController
@RequestMapping("/mars")
@Validated
public class MarsController {

    @Autowired
    WalkService walkService;

    @PostMapping("/{commands}")
    public ResponseEntity<Position> move(@PathVariable("commands") @ValidateCommands String commands) {
        return ResponseEntity.ok(walkService.followCommands(commands));
    }
}