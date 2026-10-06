package com.example.blue_hawk.application.usecase.offer;

import com.example.blue_hawk.application.dto.offer.ListOfferOutput;
import com.example.blue_hawk.application.dto.offer.ListOfferQuery;
import com.example.blue_hawk.domain.entity.offer.OfferStatus;
import com.example.blue_hawk.domain.repository.IOfferRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ListOfferUseCaseImpl implements ListOfferUseCase {

    private final IOfferRepository offerRepository;

    public ListOfferUseCaseImpl(IOfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public List<ListOfferOutput> handle(ListOfferQuery query) {
        UUID userId = query.userId() != null ? UUID.fromString(query.userId()) : null;
        UUID boardgameId = query.boardgameId() != null ? UUID.fromString(query.boardgameId()) : null;
        OfferStatus status = query.status() != null ? OfferStatus.valueOf(query.status()) : null;

        return offerRepository
                .findAll(userId, boardgameId, status, query.page(), query.size())
                .getContent()
                .stream()
                .map(o -> new ListOfferOutput(
                        o.getId().toString(),
                        o.getUserBoardgameId().toString(),
                        o.getPrice(),
                        o.getStatus().name(),
                        o.getDescription(),
                        o.getCreatedAt(),
                        o.getUpdatedAt()))
                .toList();
    }
}
