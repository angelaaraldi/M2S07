package com.example.M2S07.controller;

import com.example.M2S07.controller.dto.NutricionistaRequestDTO;
import com.example.M2S07.controller.dto.NutricionistaResponseDTO;
import com.example.M2S07.service.NutricionistaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nutricionista")
@RequiredArgsConstructor
public class NutricionistaController {
    private final NutricionistaService nutricionistaService;
    @GetMapping
    public ResponseEntity<List<NutricionistaResponseDTO>> getNutricionistas() {
        List<NutricionistaResponseDTO> response = nutricionistaService.buscarNutricionistas();
        return ResponseEntity.ok(response);
    }
    @PostMapping
    public ResponseEntity<NutricionistaResponseDTO> postNutricionista(@RequestBody NutricionistaRequestDTO request) {
        NutricionistaResponseDTO response = nutricionistaService.salvarNutricionista(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<NutricionistaResponseDTO> putNutricionista(@PathVariable Long id, @RequestBody NutricionistaRequestDTO request) {
        NutricionistaResponseDTO response = nutricionistaService.atualizarNutricionista(id, request);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<NutricionistaResponseDTO> deleteNutricionista(@PathVariable Long id) {
        NutricionistaResponseDTO response = nutricionistaService.deletarNutricionista(id);
        return ResponseEntity.ok(response);
    }
}
