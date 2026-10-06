package com.example.blue_hawk.infrastructure.persistence.boardgame;

import com.example.blue_hawk.domain.entity.Boardgame;
import com.example.blue_hawk.domain.repository.IBoardgameRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class BoardgameRepositoryImpl implements IBoardgameRepository {
    private final BoardgameJpaRepository boardgameJpaRepository;

    public BoardgameRepositoryImpl(BoardgameJpaRepository boardgameJpaRepository) {
        this.boardgameJpaRepository = boardgameJpaRepository;
    }

    @Override
    public Boardgame save(Boardgame boardgame) {
        BoardgameJpaEntity jpaEntity = toJpaEntity(boardgame);
        BoardgameJpaEntity saved = boardgameJpaRepository.save(jpaEntity);
        return toDomain(saved);
    }

    @Override
    public Optional<Boardgame> findById(UUID boardgameId) {
        return boardgameJpaRepository.findById(boardgameId)
                .map(this::toDomain);
    }

    @Override
    public Page<Boardgame> findAll(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<BoardgameJpaEntity> entities = (name != null && !name.isBlank())
                ? boardgameJpaRepository.findByNameContainingIgnoreCase(name.trim(), pageable)
                : boardgameJpaRepository.findAll(pageable);

        return entities.map(this::toDomain);
    }

    @Override
    public void deleteById(String boardgameId) {
        boardgameJpaRepository.deleteById(UUID.fromString(boardgameId));
    }

    @Override
    public boolean existsById(Boardgame boardgame) {
        if (boardgame == null || boardgame.getId() == null) {
            return false;
        }
        return boardgameJpaRepository.existsById(boardgame.getId());
    }

    private BoardgameJpaEntity toJpaEntity(Boardgame domain) {
        return new BoardgameJpaEntity(
                domain.getId(),
                domain.getName(),
                domain.getDescription(),
                domain.getReleaseYear(),
                domain.getPlayerNumber(),
                domain.getPlayTime()
        );
    }

    private Boardgame toDomain(BoardgameJpaEntity entity) {
        return new Boardgame(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getReleaseYear(),
                entity.getPlayerNumber(),
                entity.getPlayTime()
        );
    }
}
