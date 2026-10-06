package com.example.blue_hawk.application.usecase.offer;

import com.example.blue_hawk.application.dto.offer.GetOfferOutput;
import com.example.blue_hawk.application.dto.offer.GetOfferQuery;
import com.example.blue_hawk.domain.entity.offer.Offer;
import com.example.blue_hawk.domain.repository.IOfferRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetOfferUseCaseImpl implements GetOfferUseCase {

    private final IOfferRepository offerRepository;

    public GetOfferUseCaseImpl(IOfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public GetOfferOutput handle(GetOfferQuery query) {
        Offer offer = offerRepository
                .findById(UUID.fromString(query.id()))
                .orElseThrow(() -> new RuntimeException("Offer not found with id: " + query.id()));

        return new GetOfferOutput(
                offer.getId().toString(),
                offer.getUserBoardgameId().toString(),
                offer.getPrice(),
                offer.getStatus().name(),
                offer.getDescription(),
                offer.getCreatedAt(),
                offer.getUpdatedAt()
        );
    }
}
