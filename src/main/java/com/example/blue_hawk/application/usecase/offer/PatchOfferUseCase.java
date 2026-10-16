package com.example.blue_hawk.application.usecase.offer;

import com.example.blue_hawk.application.dto.offer.PatchOfferCommand;
import com.example.blue_hawk.application.dto.offer.PatchOfferOutput;

public interface PatchOfferUseCase {
    PatchOfferOutput handle(PatchOfferCommand command);
}
