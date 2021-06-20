package com.uca.controller;

import com.uca.domain.Cliente;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

        //String token = generateToken(cliente);
        //System.out.println(token);

        //parseToken Decrypt
        //System.out.println(parseToken(token).getDocumento());


        return cliente;

    }

    //@Value("${jwt.secret}")
    private String secret = MainController.encriptKey;


    public String generateToken(Cliente cliente) {

        Claims claims = Jwts.claims().setSubject(cliente.getDocumento());
        claims.put("primer_nombre", cliente.getPrimer_nombre() + "");
        claims.put("apellido", cliente.getApellido());
        claims.put("credit_card", cliente.getCredit_card());
        claims.put("tipo", cliente.getTipo());
        claims.put("telefono", cliente.getTelefono());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Cliente parseToken(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();


            Cliente cliente = new Cliente();
            cliente.setDocumento(body.getSubject());
            cliente.setPrimer_nombre((String) body.get("primer_nombre"));
            cliente.setApellido((String) body.get("apellido"));
            cliente.setCredit_card((String) body.get("credit_card"));
            cliente.setTipo((String) body.get("tipo"));
            cliente.setTelefono((String) body.get("telefono"));

            return cliente;

        } catch (JwtException | ClassCastException e) {
            return null;
        }
    }
}
