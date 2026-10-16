package com.example.blue_hawk.application.usecase.match;

import com.example.blue_hawk.application.dto.match.GetMatchOutput;
import com.example.blue_hawk.application.dto.match.GetMatchQuery;

public interface GetMatchUseCase {
    GetMatchOutput handle(GetMatchQuery query);
}
