package com.example.blue_hawk.infrastructure.persistence.userBoardgame;

import com.example.blue_hawk.domain.entity.UserBoardgame;
import com.example.blue_hawk.domain.repository.IUserBoardGameRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class UserBoardgameRepositoryImpl implements IUserBoardGameRepository {

    private final UserBoardgameJpaRepository jpaRepository;

    public UserBoardgameRepositoryImpl(UserBoardgameJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public UserBoardgame save(UserBoardgame userBoardgame) {
        UserBoardgameJpaEntity jpaEntity = toJpaEntity(userBoardgame);
        UserBoardgameJpaEntity saved = jpaRepository.save(jpaEntity);
        return toDomain(saved);
    }

    @Override
    public Optional<UserBoardgame> findById(UUID userBoardgameId) {
        return jpaRepository.findById(userBoardgameId)
                .map(this::toDomain);
    }

    @Override
    public Page<UserBoardgame> findAll(UUID userId, UUID boardgameId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return jpaRepository.findAllFiltered(userId, boardgameId, pageable)
                .map(this::toDomain);
    }

    @Override
    public void deleteById(String userBoardgameId) {
        jpaRepository.deleteById(UUID.fromString(userBoardgameId));
    }

    @Override
    public boolean existsById(UserBoardgame userBoardgame) {
        if (userBoardgame == null || userBoardgame.getId() == null) {
            return false;
        }
        return jpaRepository.existsById(userBoardgame.getId());
    }

    private UserBoardgameJpaEntity toJpaEntity(UserBoardgame domain) {
        return new UserBoardgameJpaEntity(
                domain.getId(),
                domain.getUserId(),
                domain.getBoardgameId()
        );
    }

    private UserBoardgame toDomain(UserBoardgameJpaEntity entity) {
        return new UserBoardgame(
                entity.getId(),
                entity.getUserId(),
                entity.getBoardgameId()
        );
    }
}
