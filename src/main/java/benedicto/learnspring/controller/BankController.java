package benedicto.learnspring.controller;

import benedicto.learnspring.dto.request.BankRequestDto;
import benedicto.learnspring.dto.response.BankResponseDto;
import benedicto.learnspring.service.BankService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bank")
public class BankController {

    private final BankService bankService;

    public BankController (BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping
    public List<BankResponseDto> getAllBank() {
        return bankService.getAllBank();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<BankResponseDto> createBank (@Valid @RequestBody BankRequestDto request) {
        BankResponseDto response = bankService.createBank(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
