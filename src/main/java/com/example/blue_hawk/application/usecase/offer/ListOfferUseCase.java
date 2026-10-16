package com.example.blue_hawk.application.usecase.offer;

import com.example.blue_hawk.application.dto.offer.ListOfferQuery;
import com.example.blue_hawk.application.dto.offer.ListOfferOutput;

import java.util.List;

public interface ListOfferUseCase {
    List<ListOfferOutput> handle(ListOfferQuery query);
}
