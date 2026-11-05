package benedicto.learnspring.service;

import benedicto.learnspring.dto.request.NasabahRequestDto;
import benedicto.learnspring.dto.response.NasabahResponseDto;

import java.util.List;

public interface NasabahService {

    NasabahResponseDto createNasabah(NasabahRequestDto request);

    List<NasabahResponseDto> getAllNasabah();


}
