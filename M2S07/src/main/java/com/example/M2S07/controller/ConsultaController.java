package com.example.M2S07.controller;

import com.example.M2S07.controller.dto.ConsultaRequestDTO;
import com.example.M2S07.controller.dto.ConsultaResponseDTO;
import com.example.M2S07.service.ConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consulta")
@RequiredArgsConstructor
public class ConsultaController {
    private final ConsultaService consultaService;
    @GetMapping
    public ResponseEntity<List<ConsultaResponseDTO>> getConsultas() {
        List<ConsultaResponseDTO> response = consultaService.buscarConsultas();
        return ResponseEntity.ok(response);
    }
    @PostMapping
    public ResponseEntity<ConsultaResponseDTO> postConsulta(@RequestBody ConsultaRequestDTO request) {
        ConsultaResponseDTO response = consultaService.salvarConsulta(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ConsultaResponseDTO> putConsulta(@PathVariable Long id, @RequestBody ConsultaRequestDTO request) {
        ConsultaResponseDTO response = consultaService.atualizarConsulta(id, request);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ConsultaResponseDTO> deleteConsulta(@PathVariable Long id) {
        ConsultaResponseDTO response = consultaService.deletarConsulta(id);
        return ResponseEntity.ok(response);
    }
}
