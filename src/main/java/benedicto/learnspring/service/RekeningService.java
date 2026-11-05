package benedicto.learnspring.service;

import benedicto.learnspring.dto.request.RekeningRequestDto;
import benedicto.learnspring.dto.response.RekeningResponseDto;

import java.util.List;

public interface RekeningService {

    RekeningResponseDto createRekening (RekeningRequestDto request);

    List<RekeningResponseDto> getAllRekening();

}
