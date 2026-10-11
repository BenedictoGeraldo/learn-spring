package benedicto.learnspring.controller;

import benedicto.learnspring.dto.request.MobilPatchDto;
import benedicto.learnspring.dto.response.MobilResponseDto;
import benedicto.learnspring.dto.request.MobilRequestDto;
import benedicto.learnspring.service.MobilService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mobilgeral")
public class MobilController {
    private final MobilService mobilService;

    public MobilController(MobilService mobilService) {
        this.mobilService = mobilService;
    }

    @GetMapping
    public List<MobilResponseDto> getAllMobil() {
        return mobilService.getAllMobil();
    }

    @PostMapping
    public MobilResponseDto tambahMobil(@RequestBody MobilRequestDto request) {
        return mobilService.tambahMobil(request);
    }

    @PatchMapping("/{id}")
    public MobilResponseDto patchMobil (
            @PathVariable Long id,
            @RequestBody MobilPatchDto request) {
        return mobilService.patchMobil(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteMobil(@PathVariable Long id) {
        return mobilService.deleteMobil(id);
    }
}
