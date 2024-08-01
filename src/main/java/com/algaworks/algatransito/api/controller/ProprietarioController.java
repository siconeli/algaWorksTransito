package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.domain.model.Proprietario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ProprietarioController {

    @GetMapping("/proprietarios")
    public List<Proprietario> listar() {
        var prop1 = new Proprietario();
        prop1.setId(1L);
        prop1.setNome("Henrique Siconeli");
        prop1.setEmail("henrique@gmail.com");
        prop1.setTelefone("67 99964-9982");

        var prop2 = new Proprietario();
        prop2.setId(2L);
        prop2.setNome("Benício Siconeli");
        prop2.setEmail("benicio@gmail.com");
        prop2.setTelefone("67 99131-0698");

        return Arrays.asList(prop1, prop2);

    }
}
