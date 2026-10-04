package com.example.blue_hawk.application.usecase.offer;

import com.example.blue_hawk.application.dto.offer.CreateOfferCommand;
import com.example.blue_hawk.application.dto.offer.CreateOfferOutput;

public interface CreateOfferUseCase {
    CreateOfferOutput handle(CreateOfferCommand command);
}
