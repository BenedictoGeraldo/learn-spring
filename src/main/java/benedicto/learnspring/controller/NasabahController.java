package benedicto.learnspring.controller;

import benedicto.learnspring.dto.request.NasabahRequestDto;
import benedicto.learnspring.dto.response.NasabahResponseDto;
import benedicto.learnspring.service.NasabahService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nasabah")
public class NasabahController {

    private final NasabahService nasabahService;

    public NasabahController (NasabahService nasabahService) {
        this.nasabahService = nasabahService;
    }

    @GetMapping
    public List<NasabahResponseDto> getAllNasabah() {
        return nasabahService.getAllNasabah();
    }

    @PostMapping
    public ResponseEntity<NasabahResponseDto> createNasbah(@Valid @RequestBody NasabahRequestDto request) {
        NasabahResponseDto response = nasabahService.createNasabah(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
