package br.com.section4.rest.controller;

import br.com.section4.domain.entity.Usuario;
import br.com.section4.exception.SenhaInvalidaException;
import br.com.section4.rest.dto.CredenciaisDTO;
import br.com.section4.rest.dto.TokenDTO;
import br.com.section4.security.jwt.JwtService;
import br.com.section4.service.impl.UsuarioServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.xml.ws.Response;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private UsuarioServiceImpl usuarioService;

    private JwtService jwtService;

    public UsuarioController(UsuarioServiceImpl usuarioService, JwtService jwtService) {
        this.usuarioService = usuarioService;
        this.jwtService = jwtService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody @Valid Usuario usuario) {
        return usuarioService.salvar(usuario);
    }
    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais){
        try {
            Usuario usuario = new Usuario();
            usuario.setUsername(credenciais.getLogin());
            usuario.setSenha(credenciais.getSenha());
            UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);
            String token = jwtService.gerarToken(usuario);
            return new TokenDTO(usuario.getUsername(), token);
        }catch (UsernameNotFoundException  | SenhaInvalidaException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
