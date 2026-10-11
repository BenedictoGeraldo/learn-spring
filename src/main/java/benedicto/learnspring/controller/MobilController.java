package benedicto.learnspring.controller;

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

    @GetMapping("/ghokghok")
    public List<MobilResponseDto> getAllMobil() {
        return mobilService.getAllMobil();
    }

    @PostMapping ("/ghokghok")
    public MobilResponseDto tambahMobil(@RequestBody MobilRequestDto request) {
        return mobilService.tambahMobil(request);
    }
}
