package benedicto.learnspring.service;

import benedicto.learnspring.dto.request.MobilPatchDto;
import org.springframework.http.HttpStatus;
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

//    public MobilResponseDto updateMobil(Long id, MobilPatchDto request) {
//        for (int i = 0; i < daftarMobil.size(); i++) {
//            MobilResponseDto mobilLama = daftarMobil.get(i);
//
//            if (mobilLama.id().equals(id)) {
//                MobilResponseDto mobilBaru = new MobilResponseDto (
//                        id,
//                        request.merk(),
//                        request.tipe(),
//                        "Data mobil berhasil diupdate (PUT)"
//                );
//
//                daftarMobil.set(i, mobilBaru);
//                return mobilBaru;
//            }
//        }
//        return null;
//    }

    public MobilResponseDto patchMobil (Long id, MobilPatchDto request) {
        for (int i = 0; i < daftarMobil.size(); i++) {
            MobilResponseDto mobilLama = daftarMobil.get(i);
            if (mobilLama.id().equals(id)) {
                String merkBaru = (request.merk() != null && !request.merk().isBlank()) ? request.merk() : mobilLama.merk();
                String tipeBaru = (request.tipe() != null && !request.tipe().isBlank()) ? request.tipe() : mobilLama.tipe();

                MobilResponseDto mobilBaru = new MobilResponseDto(
                        id,
                        merkBaru,
                        tipeBaru,
                        "Data mobil berhasil diperbarui sebagian (Patch)"
                );
                daftarMobil.set(i, mobilBaru);
                return mobilBaru;
            }
        }
        return null;
    }

    public String deleteMobil (Long id) {
        boolean terhapus = daftarMobil.removeIf(mobil -> mobil.id().equals(id));
        if(terhapus) {
            return "Mobil dengan ID " + id + " berhasil dihapus";
        }
        return "Mobil dengan ID" + id + " tidak ditemukan";
    }
}