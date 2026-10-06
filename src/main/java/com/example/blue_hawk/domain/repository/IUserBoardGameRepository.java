package com.example.blue_hawk.domain.repository;

import com.example.blue_hawk.domain.entity.userboardgame.UserBoardgame;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface IUserBoardGameRepository {

    UserBoardgame save(UserBoardgame userBoardgame);

    Optional<UserBoardgame> findById(UUID userBoardgameId);

    Page<UserBoardgame> findAll(UUID userId, UUID boardgameId, int page, int size);

    void deleteById(String userBoardgame);

    boolean existsById(UserBoardgame userBoardgame);
}
