package com.example.blue_hawk.application.usecase.offer;

import com.example.blue_hawk.application.dto.offer.UpdateOfferCommand;
import com.example.blue_hawk.application.dto.offer.UpdateOfferOutput;

public interface UpdateOfferUseCase {
    UpdateOfferOutput handle(UpdateOfferCommand command);
}
