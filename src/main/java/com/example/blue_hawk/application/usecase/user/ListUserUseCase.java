package com.example.blue_hawk.application.usecase.user;

import java.util.List;

import com.example.blue_hawk.application.dto.user.ListUserQuery;
import com.example.blue_hawk.application.dto.user.UserOutput;

public interface ListUserUseCase {
    List<UserOutput> handle(ListUserQuery query);
}
