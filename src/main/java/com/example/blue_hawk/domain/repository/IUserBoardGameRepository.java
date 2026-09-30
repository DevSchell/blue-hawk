package com.example.blue_hawk.domain.repository;

import com.example.blue_hawk.domain.entity.UserBoardgame;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IUserBoardGameRepository extends JpaRepository<UserBoardgame, UUID> {

    // Spring Data vai implementar esse metodo automaticamente baseando-se no nome!
    Page<UserBoardgame> findByUserIdAndBoardgameId(UUID userId, UUID boardgameId, Pageable pageable);

}
