package com.example.M2S07.service;

import com.example.M2S07.controller.dto.PacienteRequestDTO;
import com.example.M2S07.controller.dto.PacienteResponseDTO;
import com.example.M2S07.entity.PacienteEntity;
import com.example.M2S07.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PacienteService {
    private final PacienteRepository repository;
    public List<PacienteResponseDTO> buscarPacientes() {
        return repository.findAll().stream().map(pacienteEntity -> new PacienteResponseDTO(pacienteEntity.getId(), pacienteEntity.getNome(), pacienteEntity.getDataNascimento(), pacienteEntity.getCpf(), pacienteEntity.getTelefone(), pacienteEntity.getEmail(), pacienteEntity.getEndereco())).collect(Collectors.toList());
    }
    public PacienteResponseDTO salvarPaciente(PacienteRequestDTO request) {
        PacienteEntity entity = new PacienteEntity();
        entity.setNome(request.getNome());
        entity.setDataNascimento(request.getDataNascimento());
        entity.setCpf(request.getCpf());
        entity.setTelefone(request.getTelefone());
        entity.setEmail(request.getEmail());
        entity.setEndereco(request.getEndereco());
        PacienteEntity entitySalva = repository.save(entity);
        return new PacienteResponseDTO(entitySalva.getId(), entitySalva.getNome(), entitySalva.getDataNascimento(), entitySalva.getCpf(), entitySalva.getTelefone(), entitySalva.getEmail(), entitySalva.getEndereco());
    }
    public PacienteResponseDTO atualizarPaciente(Long id, PacienteRequestDTO request) {
        PacienteEntity paciente = repository.findById(id).orElse(null);
        if (paciente != null) {
            paciente.setNome(request.getNome());
            paciente.setDataNascimento(request.getDataNascimento());
            paciente.setCpf(request.getCpf());
            paciente.setTelefone(request.getTelefone());
            paciente.setEmail(request.getEmail());
            paciente.setEndereco(request.getEndereco());
            PacienteEntity pacienteAtualizado = repository.save(paciente);
            return new PacienteResponseDTO(pacienteAtualizado.getId(), pacienteAtualizado.getNome(), pacienteAtualizado.getDataNascimento(), pacienteAtualizado.getCpf(), pacienteAtualizado.getTelefone(), pacienteAtualizado.getEmail(), pacienteAtualizado.getEndereco());
        }
        return null;
    }
    public PacienteResponseDTO deletarPaciente(Long id) {
        PacienteEntity paciente = repository.findById(id).orElse(null);
        if (paciente != null) {
            repository.delete(paciente);
            return new PacienteResponseDTO(paciente.getId(), paciente.getNome(), paciente.getDataNascimento(), paciente.getCpf(), paciente.getTelefone(), paciente.getEmail(), paciente.getEndereco());
        }
        return null;
    }
}
