package benedicto.learnspring.service;

import benedicto.learnspring.dto.request.MobilPatchDto;
import benedicto.learnspring.model.MobilModel;
import benedicto.learnspring.repository.MobilRepository;
import org.springframework.stereotype.Service;
import benedicto.learnspring.dto.request.MobilRequestDto;
import benedicto.learnspring.dto.response.MobilResponseDto;

import java.util.List;

@Service
public class MobilService {
    private final MobilRepository mobilRepository;

    public MobilService(MobilRepository mobilRepository) {
        this.mobilRepository = mobilRepository;
    }

    public List<MobilResponseDto> getAllMobil() {
        return mobilRepository.findAll().stream().map(m -> new MobilResponseDto(
                m.getId(),
                m.getMerk(),
                m.getTipe(),
                m.getStatus()
        )).toList();
    }

    public MobilResponseDto tambahMobil(MobilRequestDto request) {
        MobilModel mobilBaru = new MobilModel(
                request.merk(),
                request.tipe(),
                "Mobil berhasil mendaftar dan siap jalan"
        );

        MobilModel saved = mobilRepository.save(mobilBaru);

        return new MobilResponseDto(
                saved.getId(),
                saved.getMerk(),
                saved.getTipe(),
                saved.getStatus()
        );
    }

    public MobilResponseDto patchMobil(Long id, MobilPatchDto request) {
        return mobilRepository.findById(id).map(mobil -> {
            if (request.merk() != null && !request.merk().isBlank()) {
                mobil.setMerk(request.merk());
            }
            if (request.tipe() != null && !request.merk().isBlank()) {
                mobil.setTipe(request.tipe());
            }

            mobil.setStatus("Data mobil berhasil diperbarui dengan method patch");

            MobilModel updated = mobilRepository.save(mobil);

            return new MobilResponseDto(
                    updated.getId(),
                    updated.getMerk(),
                    updated.getTipe(),
                    updated.getStatus()
            );
        }).orElse(null);
    }

    public String deleteMobil(Long id) {
        if (mobilRepository.existsById(id)) {
            mobilRepository.deleteById(id);
            return "Mobil dengan ID " + id + " berhasil dihapus";
        }

        return "Mobil dengan ID" + id + " tidak ditemukan di db";
    }
}