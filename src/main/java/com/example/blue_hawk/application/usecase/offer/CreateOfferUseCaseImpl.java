package com.example.blue_hawk.application.usecase.offer;

import com.example.blue_hawk.application.dto.offer.CreateOfferCommand;
import com.example.blue_hawk.application.dto.offer.CreateOfferOutput;
import com.example.blue_hawk.domain.entity.offer.Offer;
import com.example.blue_hawk.domain.entity.offer.OfferStatus;
import com.example.blue_hawk.domain.repository.IOfferRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateOfferUseCaseImpl implements CreateOfferUseCase {

    private final IOfferRepository offerRepository;

    public CreateOfferUseCaseImpl(IOfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public CreateOfferOutput handle(CreateOfferCommand command) {
        OfferStatus status = command.status() != null
                ? OfferStatus.valueOf(command.status())
                : OfferStatus.ACTIVE;

        Offer offer = new Offer(
                command.userBoardgameId(),
                command.price(),
                status,
                command.description()
        );

        Offer saved = offerRepository.save(offer);

        return new CreateOfferOutput(
                saved.getId().toString(),
                saved.getUserBoardgameId().toString(),
                saved.getPrice(),
                saved.getStatus().name(),
                saved.getDescription(),
                saved.getCreatedAt(),
                saved.getUpdatedAt()
        );
    }
}
