package com.example.blue_hawk.presentation.UserBoardgame;

import com.example.blue_hawk.application.dto.userboardgame.CreateUserBoardgameCommand;
import com.example.blue_hawk.application.dto.userboardgame.CreateUserBoardgameOutput;
import com.example.blue_hawk.application.dto.userboardgame.DeleteUserBoardgameCommand;
import com.example.blue_hawk.application.dto.userboardgame.GetUserBoardgameOutput;
import com.example.blue_hawk.application.dto.userboardgame.GetUserBoardgameQuery;
import com.example.blue_hawk.application.dto.userboardgame.ListUserBoardgameOutput;
import com.example.blue_hawk.application.dto.userboardgame.ListUserBoardgameQuery;
import com.example.blue_hawk.application.dto.userboardgame.UpdateUserBoardgameCommand;
import com.example.blue_hawk.application.dto.userboardgame.UpdateUserBoardgameOutput;
import com.example.blue_hawk.application.usecase.userboardgame.CreateUserBoardgameUseCase;
import com.example.blue_hawk.application.usecase.userboardgame.DeleteUserBoardgameUseCase;
import com.example.blue_hawk.application.usecase.userboardgame.GetUserBoardgameUseCase;
import com.example.blue_hawk.application.usecase.userboardgame.ListUserBoardgameUseCase;
import com.example.blue_hawk.application.usecase.userboardgame.UpdateUserBoardgameUseCase;
import com.example.blue_hawk.presentation.UserBoardgame.dto.UserBoardgameRequest;
import com.example.blue_hawk.presentation.UserBoardgame.dto.UserBoardgameResponse;
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
@RequestMapping("/user-boardgames")
public class UserBoardgameController {

    private final CreateUserBoardgameUseCase createUserBoardgameUseCase;
    private final GetUserBoardgameUseCase getUserBoardgameUseCase;
    private final ListUserBoardgameUseCase listUserBoardgameUseCase;
    private final UpdateUserBoardgameUseCase updateUserBoardgameUseCase;
    private final DeleteUserBoardgameUseCase deleteUserBoardgameUseCase;

    public UserBoardgameController(CreateUserBoardgameUseCase createUserBoardgameUseCase,
                                  GetUserBoardgameUseCase getUserBoardgameUseCase,
                                  ListUserBoardgameUseCase listUserBoardgameUseCase,
                                  UpdateUserBoardgameUseCase updateUserBoardgameUseCase,
                                  DeleteUserBoardgameUseCase deleteUserBoardgameUseCase) {
        this.createUserBoardgameUseCase = createUserBoardgameUseCase;
        this.getUserBoardgameUseCase = getUserBoardgameUseCase;
        this.listUserBoardgameUseCase = listUserBoardgameUseCase;
        this.updateUserBoardgameUseCase = updateUserBoardgameUseCase;
        this.deleteUserBoardgameUseCase = deleteUserBoardgameUseCase;
    }

    @PostMapping
    public ResponseEntity<UserBoardgameResponse> create(@RequestBody UserBoardgameRequest request) {
        CreateUserBoardgameCommand command = new CreateUserBoardgameCommand(
                request.userId(),
                request.boardgameId()
        );
        CreateUserBoardgameOutput output = createUserBoardgameUseCase.handle(command);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new UserBoardgameResponse(output.id(), output.userId(), output.boardgameId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserBoardgameResponse> getById(@PathVariable("id") String id) {
        GetUserBoardgameQuery query = new GetUserBoardgameQuery(id);
        GetUserBoardgameOutput output = getUserBoardgameUseCase.handle(query);
        return ResponseEntity.ok(new UserBoardgameResponse(output.id(), output.userId(), output.boardgameId()));
    }

    @GetMapping
    public ResponseEntity<List<UserBoardgameResponse>> list(
            @RequestParam(name = "userId", required = false) String userId,
            @RequestParam(name = "boardgameId", required = false) String boardgameId,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size) {

        ListUserBoardgameQuery query = new ListUserBoardgameQuery(userId, boardgameId, page, size);
        List<ListUserBoardgameOutput> output = listUserBoardgameUseCase.handle(query);

        List<UserBoardgameResponse> response = output.stream()
                .map(item -> new UserBoardgameResponse(item.id(), item.userId(), item.boardgameId()))
                .toList();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserBoardgameResponse> update(
            @PathVariable("id") String id,
            @RequestBody UserBoardgameRequest request) {

        UpdateUserBoardgameCommand command = new UpdateUserBoardgameCommand(
                id,
                request.userId(),
                request.boardgameId()
        );
        UpdateUserBoardgameOutput output = updateUserBoardgameUseCase.handle(command);
        return ResponseEntity.ok(new UserBoardgameResponse(output.id(), output.userId(), output.boardgameId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        DeleteUserBoardgameCommand command = new DeleteUserBoardgameCommand(id);
        deleteUserBoardgameUseCase.handle(command);
        return ResponseEntity.noContent().build();
    }
}
