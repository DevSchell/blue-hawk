package com.example.blue_hawk.presentation;

import com.example.blue_hawk.application.dto.matchParticipant.CreateMatchParticipantCommand;
import com.example.blue_hawk.application.dto.matchParticipant.CreateMatchParticipantOutput;
import com.example.blue_hawk.application.dto.matchParticipant.DeleteMatchParticipantCommand;
import com.example.blue_hawk.application.dto.matchParticipant.GetMatchParticipantOutput;
import com.example.blue_hawk.application.dto.matchParticipant.GetMatchParticipantQuery;
import com.example.blue_hawk.application.dto.matchParticipant.ListMatchParticipantOutput;
import com.example.blue_hawk.application.dto.matchParticipant.ListMatchParticipantQuery;
import com.example.blue_hawk.application.dto.matchParticipant.UpdateMatchParticipantCommand;
import com.example.blue_hawk.application.dto.matchParticipant.UpdateMatchParticipantOutput;
import com.example.blue_hawk.application.usecase.matchParticipant.CreateMatchParticipantUseCase;
import com.example.blue_hawk.application.usecase.matchParticipant.DeleteMatchParticipantUseCase;
import com.example.blue_hawk.application.usecase.matchParticipant.GetMatchParticipantUseCase;
import com.example.blue_hawk.application.usecase.matchParticipant.ListMatchParticipantUseCase;
import com.example.blue_hawk.application.usecase.matchParticipant.UpdateMatchParticipantUseCase;
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
@RequestMapping("/match-participants")
public class MatchParticipantController {

    private final CreateMatchParticipantUseCase createMatchParticipantUseCase;
    private final GetMatchParticipantUseCase getMatchParticipantUseCase;
    private final ListMatchParticipantUseCase listMatchParticipantUseCase;
    private final UpdateMatchParticipantUseCase updateMatchParticipantUseCase;
    private final DeleteMatchParticipantUseCase deleteMatchParticipantUseCase;

    public MatchParticipantController(CreateMatchParticipantUseCase createMatchParticipantUseCase,
                                      GetMatchParticipantUseCase getMatchParticipantUseCase,
                                      ListMatchParticipantUseCase listMatchParticipantUseCase,
                                      UpdateMatchParticipantUseCase updateMatchParticipantUseCase,
                                      DeleteMatchParticipantUseCase deleteMatchParticipantUseCase) {
        this.createMatchParticipantUseCase = createMatchParticipantUseCase;
        this.getMatchParticipantUseCase = getMatchParticipantUseCase;
        this.listMatchParticipantUseCase = listMatchParticipantUseCase;
        this.updateMatchParticipantUseCase = updateMatchParticipantUseCase;
        this.deleteMatchParticipantUseCase = deleteMatchParticipantUseCase;
    }

    @PostMapping
    public ResponseEntity<MatchParticipantResponse> create(@RequestBody MatchParticipantRequest request) {
        CreateMatchParticipantCommand command = new CreateMatchParticipantCommand(
                request.matchId(),
                request.userId()
        );
        CreateMatchParticipantOutput output = createMatchParticipantUseCase.handle(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(output));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<MatchParticipantResponse> getById(@PathVariable("uuid") String uuid) {
        GetMatchParticipantQuery query = new GetMatchParticipantQuery(uuid);
        GetMatchParticipantOutput output = getMatchParticipantUseCase.handle(query);
        return ResponseEntity.ok(toResponse(output));
    }

    @GetMapping
    public ResponseEntity<List<MatchParticipantResponse>> list(
            @RequestParam(name = "matchId", required = false) String matchId,
            @RequestParam(name = "userId", required = false) String userId,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size) {

        ListMatchParticipantQuery query = new ListMatchParticipantQuery(matchId, userId, page, size);
        List<ListMatchParticipantOutput> output = listMatchParticipantUseCase.handle(query);

        List<MatchParticipantResponse> response = output.stream()
                .map(o -> new MatchParticipantResponse(o.id(), o.matchId(), o.userId()))
                .toList();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<MatchParticipantResponse> update(
            @PathVariable("uuid") String uuid,
            @RequestBody MatchParticipantRequest request) {

        UpdateMatchParticipantCommand command = new UpdateMatchParticipantCommand(
                uuid,
                request.matchId(),
                request.userId()
        );
        UpdateMatchParticipantOutput output = updateMatchParticipantUseCase.handle(command);
        return ResponseEntity.ok(toResponse(output));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable("uuid") String uuid) {
        deleteMatchParticipantUseCase.handle(new DeleteMatchParticipantCommand(uuid));
        return ResponseEntity.noContent().build();
    }

    // ── helpers ──────────────────────────────────────────────────────────────

    private MatchParticipantResponse toResponse(CreateMatchParticipantOutput o) {
        return new MatchParticipantResponse(o.id(), o.matchId(), o.userId());
    }

    private MatchParticipantResponse toResponse(GetMatchParticipantOutput o) {
        return new MatchParticipantResponse(o.id(), o.matchId(), o.userId());
    }

    private MatchParticipantResponse toResponse(UpdateMatchParticipantOutput o) {
        return new MatchParticipantResponse(o.id(), o.matchId(), o.userId());
    }
}
