package com.leucotron.api.controller;

import com.leucotron.api.domain.usuario.Usuario;
import com.leucotron.api.domain.usuario.LoginDTO;
import com.leucotron.api.domain.usuario.TokenJwtDTO;
import com.leucotron.api.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid LoginDTO dto){
        var autheticationToken = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
        var authetication = manager.authenticate(autheticationToken);
        var tokenJWT = tokenService.gerarToken((Usuario) authetication.getPrincipal());

        return ResponseEntity.ok(new TokenJwtDTO(tokenJWT));
    }
}
