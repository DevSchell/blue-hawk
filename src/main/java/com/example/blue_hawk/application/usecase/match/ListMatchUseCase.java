package com.example.blue_hawk.application.usecase.match;

import com.example.blue_hawk.application.dto.match.ListMatchOutput;
import com.example.blue_hawk.application.dto.match.ListMatchQuery;

import java.util.List;

public interface ListMatchUseCase {
    List<ListMatchOutput> handle(ListMatchQuery query);
}
