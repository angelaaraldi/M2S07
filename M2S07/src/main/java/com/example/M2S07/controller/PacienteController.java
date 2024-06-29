package com.example.M2S07.controller;

import com.example.M2S07.controller.dto.PacienteRequestDTO;
import com.example.M2S07.controller.dto.PacienteResponseDTO;
import com.example.M2S07.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
@RequiredArgsConstructor
public class PacienteController {
    private final PacienteService pacienteService;
    @GetMapping
    public ResponseEntity<List<PacienteResponseDTO>> getPacientes() {
        List<PacienteResponseDTO> response = pacienteService.buscarPacientes();
        return ResponseEntity.ok(response);
    }
    @PostMapping
    public ResponseEntity<PacienteResponseDTO> postPaciente(@RequestBody PacienteRequestDTO request) {
        PacienteResponseDTO response = pacienteService.salvarPaciente(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> putPaciente(@PathVariable Long id, @RequestBody PacienteRequestDTO request) {
        PacienteResponseDTO response = pacienteService.atualizarPaciente(id, request);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> deletePaciente(@PathVariable Long id) {
        PacienteResponseDTO response = pacienteService.deletarPaciente(id);
        return ResponseEntity.ok(response);
    }
}
