package com.example.M2S07.controller;

import com.example.M2S07.controller.dto.EnderecoRequestDTO;
import com.example.M2S07.controller.dto.EnderecoResponseDTO;
import com.example.M2S07.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/endereco")
@RequiredArgsConstructor
public class EnderecoController {
    private final EnderecoService enderecoService;
    @GetMapping
    public ResponseEntity<List<EnderecoResponseDTO>> getEnderecos() {
        List<EnderecoResponseDTO> response = enderecoService.buscarEnderecos();
        return ResponseEntity.ok(response);
    }
    @PostMapping
    public ResponseEntity<EnderecoResponseDTO> postEndereco(@RequestBody EnderecoRequestDTO request) {
        EnderecoResponseDTO response = enderecoService.salvarEndereco(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<EnderecoResponseDTO> putEndereco(@PathVariable Long id, @RequestBody EnderecoRequestDTO request) {
        EnderecoResponseDTO response = enderecoService.atualizarEndereco(id, request);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<EnderecoResponseDTO> deleteEndereco(@PathVariable Long id) {
        EnderecoResponseDTO response = enderecoService.deletarEndereco(id);
        return ResponseEntity.ok(response);
    }
}
