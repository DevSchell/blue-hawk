package com.example.blue_hawk.domain.repository;

import com.example.blue_hawk.domain.entity.UserBoardgame;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserBoardGameRepository {

    UserBoardgame save(UserBoardgame userBoardgame);

    Optional<UserBoardgame> findById(UserBoardgame userBoardgame);

    Page<UserBoardgame> findAll(UUID userId, UUID boardgameId);

    void deleteById(UserBoardgame userBoardgame);

    boolean existsById(UserBoardgame userBoardgame);
}
