package benedicto.learnspring.service;

import benedicto.learnspring.dto.request.BankRequestDto;
import benedicto.learnspring.dto.response.BankResponseDto;

import java.util.List;

public interface BankService {

    BankResponseDto createBank (BankRequestDto request);

    List<BankResponseDto> getAllBank();
}
