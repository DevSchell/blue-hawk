package com.example.blue_hawk.application.usecase.offer;

import com.example.blue_hawk.application.dto.offer.GetOfferQuery;
import com.example.blue_hawk.application.dto.offer.GetOfferOutput;

public interface GetOfferUseCase {
    GetOfferOutput handle(GetOfferQuery query);
}
