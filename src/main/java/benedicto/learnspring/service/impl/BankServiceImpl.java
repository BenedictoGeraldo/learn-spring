package benedicto.learnspring.service.impl;

import benedicto.learnspring.dto.request.BankRequestDto;
import benedicto.learnspring.dto.response.BankResponseDto;
import benedicto.learnspring.model.BankModel;
import benedicto.learnspring.repository.BankRepository;
import benedicto.learnspring.service.BankService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BankServiceImpl implements BankService {
    private final BankRepository bankRepository;

    public BankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    @Transactional
    public BankResponseDto createBank (BankRequestDto request) {
        BankModel bankBaru = BankModel.builder()
                .bankName(request.getBankName())
                .build();

        BankModel saved = bankRepository.save(bankBaru);

        return mapToResponseDto(saved);
    }

    @Override
    @Transactional
    public List<BankResponseDto> getAllBank() {
        return bankRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }


    private BankResponseDto mapToResponseDto(BankModel bankModel) {
        return BankResponseDto.builder()
                .bankName(bankModel.getBankName())
                .build();
    }

}
