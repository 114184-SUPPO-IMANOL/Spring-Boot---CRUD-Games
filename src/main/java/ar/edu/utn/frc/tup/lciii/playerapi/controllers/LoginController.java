package ar.edu.utn.frc.tup.lciii.playerapi.controllers;


import ar.edu.utn.frc.tup.lciii.playerapi.dtos.common.ErrorApi;
import ar.edu.utn.frc.tup.lciii.playerapi.dtos.login.*;
import ar.edu.utn.frc.tup.lciii.playerapi.models.Player;
import ar.edu.utn.frc.tup.lciii.playerapi.services.LoginService;
import ar.edu.utn.frc.tup.lciii.playerapi.services.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.Access;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Operation(
            summary = "Login a player in the platform",
            description = "Return the player logged in if the credentials are correct."
    )
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content =
            @Content(schema = @Schema(implementation = Player.class))),
            @ApiResponse(responseCode = "400", description = "Username or email already exists", content =
            @Content(schema = @Schema(implementation = ErrorApi.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content =
            @Content(schema = @Schema(implementation = ErrorApi.class)))
    })
    @PostMapping("")
    public ResponseEntity<Player> loginPlayer(@RequestBody @Valid Credential credential){
        return ResponseEntity.ok(loginService.login(credential));
    }

    @Operation(
            summary = "Login a player in the platform",
            description = "Return the player logged in if the credentials are correct."
    )
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content =
            @Content(schema = @Schema(implementation = Player.class))),
            @ApiResponse(responseCode = "400", description = "Username or email already exists", content =
            @Content(schema = @Schema(implementation = ErrorApi.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content =
            @Content(schema = @Schema(implementation = ErrorApi.class)))
    })

    @PostMapping("/v2")
    public ResponseEntity<Player> loginPlayer(@RequestBody @Valid CredentialV2 credential){
        return ResponseEntity.ok(loginService.login(credential));
    }
}
