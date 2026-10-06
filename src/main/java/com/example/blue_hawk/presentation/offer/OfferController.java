package com.example.blue_hawk.presentation.offer;

import com.example.blue_hawk.application.dto.offer.CreateOfferCommand;
import com.example.blue_hawk.application.dto.offer.CreateOfferOutput;
import com.example.blue_hawk.application.dto.offer.DeleteOfferCommand;
import com.example.blue_hawk.application.dto.offer.GetOfferOutput;
import com.example.blue_hawk.application.dto.offer.GetOfferQuery;
import com.example.blue_hawk.application.dto.offer.ListOfferOutput;
import com.example.blue_hawk.application.dto.offer.ListOfferQuery;
import com.example.blue_hawk.application.dto.offer.PatchOfferCommand;
import com.example.blue_hawk.application.dto.offer.PatchOfferOutput;
import com.example.blue_hawk.application.dto.offer.UpdateOfferCommand;
import com.example.blue_hawk.application.dto.offer.UpdateOfferOutput;
import com.example.blue_hawk.application.usecase.offer.CreateOfferUseCase;
import com.example.blue_hawk.application.usecase.offer.DeleteOfferUseCase;
import com.example.blue_hawk.application.usecase.offer.GetOfferUseCase;
import com.example.blue_hawk.application.usecase.offer.ListOfferUseCase;
import com.example.blue_hawk.application.usecase.offer.PatchOfferUseCase;
import com.example.blue_hawk.application.usecase.offer.UpdateOfferUseCase;
import com.example.blue_hawk.presentation.offer.dto.OfferPatchRequest;
import com.example.blue_hawk.presentation.offer.dto.OfferRequest;
import com.example.blue_hawk.presentation.offer.dto.OfferResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/offers")
public class OfferController {

    private final CreateOfferUseCase createOfferUseCase;
    private final GetOfferUseCase getOfferUseCase;
    private final ListOfferUseCase listOfferUseCase;
    private final UpdateOfferUseCase updateOfferUseCase;
    private final PatchOfferUseCase patchOfferUseCase;
    private final DeleteOfferUseCase deleteOfferUseCase;

    public OfferController(CreateOfferUseCase createOfferUseCase,
                           GetOfferUseCase getOfferUseCase,
                           ListOfferUseCase listOfferUseCase,
                           UpdateOfferUseCase updateOfferUseCase,
                           PatchOfferUseCase patchOfferUseCase,
                           DeleteOfferUseCase deleteOfferUseCase) {
        this.createOfferUseCase = createOfferUseCase;
        this.getOfferUseCase = getOfferUseCase;
        this.listOfferUseCase = listOfferUseCase;
        this.updateOfferUseCase = updateOfferUseCase;
        this.patchOfferUseCase = patchOfferUseCase;
        this.deleteOfferUseCase = deleteOfferUseCase;
    }

    @PostMapping
    public ResponseEntity<OfferResponse> create(@RequestBody OfferRequest request) {
        CreateOfferCommand command = new CreateOfferCommand(
                request.userBoardgameId(),
                request.price(),
                request.status(),
                request.description()
        );
        CreateOfferOutput output = createOfferUseCase.handle(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(output));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<OfferResponse> getById(@PathVariable("uuid") String uuid) {
        GetOfferQuery query = new GetOfferQuery(uuid);
        GetOfferOutput output = getOfferUseCase.handle(query);
        return ResponseEntity.ok(toResponse(output));
    }

    @GetMapping
    public ResponseEntity<List<OfferResponse>> list(
            @RequestParam(name = "userId", required = false) String userId,
            @RequestParam(name = "boardgameId", required = false) String boardgameId,
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size) {

        ListOfferQuery query = new ListOfferQuery(userId, boardgameId, status, page, size);
        List<ListOfferOutput> output = listOfferUseCase.handle(query);

        List<OfferResponse> response = output.stream()
                .map(o -> new OfferResponse(o.id(), o.userBoardgameId(), o.price(),
                        o.status(), o.description(), o.createdAt(), o.updatedAt()))
                .toList();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<OfferResponse> update(
            @PathVariable("uuid") String uuid,
            @RequestBody OfferRequest request) {

        UpdateOfferCommand command = new UpdateOfferCommand(
                uuid,
                request.userBoardgameId(),
                request.price(),
                request.status(),
                request.description()
        );
        UpdateOfferOutput output = updateOfferUseCase.handle(command);
        return ResponseEntity.ok(toResponse(output));
    }

    @PatchMapping("/{uuid}")
    public ResponseEntity<OfferResponse> patch(
            @PathVariable("uuid") String uuid,
            @RequestBody OfferPatchRequest request) {

        PatchOfferCommand command = new PatchOfferCommand(
                uuid,
                request.price(),
                request.status(),
                request.description()
        );
        PatchOfferOutput output = patchOfferUseCase.handle(command);
        return ResponseEntity.ok(toResponse(output));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable("uuid") String uuid) {
        deleteOfferUseCase.handle(new DeleteOfferCommand(uuid));
        return ResponseEntity.noContent().build();
    }

    // ── helpers ──────────────────────────────────────────────────────────────

    private OfferResponse toResponse(CreateOfferOutput o) {
        return new OfferResponse(o.id(), o.userBoardgameId(), o.price(),
                o.status(), o.description(), o.createdAt(), o.updatedAt());
    }

    private OfferResponse toResponse(GetOfferOutput o) {
        return new OfferResponse(o.id(), o.userBoardgameId(), o.price(),
                o.status(), o.description(), o.createdAt(), o.updatedAt());
    }

    private OfferResponse toResponse(UpdateOfferOutput o) {
        return new OfferResponse(o.id(), o.userBoardgameId(), o.price(),
                o.status(), o.description(), o.createdAt(), o.updatedAt());
    }

    private OfferResponse toResponse(PatchOfferOutput o) {
        return new OfferResponse(o.id(), o.userBoardgameId(), o.price(),
                o.status(), o.description(), o.createdAt(), o.updatedAt());
    }
}
