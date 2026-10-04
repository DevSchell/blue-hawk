package com.example.blue_hawk.application.usecase.offer;

import com.example.blue_hawk.application.dto.offer.DeleteOfferCommand;
import com.example.blue_hawk.domain.repository.IOfferRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteOfferUseCaseImpl implements DeleteOfferUseCase {

    private final IOfferRepository offerRepository;

    public DeleteOfferUseCaseImpl(IOfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public void handle(DeleteOfferCommand command) {
        UUID id = UUID.fromString(command.id());
        if (!offerRepository.existsById(id)) {
            throw new RuntimeException("Offer not found with id: " + command.id());
        }
        offerRepository.deleteById(id);
    }
}
