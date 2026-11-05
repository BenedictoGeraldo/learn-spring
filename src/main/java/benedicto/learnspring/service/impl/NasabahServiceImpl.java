package benedicto.learnspring.service.impl;

import benedicto.learnspring.dto.request.NasabahRequestDto;
import benedicto.learnspring.dto.response.NasabahResponseDto;
import benedicto.learnspring.model.NasabahModel;
import benedicto.learnspring.repository.NasabahRepository;
import benedicto.learnspring.service.NasabahService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NasabahServiceImpl implements NasabahService {

    private final NasabahRepository nasabahRepository;

    //Bikin constructor
    public NasabahServiceImpl(NasabahRepository nasabahRepository) {
        this.nasabahRepository = nasabahRepository;
    }

    //Bikin logic untuk create nasabah baru
    @Override
    @Transactional
    public NasabahResponseDto createNasabah(NasabahRequestDto request) {
        //Konversi dari request DTO ke model
        NasabahModel nasabahBaru = NasabahModel.builder()
                .nasabahName(request.getNasabahName())
                .build();

        //simpan ke database via repository
        NasabahModel saved = nasabahRepository.save(nasabahBaru);

        //kembalikan dalam bentuk response dto
        return mapToResponseDto(saved);
    }

    //mengambil seluruh data nasabah
    @Override
    @Transactional (readOnly = true)
    public List<NasabahResponseDto> getAllNasabah() {
        return nasabahRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    private NasabahResponseDto mapToResponseDto(NasabahModel nasabahModel) {
        return NasabahResponseDto.builder()
                .nasabahName(nasabahModel.getNasabahName())
                .build();
    }

}
