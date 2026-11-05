package benedicto.learnspring.service.impl;

import benedicto.learnspring.dto.request.RekeningRequestDto;
import benedicto.learnspring.dto.response.RekeningResponseDto;
import benedicto.learnspring.model.BankModel;
import benedicto.learnspring.model.NasabahModel;
import benedicto.learnspring.model.RekeningModel;
import benedicto.learnspring.repository.BankRepository;
import benedicto.learnspring.repository.NasabahRepository;
import benedicto.learnspring.repository.RekeningRepository;
import benedicto.learnspring.service.RekeningService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RekeningServiceImpl implements RekeningService {
    private final RekeningRepository rekeningRepository;
    private final BankRepository bankRepository;
    private final NasabahRepository nasabahRepository;

    public RekeningServiceImpl (
            RekeningRepository rekeningRepository,
            BankRepository bankRepository,
            NasabahRepository nasabahRepository
    ) {
        this.rekeningRepository = rekeningRepository;
        this.bankRepository = bankRepository;
        this.nasabahRepository = nasabahRepository;
    }


    //membuat rekening baru
    @Override
    @Transactional
    public RekeningResponseDto createRekening (RekeningRequestDto request) {
        //cek apakah nomor rekening sudah dipakai atau belum
        if (rekeningRepository.existsByNoRekening(request.getNoRekening())) {
            throw new IllegalArgumentException("Nomor rekening " + request.getNoRekening() + " sudah terdaftar!");
        }

        //cari objek nasabah di database
        NasabahModel nasabah = nasabahRepository.findById(request.getIdNasabah())
                .orElseThrow(() -> new NoSuchElementException("Nasabah dengan IO " + request.getIdNasabah() + " tidak ditemukan"));

        //cari objek bank di database
        BankModel bank = bankRepository.findById(request.getIdBank())
                .orElseThrow(() -> new NoSuchElementException("Bank dengan ID " + request.getIdBank() + " tidak ditemukan"));

        //rakit model RekeningModel dengan objek relasi nasabah dan bank
        RekeningModel rekeningBaru = RekeningModel.builder()
                .noRekening(request.getNoRekening())
                .nasabah(nasabah)
                .bank(bank)
                .build();

        RekeningModel saved = rekeningRepository.save(rekeningBaru);

        return mapToResponseDto(saved);
    }

    @Override
    public List<RekeningResponseDto> getAllRekening() {
        return rekeningRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    private RekeningResponseDto mapToResponseDto(RekeningModel rekening) {
        return RekeningResponseDto.builder()
                .id(rekening.getId())
                .noRekening(rekening.getNoRekening())
                .idNasabah(rekening.getNasabah().getId())
                .idBank(rekening.getBank().getId())
                .build();
    }

}
