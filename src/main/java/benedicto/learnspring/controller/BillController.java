package benedicto.learnspring.controller;

import benedicto.learnspring.dto.request.CreateBillRequestDto;
import benedicto.learnspring.dto.response.BillResponseDto;
import benedicto.learnspring.service.BillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bills")
public class BillController {

    private final BillService billService;

    public BillController(BillService billService){
        this.billService = billService;
    }

    @PostMapping
    public ResponseEntity<BillResponseDto> createBill(@RequestBody CreateBillRequestDto request) {
        BillResponseDto response = billService.createBill(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BillResponseDto>> getAllBills() {
        return ResponseEntity.ok(billService.getAllBils());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillResponseDto> getBillById(@PathVariable Long id) {
        return ResponseEntity.ok(billService.getBillById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBill(@PathVariable Long id) {
        return ResponseEntity.ok(billService.deleteBill(id));
    }
}
