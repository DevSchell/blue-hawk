package com.example.blue_hawk.application.usecase.offer;

import com.example.blue_hawk.application.dto.offer.PatchOfferCommand;
import com.example.blue_hawk.application.dto.offer.PatchOfferOutput;
import com.example.blue_hawk.domain.entity.offer.Offer;
import com.example.blue_hawk.domain.entity.offer.OfferStatus;
import com.example.blue_hawk.domain.repository.IOfferRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PatchOfferUseCaseImpl implements PatchOfferUseCase {

    private final IOfferRepository offerRepository;

    public PatchOfferUseCaseImpl(IOfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public PatchOfferOutput handle(PatchOfferCommand command) {
        Offer offer = offerRepository
                .findById(UUID.fromString(command.id()))
                .orElseThrow(() -> new RuntimeException("Offer not found with id: " + command.id()));

        if (command.price() != null) {
            offer.setPrice(command.price());
        }
        if (command.status() != null) {
            offer.setStatus(OfferStatus.valueOf(command.status()));
        }
        if (command.description() != null) {
            offer.setDescription(command.description());
        }

        Offer updated = offerRepository.save(offer);

        return new PatchOfferOutput(
                updated.getId().toString(),
                updated.getUserBoardgameId().toString(),
                updated.getPrice(),
                updated.getStatus().name(),
                updated.getDescription(),
                updated.getCreatedAt(),
                updated.getUpdatedAt()
        );
    }
}
