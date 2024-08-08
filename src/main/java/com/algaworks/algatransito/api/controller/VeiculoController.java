package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.domain.exception.VeiculoException;
import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.repository.VeiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/veiculos")
@RestController
public class VeiculoController {

    private VeiculoRepository veiculoRepository;

    @GetMapping
    public List<Veiculo> listar () {
        return veiculoRepository.findAll();
    }

    @GetMapping("/{veiculoId}")
    public ResponseEntity<Veiculo> buscar (@PathVariable Long veiculoId) {
        return veiculoRepository.findById(veiculoId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Veiculo adicionar (@RequestBody Veiculo veiculo) {
        boolean placaEmUso = veiculoRepository.findByPlaca(veiculo.getPlaca()).filter(v -> !v.equals(veiculo)).isPresent();

        if (placaEmUso) {
            throw new VeiculoException("Já existe um veículo cadastrado com esta placa");
        }

        return veiculoRepository.save(veiculo);
    }


}
