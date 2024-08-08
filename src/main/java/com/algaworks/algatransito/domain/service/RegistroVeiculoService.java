package com.algaworks.algatransito.domain.service;

import com.algaworks.algatransito.domain.exception.VeiculoException;
import com.algaworks.algatransito.domain.model.StatusVeiculo;
import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.repository.VeiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class RegistroVeiculoService {

    public final VeiculoRepository veiculoRepository;

    @Transactional
    public Veiculo salvar (Veiculo veiculo) {
        boolean placaEmUso = veiculoRepository.findByPlaca(veiculo.getPlaca()).filter(v -> !v.equals(veiculo)).isPresent();

        if (placaEmUso) {
            throw new VeiculoException("Já existe um veículo cadastrado com esta placa");
        }

        veiculo.setStatus(StatusVeiculo.REGULAR);
        veiculo.setDataCadastro(LocalDateTime.now());

        return veiculoRepository.save(veiculo);
    }
}
