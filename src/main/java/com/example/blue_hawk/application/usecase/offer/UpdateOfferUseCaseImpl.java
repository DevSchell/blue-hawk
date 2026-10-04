package com.example.blue_hawk.application.usecase.offer;

import com.example.blue_hawk.application.dto.offer.UpdateOfferCommand;
import com.example.blue_hawk.application.dto.offer.UpdateOfferOutput;
import com.example.blue_hawk.domain.entity.Offer;
import com.example.blue_hawk.domain.entity.OfferStatus;
import com.example.blue_hawk.domain.repository.IOfferRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateOfferUseCaseImpl implements UpdateOfferUseCase {

    private final IOfferRepository offerRepository;

    public UpdateOfferUseCaseImpl(IOfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public UpdateOfferOutput handle(UpdateOfferCommand command) {
        Offer offer = offerRepository
                .findById(UUID.fromString(command.id()))
                .orElseThrow(() -> new RuntimeException("Offer not found with id: " + command.id()));

        offer.setUserBoardgameId(UUID.fromString(command.userBoardgameId()));
        offer.setPrice(command.price());
        offer.setStatus(OfferStatus.valueOf(command.status()));
        offer.setDescription(command.description());

        Offer updated = offerRepository.save(offer);

        return new UpdateOfferOutput(
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
