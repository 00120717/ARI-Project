package com.uca.controller;

import com.uca.domain.Cliente;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
public class clienteController {

    @PostMapping("cliente")
    public Cliente login(@RequestParam("documento") String documento, @RequestParam("primer_nombre") String primer_nombre, @RequestParam("apellido") String apellido
            , @RequestParam("credit_card") String credit_card, @RequestParam("tipo") String tipo, @RequestParam("telefono") String telefono) {

        Cliente cliente = new Cliente();
        cliente.setDocumento(documento);
        cliente.setPrimer_nombre(primer_nombre);
        cliente.setApellido(apellido);
        cliente.setCredit_card(credit_card);
        cliente.setTipo(tipo);
        cliente.setTelefono(telefono);

        String token = generateToken(cliente);
        //System.out.println(token);
        cliente.setToken(token);
        //System.out.println(cliente.toString());
        return cliente;

    }

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(Cliente cliente) {
        String encodedString = Base64.getEncoder().encodeToString(secret.getBytes());
        Claims claims = Jwts.claims().setSubject(cliente.getDocumento());
        claims.put("primer_nombre", cliente.getPrimer_nombre() + "");
        claims.put("apellido", cliente.getApellido());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, encodedString)
                .compact();
    }

}
