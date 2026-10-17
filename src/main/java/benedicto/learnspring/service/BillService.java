package benedicto.learnspring.service;

import benedicto.learnspring.dto.request.BillItemRequestDto;
import benedicto.learnspring.dto.request.CreateBillRequestDto;
import benedicto.learnspring.dto.response.BillItemResponseDto;
import benedicto.learnspring.dto.response.BillResponseDto;
import benedicto.learnspring.dto.response.ParticipantResponseDto;
import benedicto.learnspring.model.BillItemModel;
import benedicto.learnspring.model.BillModel;
import benedicto.learnspring.model.ParticipantModel;
import benedicto.learnspring.repository.BillRepository;
import benedicto.learnspring.repository.ParticipantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class BillService {

    private final BillRepository billRepository;

    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Transactional
    public BillResponseDto createBill(CreateBillRequestDto request) {
        BigDecimal taxPercent = request.taxPercent() != null ? request.taxPercent() : BigDecimal.ZERO;
        BigDecimal servicePercent = request.servicePercent() != null ? request.servicePercent() : BigDecimal.ZERO;

        BillModel bill = BillModel.builder()
                .title(request.title())
                .taxPercent(taxPercent)
                .servicePercent(servicePercent)
                .build();

        Map<String, ParticipantModel> participantMap = new HashMap<>();
        for (String name : request.participantNames()) {
            ParticipantModel participant = ParticipantModel.builder()
                    .name(name)
                    .subtotal(BigDecimal.ZERO)
                    .taxAmount(BigDecimal.ZERO)
                    .serviceAmount(BigDecimal.ZERO)
                    .totalToPay(BigDecimal.ZERO)
                    .isPaid(false)
                    .build();

            bill.addParticipant(participant);
            participantMap.put(name, participant);
        }

        BigDecimal totalBillSubtotal = BigDecimal.ZERO;

        for (
                BillItemRequestDto itemDto : request.items()) {
            int qty = itemDto.quantity() != null && itemDto.quantity() > 0 ? itemDto.quantity() : 1;
            BigDecimal itemTotal = itemDto.price().multiply(BigDecimal.valueOf(qty));
            totalBillSubtotal = totalBillSubtotal.add(itemTotal);

            List<String> consumers = itemDto.participantsNames() != null ? itemDto.participantsNames() : Collections.emptyList();

            if (consumers.isEmpty()) {
                throw new IllegalArgumentException("Item '" + itemDto.name() + "' harus memiliki minimal 1 partisipan");
            }

            BigDecimal splitCount = BigDecimal.valueOf(consumers.size());
            BigDecimal pricePerPerson = itemTotal.divide(splitCount, 2, RoundingMode.HALF_UP);

            List<ParticipantModel> itemParticipants = new ArrayList<>();

            for (String consumerName : consumers) {
                ParticipantModel p = participantMap.get(consumerName);
                if (p == null) {
                    throw new IllegalArgumentException("Peserta bernama '" + consumerName + "' tidak terdaftar di participantNames");
                }

                p.setSubtotal(p.getSubtotal().add(pricePerPerson));
                itemParticipants.add(p);
            }

            BillItemModel itemModel = BillItemModel.builder()
                    .name(itemDto.name())
                    .price(itemDto.price())
                    .quantity(qty)
                    .participants(itemParticipants)
                    .build();
            bill.addItem(itemModel);
        }

        BigDecimal totalTax = totalBillSubtotal.multiply(taxPercent)
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        BigDecimal totalService = totalBillSubtotal.multiply(servicePercent)
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        BigDecimal totalAmount = totalBillSubtotal.add(totalTax).add(totalService);

        bill.setSubtotal(totalBillSubtotal);
        bill.setTotalTax(totalTax);
        bill.setTotalService(totalService);
        bill.setTotalAmount(totalAmount);

        for (
                ParticipantModel p : bill.getParticipants()) {
            BigDecimal pTax = p.getSubtotal().multiply(taxPercent)
                    .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
            BigDecimal pService = p.getSubtotal().multiply(servicePercent)
                    .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
            BigDecimal pTotal = p.getSubtotal().add(pTax).add(pService);

            p.setTaxAmount(pTax);
            p.setServiceAmount(pService);
            p.setTotalToPay(pTotal);
        }

        BillModel savedBill = billRepository.save(bill);

        return

                mapToResponseDto(savedBill);
    }

    @Transactional(readOnly = true)
    public BillResponseDto getBillById(Long id) {
        BillModel bill = billRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Bill dengan ID" + id + " tidak ditemukan!"));
        return mapToResponseDto(bill);
    }

    @Transactional(readOnly = true)
    public List<BillResponseDto> getAllBils() {
        return billRepository.findAll().stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    public String deleteBill(Long id) {
        if (!billRepository.existsById(id)) {
            return "Bill dengan ID " + id + " tidak ditemukan";
        }

        billRepository.deleteById(id);
        return "Bill dengan ID " + id + " berhasil dihapus dari database";
    }


    private BillResponseDto mapToResponseDto(BillModel bill) {
        List<ParticipantResponseDto> participantDtos = bill.getParticipants().stream()
                .map(p -> new ParticipantResponseDto(
                        p.getId(),
                        p.getName(),
                        p.getSubtotal(),
                        p.getTaxAmount(),
                        p.getServiceAmount(),
                        p.getTotalToPay(),
                        p.getIsPaid()
                )).toList();

        List<BillItemResponseDto> itemDtos = bill.getItems().stream()
                .map(item -> new BillItemResponseDto(
                        item.getId(),
                        item.getName(),
                        item.getPrice(),
                        item.getQuantity(),
                        item.getParticipants().stream().map(ParticipantModel::getName).toList()
                )).toList();

        return new BillResponseDto(
                bill.getId(),
                bill.getTitle(),
                bill.getTaxPercent(),
                bill.getServicePercent(),
                bill.getSubtotal(),
                bill.getTotalTax(),
                bill.getTotalService(),
                bill.getTotalAmount(),
                bill.getCreatedAt(),
                participantDtos,
                itemDtos
        );
    }
}