package benedicto.learnspring.controller;

import benedicto.learnspring.dto.request.RekeningRequestDto;
import benedicto.learnspring.dto.response.RekeningResponseDto;
import benedicto.learnspring.service.RekeningService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rekening")
public class RekeningController {

    private final RekeningService rekeningService;

    public RekeningController (RekeningService rekeningService) {
        this.rekeningService = rekeningService;
    }

    @GetMapping
    public List<RekeningResponseDto> getAllRekening() {
        return rekeningService.getAllRekening();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<RekeningResponseDto> createRekening (@Valid @RequestBody RekeningRequestDto request) {
        RekeningResponseDto response  = rekeningService.createRekening(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
