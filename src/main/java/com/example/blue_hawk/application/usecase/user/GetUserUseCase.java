package com.example.blue_hawk.application.usecase.user;

import com.example.blue_hawk.application.dto.user.GetUserQuery;
import com.example.blue_hawk.application.dto.user.UserOutput;

public interface GetUserUseCase {
    UserOutput handle(GetUserQuery query);
}
