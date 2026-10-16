package com.example.blue_hawk.presentation.match;

import com.example.blue_hawk.application.dto.match.CreateMatchCommand;
import com.example.blue_hawk.application.dto.match.CreateMatchOutput;
import com.example.blue_hawk.application.dto.match.DeleteMatchCommand;
import com.example.blue_hawk.application.dto.match.GetMatchOutput;
import com.example.blue_hawk.application.dto.match.GetMatchQuery;
import com.example.blue_hawk.application.dto.match.ListMatchOutput;
import com.example.blue_hawk.application.dto.match.ListMatchQuery;
import com.example.blue_hawk.application.dto.match.UpdateMatchCommand;
import com.example.blue_hawk.application.dto.match.UpdateMatchOutput;
import com.example.blue_hawk.application.usecase.match.CreateMatchUseCase;
import com.example.blue_hawk.application.usecase.match.DeleteMatchUseCase;
import com.example.blue_hawk.application.usecase.match.GetMatchUseCase;
import com.example.blue_hawk.application.usecase.match.ListMatchUseCase;
import com.example.blue_hawk.application.usecase.match.UpdateMatchUseCase;
import com.example.blue_hawk.presentation.match.dto.MatchRequest;
import com.example.blue_hawk.presentation.match.dto.MatchResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchController {

    private final CreateMatchUseCase createMatchUseCase;
    private final GetMatchUseCase getMatchUseCase;
    private final ListMatchUseCase listMatchUseCase;
    private final UpdateMatchUseCase updateMatchUseCase;
    private final DeleteMatchUseCase deleteMatchUseCase;

    public MatchController(CreateMatchUseCase createMatchUseCase,
                           GetMatchUseCase getMatchUseCase,
                           ListMatchUseCase listMatchUseCase,
                           UpdateMatchUseCase updateMatchUseCase,
                           DeleteMatchUseCase deleteMatchUseCase) {
        this.createMatchUseCase = createMatchUseCase;
        this.getMatchUseCase = getMatchUseCase;
        this.listMatchUseCase = listMatchUseCase;
        this.updateMatchUseCase = updateMatchUseCase;
        this.deleteMatchUseCase = deleteMatchUseCase;
    }

    @PostMapping
    public ResponseEntity<MatchResponse> create(@RequestBody MatchRequest request) {
        CreateMatchCommand command = new CreateMatchCommand(
                request.userBoardgameId(),
                request.maxUsers()
        );
        CreateMatchOutput output = createMatchUseCase.handle(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(output));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<MatchResponse> getById(@PathVariable("uuid") String uuid) {
        GetMatchQuery query = new GetMatchQuery(uuid);
        GetMatchOutput output = getMatchUseCase.handle(query);
        return ResponseEntity.ok(toResponse(output));
    }

    @GetMapping
    public ResponseEntity<List<MatchResponse>> list(
            @RequestParam(name = "userBoardgameId", required = false) String userBoardgameId,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size) {

        ListMatchQuery query = new ListMatchQuery(userBoardgameId, page, size);
        List<ListMatchOutput> output = listMatchUseCase.handle(query);

        List<MatchResponse> response = output.stream()
                .map(o -> new MatchResponse(o.id(), o.userBoardgameId(), o.maxUsers()))
                .toList();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<MatchResponse> update(
            @PathVariable("uuid") String uuid,
            @RequestBody MatchRequest request) {

        UpdateMatchCommand command = new UpdateMatchCommand(
                uuid,
                request.userBoardgameId(),
                request.maxUsers()
        );
        UpdateMatchOutput output = updateMatchUseCase.handle(command);
        return ResponseEntity.ok(toResponse(output));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable("uuid") String uuid) {
        deleteMatchUseCase.handle(new DeleteMatchCommand(uuid));
        return ResponseEntity.noContent().build();
    }

    // ── helpers ──────────────────────────────────────────────────────────────

    private MatchResponse toResponse(CreateMatchOutput o) {
        return new MatchResponse(o.id(), o.userBoardgameId(), o.maxUsers());
    }

    private MatchResponse toResponse(GetMatchOutput o) {
        return new MatchResponse(o.id(), o.userBoardgameId(), o.maxUsers());
    }

    private MatchResponse toResponse(UpdateMatchOutput o) {
        return new MatchResponse(o.id(), o.userBoardgameId(), o.maxUsers());
    }
}
