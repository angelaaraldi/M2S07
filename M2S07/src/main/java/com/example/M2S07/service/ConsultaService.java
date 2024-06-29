package com.example.M2S07.service;

import com.example.M2S07.controller.dto.ConsultaRequestDTO;
import com.example.M2S07.controller.dto.ConsultaResponseDTO;
import com.example.M2S07.entity.ConsultaEntity;
import com.example.M2S07.repository.ConsultaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultaService {
    private final ConsultaRepository repository;
    public List<ConsultaResponseDTO> buscarConsultas() {
        return repository.findAll().stream().map(consultaEntity -> new ConsultaResponseDTO(consultaEntity.getId(), consultaEntity.getNutricionista(), consultaEntity.getPaciente(), consultaEntity.getDataConsulta(), consultaEntity.getObservacoes(), consultaEntity.getHoraConsulta())).collect(Collectors.toList());
    }
    public ConsultaResponseDTO salvarConsulta(ConsultaRequestDTO request) {
        ConsultaEntity entity = new ConsultaEntity();
        entity.setNutricionista(request.getNutricionista());
        entity.setPaciente(request.getPaciente());
        entity.setDataConsulta(request.getDataConsulta());
        entity.setObservacoes(request.getObservacoes());
        entity.setHoraConsulta(request.getHoraConsulta());
        ConsultaEntity entitySalva = repository.save(entity);
        return new ConsultaResponseDTO(entitySalva.getId(), entitySalva.getNutricionista(), entitySalva.getPaciente(), entitySalva.getDataConsulta(), entitySalva.getObservacoes(), entitySalva.getHoraConsulta());
    }
    public ConsultaResponseDTO atualizarConsulta(Long id, ConsultaRequestDTO request) {
        ConsultaEntity consulta = repository.findById(id).orElse(null);
        if (consulta != null) {
            consulta.setNutricionista(request.getNutricionista());
            consulta.setPaciente(request.getPaciente());
            consulta.setDataConsulta(request.getDataConsulta());
            consulta.setObservacoes(request.getObservacoes());
            consulta.setHoraConsulta(request.getHoraConsulta());
            ConsultaEntity consultaAtualizada = repository.save(consulta);
            return new ConsultaResponseDTO(consultaAtualizada.getId(), consultaAtualizada.getNutricionista(), consultaAtualizada.getPaciente(), consultaAtualizada.getDataConsulta(), consultaAtualizada.getObservacoes(), consultaAtualizada.getHoraConsulta());
        }
        return null;
    }
    public ConsultaResponseDTO deletarConsulta(Long id) {
        ConsultaEntity consulta = repository.findById(id).orElse(null);
        if (consulta != null) {
            repository.delete(consulta);
            return new ConsultaResponseDTO(consulta.getId(), consulta.getNutricionista(), consulta.getPaciente(), consulta.getDataConsulta(), consulta.getObservacoes(), consulta.getHoraConsulta());
        }
        return null;
    }
    public String listarConsultas() {
        List<ConsultaEntity> lista = repository.findAll();
        StringBuilder stringLista = new StringBuilder();
        for (ConsultaEntity consulta : lista) {
            stringLista.append("Data/hora: ").append(consulta.getDataConsulta()).append("/").append(consulta.getHoraConsulta()).append(" - Nutricionista: ").append(consulta.getNutricionista().getNome()).append(" - Paciente: ").append(consulta.getPaciente().getNome()).append("\n");
        }
        return stringLista.toString();
    }
}
