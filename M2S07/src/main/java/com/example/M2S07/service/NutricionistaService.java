package com.example.M2S07.service;

import com.example.M2S07.controller.dto.NutricionistaRequestDTO;
import com.example.M2S07.controller.dto.NutricionistaResponseDTO;
import com.example.M2S07.entity.NutricionistaEntity;
import com.example.M2S07.repository.NutricionistaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NutricionistaService {
    private final NutricionistaRepository repository;
    public List<NutricionistaResponseDTO> buscarNutricionistas() {
        return repository.findAll().stream().map(nutricionistaEntity -> new NutricionistaResponseDTO(nutricionistaEntity.getId(), nutricionistaEntity.getCrn(), nutricionistaEntity.getEspecialidade(), nutricionistaEntity.getTelefone(), nutricionistaEntity.getNome(), nutricionistaEntity.getTempoExperiencia(), nutricionistaEntity.getCertificacoes())).collect(Collectors.toList());
    }
    public List<NutricionistaResponseDTO> buscarNome(String nome) {
        return repository.findAllByNome(nome).stream().map(nutricionistaEntity -> new NutricionistaResponseDTO(nutricionistaEntity.getId(), nutricionistaEntity.getCrn(), nutricionistaEntity.getEspecialidade(), nutricionistaEntity.getTelefone(), nutricionistaEntity.getNome(), nutricionistaEntity.getTempoExperiencia(), nutricionistaEntity.getCertificacoes())).collect(Collectors.toList());
    }
    public NutricionistaResponseDTO salvarNutricionista(NutricionistaRequestDTO request) {
        NutricionistaEntity entity = new NutricionistaEntity();
        entity.setCrn(request.getCrn());
        entity.setEspecialidade(request.getEspecialidade());
        entity.setTelefone(request.getTelefone());
        if (buscarNome(request.getNome()).isEmpty()) {
            entity.setNome(request.getNome());
        }
        entity.setTempoExperiencia(request.getTempoExperiencia());
        entity.setCertificacoes(request.getCertificacoes());
        NutricionistaEntity entitySalva = repository.save(entity);
        return new NutricionistaResponseDTO(entitySalva.getId(), entitySalva.getCrn(), entitySalva.getEspecialidade(), entitySalva.getTelefone(), entitySalva.getNome(), entitySalva.getTempoExperiencia(), entitySalva.getCertificacoes());
    }
    public NutricionistaResponseDTO atualizarNutricionista(Long id, NutricionistaRequestDTO request) {
        NutricionistaEntity nutricionista = repository.findById(id).orElse(null);
        if (nutricionista != null) {
            nutricionista.setCrn(request.getCrn());
            nutricionista.setEspecialidade(request.getEspecialidade());
            nutricionista.setTelefone(request.getTelefone());
            if (buscarNome(request.getNome()).isEmpty()) {
                nutricionista.setNome(request.getNome());
            }
            nutricionista.setTempoExperiencia(request.getTempoExperiencia());
            nutricionista.setCertificacoes(request.getCertificacoes());
            NutricionistaEntity nutricionistaAtualizado = repository.save(nutricionista);
            return new NutricionistaResponseDTO(nutricionistaAtualizado.getId(), nutricionistaAtualizado.getCrn(), nutricionistaAtualizado.getEspecialidade(), nutricionistaAtualizado.getTelefone(), nutricionistaAtualizado.getNome(), nutricionistaAtualizado.getTempoExperiencia(), nutricionistaAtualizado.getCertificacoes());
        }
        return null;
    }
    public NutricionistaResponseDTO deletarNutricionista(Long id) {
        NutricionistaEntity nutricionista = repository.findById(id).orElse(null);
        if (nutricionista != null) {
            repository.delete(nutricionista);
            return new NutricionistaResponseDTO(nutricionista.getId(), nutricionista.getCrn(), nutricionista.getEspecialidade(), nutricionista.getTelefone(), nutricionista.getNome(), nutricionista.getTempoExperiencia(), nutricionista.getCertificacoes());
        }
        return null;
    }
    public void adicionarTempoExperiencia(Long id) {
        repository.findById(id).ifPresent(nutricionista -> nutricionista.setTempoExperiencia(nutricionista.getTempoExperiencia() + 1));
    }
    public void adicionarCertificacao(Long id, String certificacao) {
        repository.findById(id).ifPresent(nutricionista -> nutricionista.setCertificacoes(nutricionista.getCertificacoes() + ", " + certificacao));
    }
}
