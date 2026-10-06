package com.example.blue_hawk.presentation.boardgame;

import com.example.blue_hawk.application.dto.boardgame.CreateBoardgameCommand;
import com.example.blue_hawk.application.dto.boardgame.CreateBoardgameOutput;
import com.example.blue_hawk.application.dto.boardgame.DeleteBoardgameCommand;
import com.example.blue_hawk.application.dto.boardgame.GetBoardgameOutput;
import com.example.blue_hawk.application.dto.boardgame.GetBoardgameQuery;
import com.example.blue_hawk.application.dto.boardgame.ListBoardgameOutput;
import com.example.blue_hawk.application.dto.boardgame.ListBoardgameQuery;
import com.example.blue_hawk.application.dto.boardgame.UpdateBoardgameCommand;
import com.example.blue_hawk.application.dto.boardgame.UpdateBoardgameOutput;
import com.example.blue_hawk.application.usecase.boardgame.CreateBoardgameUseCase;
import com.example.blue_hawk.application.usecase.boardgame.DeleteBoardgameUseCase;
import com.example.blue_hawk.application.usecase.boardgame.GetBoardgameUseCase;
import com.example.blue_hawk.application.usecase.boardgame.ListBoardgameUseCase;
import com.example.blue_hawk.application.usecase.boardgame.UpdateBoardgameUseCase;
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
@RequestMapping("/boardgames")
public class BoardgameController {

    private final CreateBoardgameUseCase createBoardgameUseCase;
    private final GetBoardgameUseCase getBoardgameUseCase;
    private final ListBoardgameUseCase listBoardgameUseCase;
    private final UpdateBoardgameUseCase updateBoardgameUseCase;
    private final DeleteBoardgameUseCase deleteBoardgameUseCase;

    public BoardgameController(CreateBoardgameUseCase createBoardgameUseCase,
                              GetBoardgameUseCase getBoardgameUseCase,
                              ListBoardgameUseCase listBoardgameUseCase,
                              UpdateBoardgameUseCase updateBoardgameUseCase,
                              DeleteBoardgameUseCase deleteBoardgameUseCase) {
        this.createBoardgameUseCase = createBoardgameUseCase;
        this.getBoardgameUseCase = getBoardgameUseCase;
        this.listBoardgameUseCase = listBoardgameUseCase;
        this.updateBoardgameUseCase = updateBoardgameUseCase;
        this.deleteBoardgameUseCase = deleteBoardgameUseCase;
    }

    @PostMapping
    public ResponseEntity<BoardgameResponse> create(@RequestBody BoardgameRequest request) {
        CreateBoardgameCommand command = new CreateBoardgameCommand(
                request.name(),
                request.description(),
                request.releaseYear(),
                request.playerNumber(),
                request.playTime()
        );
        CreateBoardgameOutput output = createBoardgameUseCase.handle(command);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BoardgameResponse(
                        output.id(),
                        output.name(),
                        output.description(),
                        output.releaseYear(),
                        output.playerNumber(),
                        output.playTime()
                ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardgameResponse> getById(@PathVariable String id) {
        GetBoardgameQuery query = new GetBoardgameQuery(id);
        GetBoardgameOutput output = getBoardgameUseCase.handle(query);
        return ResponseEntity.ok(new BoardgameResponse(
                output.id(),
                output.name(),
                output.description(),
                output.releaseYear(),
                output.playerNumber(),
                output.playTime()
        ));
    }

    @GetMapping
    public ResponseEntity<List<BoardgameResponse>> list(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size) {

        ListBoardgameQuery query = new ListBoardgameQuery(name, page, size);
        List<ListBoardgameOutput> output = listBoardgameUseCase.handle(query);

        List<BoardgameResponse> response = output.stream()
                .map(item -> new BoardgameResponse(
                        item.id(),
                        item.name(),
                        item.description(),
                        item.releaseYear(),
                        item.playerNumber(),
                        item.playTime()))
                .toList();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardgameResponse> update(
            @PathVariable String id,
            @RequestBody BoardgameRequest request) {

        UpdateBoardgameCommand command = new UpdateBoardgameCommand(
                id,
                request.name(),
                request.description(),
                request.releaseYear(),
                request.playerNumber(),
                request.playTime()
        );
        UpdateBoardgameOutput output = updateBoardgameUseCase.handle(command);
        return ResponseEntity.ok(new BoardgameResponse(
                output.id(),
                output.name(),
                output.description(),
                output.releaseYear(),
                output.playerNumber(),
                output.playTime()
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        deleteBoardgameUseCase.handle(new DeleteBoardgameCommand(id));
        return ResponseEntity.noContent().build();
    }
}
