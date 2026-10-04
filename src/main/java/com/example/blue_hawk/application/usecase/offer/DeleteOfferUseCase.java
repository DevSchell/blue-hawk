package com.example.blue_hawk.application.usecase.offer;

import com.example.blue_hawk.application.dto.offer.DeleteOfferCommand;

public interface DeleteOfferUseCase {
    void handle(DeleteOfferCommand command);
}
