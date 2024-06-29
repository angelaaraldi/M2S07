package com.example.M2S07.service;

import com.example.M2S07.controller.dto.EnderecoRequestDTO;
import com.example.M2S07.controller.dto.EnderecoResponseDTO;
import com.example.M2S07.entity.EnderecoEntity;
import com.example.M2S07.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnderecoService {
    private final EnderecoRepository repository;
    public List<EnderecoResponseDTO> buscarEnderecos() {
        return repository.findAll().stream().map(enderecoEntity -> new EnderecoResponseDTO(enderecoEntity.getId(), enderecoEntity.getLogradouro(), enderecoEntity.getEstado(), enderecoEntity.getCidade(), enderecoEntity.getNumero(), enderecoEntity.getCep())).collect(Collectors.toList());
    }
    public EnderecoResponseDTO salvarEndereco(EnderecoRequestDTO request) {
        EnderecoEntity entity = new EnderecoEntity();
        entity.setLogradouro(request.getLogradouro());
        entity.setEstado(request.getEstado());
        entity.setCidade(request.getCidade());
        entity.setNumero(request.getNumero());
        entity.setCep(request.getCep());
        EnderecoEntity entitySalva = repository.save(entity);
        return new EnderecoResponseDTO(entitySalva.getId(), entitySalva.getLogradouro(), entitySalva.getEstado(), entitySalva.getCidade(), entitySalva.getNumero(), entitySalva.getCep());
    }
    public EnderecoResponseDTO atualizarEndereco(Long id, EnderecoRequestDTO request) {
        EnderecoEntity endereco = repository.findById(id).orElse(null);
        if (endereco != null) {
            endereco.setLogradouro(request.getLogradouro());
            endereco.setEstado(request.getEstado());
            endereco.setCidade(request.getCidade());
            endereco.setNumero(request.getNumero());
            endereco.setCep(request.getCep());
            EnderecoEntity enderecoAtualizado = repository.save(endereco);
            return new EnderecoResponseDTO(enderecoAtualizado.getId(), enderecoAtualizado.getLogradouro(), enderecoAtualizado.getEstado(), enderecoAtualizado.getCidade(), enderecoAtualizado.getNumero(), enderecoAtualizado.getCep());
        }
        return null;
    }
    public EnderecoResponseDTO deletarEndereco(Long id) {
        EnderecoEntity endereco = repository.findById(id).orElse(null);
        if (endereco != null) {
            repository.delete(endereco);
            return new EnderecoResponseDTO(endereco.getId(), endereco.getLogradouro(), endereco.getEstado(), endereco.getCidade(), endereco.getNumero(), endereco.getCep());
        }
        return null;
    }
}
