package benedicto.learnspring.controller;

import benedicto.learnspring.service.MobilService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mobilgeral")
public class MobilController {
    private final MobilService mobilService;

    public MobilController(MobilService mobilService) {
        this.mobilService = mobilService;
    }

    @GetMapping("/status")
    public String getStatusMobil() {
        return mobilService.jalankanMobil();
    }

}
