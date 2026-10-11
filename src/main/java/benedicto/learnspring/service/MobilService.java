package benedicto.learnspring.service;

import org.springframework.stereotype.Service;
import benedicto.learnspring.dto.request.MobilRequestDto;
import benedicto.learnspring.dto.response.MobilResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MobilService {
    private final List<MobilResponseDto> daftarMobil = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public MobilService() {
        daftarMobil.add(new MobilResponseDto(idCounter.getAndIncrement(), "Toyota", "Avanza", "Siap jalan"));
    }

    public List<MobilResponseDto> getAllMobil(){
        return daftarMobil;
    }

    public MobilResponseDto tambahMobil(MobilRequestDto request) {
        MobilResponseDto response = new MobilResponseDto(
                idCounter.getAndIncrement(),
                request.merk(),
                request.tipe(),
                "Mobil berhasil mendaftar dan siap jalan"
        );

        daftarMobil.add(response);
        return response;
    }
}