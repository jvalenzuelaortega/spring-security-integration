package com.example.exercise.controller;

import com.example.exercise.dto.request.UserRequestDto;
import com.example.exercise.dto.request.UserUpdateRequestDto;
import com.example.exercise.exceptions.UserOperationException;
import com.example.exercise.dto.response.ResponseBaseDto;
import com.example.exercise.service.UserOperationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Operaciones de usuario")
@RestController
@RequestMapping("/exercise/api")
public class UserController {

    private final UserOperationService userOperationService;

    public UserController(UserOperationService userOperationService) {
        this.userOperationService = userOperationService;
    }

    @Operation(
            summary = "Crea un nuevo usuario con un token JWT",
            description = "Crea un nuevo usuario con un token JWT y lo guarda en la base de datos"
    )
    @PostMapping(value = "/create-user", produces="application/json")
    public ResponseEntity<ResponseBaseDto> createUser(@RequestBody @Valid UserRequestDto userRequestDto) throws UserOperationException {
        ResponseBaseDto userDefaultResponseDto = userOperationService.createUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDefaultResponseDto);
    }

    @Operation(
            summary = "Entrega la informacion del usuario",
            description = "Obtiene la informacion de un usuario enviando un nombre y un tipo(type) de respuesta (Puede ser default o detail)",
            security = @SecurityRequirement(name = "Authorization")
    )
    @GetMapping(value="/get-user-by-name-and-type/{type}", produces = "application/json")
    public ResponseEntity<ResponseBaseDto> getUserDetailsByNameAndTypeResponse(
            @PathVariable(value = "type") String typeResponse,
            @RequestParam(value = "name") String name) throws UserOperationException {
        ResponseBaseDto userDetailResponseBaseDto = userOperationService.getUserByNameAndResponseType(name, typeResponse);
        return ResponseEntity.status(HttpStatus.OK).body(userDetailResponseBaseDto);
    }

    @Operation(
            summary = "Actualiza los datos de correo y contraseña de un usuario",
            description = "Actualiza los datos de correo y contraseña de un usuario existentes en base de datos",
            security = @SecurityRequirement(name = "Authorization")
    )
    @PutMapping("/update-email-and-password/{name}")
    public ResponseEntity<ResponseBaseDto> updateEmailAndPasswordOfUser(
            @PathVariable(value = "name") String name,
            @RequestBody @Valid UserUpdateRequestDto userRequestDto) throws UserOperationException {
        userRequestDto.setName(name);
        ResponseBaseDto userDefaultResponseDto = userOperationService.updateMailAndPasswordOfUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(userDefaultResponseDto);
    }

}
