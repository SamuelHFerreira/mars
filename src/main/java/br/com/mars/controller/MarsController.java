package br.com.mars.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mars.domain.constant.Command;
import br.com.mars.domain.data.Position;
import br.com.mars.domain.response.PositionResponse;
import br.com.mars.service.RobotService;
import br.com.mars.validation.ValidateCommands;

@RestController
@RequestMapping("/mars")
@Validated
public class MarsController {

    @Autowired
    RobotService robotService;

    @PostMapping("/{commands}")
    public ResponseEntity<PositionResponse> move(@PathVariable("commands") @ValidateCommands String commands) {
        List<Command> commandList = Arrays.asList(commands.split("")).stream().map(s -> Command.valueOf(s)).collect(
                Collectors.toList());
        Position finalPosition = robotService.followCommands(commandList);
        return ResponseEntity.ok(new PositionResponse(finalPosition));
    }

    @GetMapping
    public ResponseEntity<PositionResponse> whereIam() {
        Position currentPosition = robotService.getCurrentPosition();
        return ResponseEntity.ok(new PositionResponse(currentPosition));
    }
}