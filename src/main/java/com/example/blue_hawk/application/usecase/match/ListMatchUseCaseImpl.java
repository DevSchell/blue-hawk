package com.example.blue_hawk.application.usecase.match;

import com.example.blue_hawk.application.dto.match.ListMatchOutput;
import com.example.blue_hawk.application.dto.match.ListMatchQuery;
import com.example.blue_hawk.domain.repository.IMatchRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ListMatchUseCaseImpl implements ListMatchUseCase {

    private final IMatchRepository matchRepository;

    public ListMatchUseCaseImpl(IMatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public List<ListMatchOutput> handle(ListMatchQuery query) {
        UUID boardgameId = query.boardgameId() != null ? UUID.fromString(query.boardgameId()) : null;
        UUID userId = query.userId() != null ? UUID.fromString(query.userId()) : null;

        return matchRepository
                .findAll(boardgameId, userId, query.page(), query.size())
                .getContent()
                .stream()
                .map(m -> new ListMatchOutput(
                        m.getId().toString(),
                        m.getBoardgameId().toString(),
                        m.getUserId().toString()))
                .toList();
    }
}
