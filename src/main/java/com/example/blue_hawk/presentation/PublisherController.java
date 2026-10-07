package com.example.blue_hawk.presentation;

import com.example.blue_hawk.domain.entity.publisher.Country;
import com.example.blue_hawk.application.dto.publisher.CreatePublisherCommand;
import com.example.blue_hawk.application.dto.publisher.CreatePublisherOutput;
import com.example.blue_hawk.application.dto.publisher.DeletePublisherCommand;
import com.example.blue_hawk.application.dto.publisher.GetPublisherOutput;
import com.example.blue_hawk.application.dto.publisher.GetPublisherQuery;
import com.example.blue_hawk.application.dto.publisher.ListPublisherOutput;
import com.example.blue_hawk.application.dto.publisher.ListPublisherQuery;
import com.example.blue_hawk.application.dto.publisher.UpdatePublisherCommand;
import com.example.blue_hawk.application.dto.publisher.UpdatePublisherOutput;
import com.example.blue_hawk.application.usecase.publisher.CreatePublisherUseCase;
import com.example.blue_hawk.application.usecase.publisher.DeletePublisherUseCase;
import com.example.blue_hawk.application.usecase.publisher.GetPublisherUseCase;
import com.example.blue_hawk.application.usecase.publisher.ListPublisherUseCase;
import com.example.blue_hawk.application.usecase.publisher.UpdatePublisherUseCase;
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
@RequestMapping("/publishers")
public class PublisherController {

    private final CreatePublisherUseCase createPublisherUseCase;
    private final GetPublisherUseCase getPublisherUseCase;
    private final ListPublisherUseCase listPublisherUseCase;
    private final UpdatePublisherUseCase updatePublisherUseCase;
    private final DeletePublisherUseCase deletePublisherUseCase;

    public PublisherController(CreatePublisherUseCase createPublisherUseCase,
                               GetPublisherUseCase getPublisherUseCase,
                               ListPublisherUseCase listPublisherUseCase,
                               UpdatePublisherUseCase updatePublisherUseCase,
                               DeletePublisherUseCase deletePublisherUseCase) {
        this.createPublisherUseCase = createPublisherUseCase;
        this.getPublisherUseCase = getPublisherUseCase;
        this.listPublisherUseCase = listPublisherUseCase;
        this.updatePublisherUseCase = updatePublisherUseCase;
        this.deletePublisherUseCase = deletePublisherUseCase;
    }

    @PostMapping
    public ResponseEntity<PublisherResponse> create(@RequestBody PublisherRequest request) {
        CreatePublisherCommand command = new CreatePublisherCommand(
                null,
                request.name(),
                request.country()
        );
        CreatePublisherOutput output = createPublisherUseCase.handle(command);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new PublisherResponse(
                        output.id(),
                        output.name(),
                        output.country()
                ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherResponse> getById(@PathVariable String id) {
        GetPublisherQuery query = new GetPublisherQuery(id);
        GetPublisherOutput output = getPublisherUseCase.handle(query);
        return ResponseEntity.ok(new PublisherResponse(
                output.id(),
                output.name(),
                output.country()
        ));
    }

    @GetMapping
    public ResponseEntity<List<PublisherResponse>> list(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "country", required = false) Country country,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size) {

        ListPublisherQuery query = new ListPublisherQuery(name, country, page, size);
        List<ListPublisherOutput> output = listPublisherUseCase.handle(query);

        List<PublisherResponse> response = output.stream()
                .map(item -> new PublisherResponse(
                        item.id(),
                        item.name(),
                        item.country()))
                .toList();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublisherResponse> update(
            @PathVariable String id,
            @RequestBody PublisherRequest request) {

        UpdatePublisherCommand command = new UpdatePublisherCommand(
                id,
                request.name(),
                request.country()
        );
        UpdatePublisherOutput output = updatePublisherUseCase.handle(command);
        return ResponseEntity.ok(new PublisherResponse(
                output.id(),
                output.name(),
                output.country()
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        deletePublisherUseCase.handle(new DeletePublisherCommand(id));
        return ResponseEntity.noContent().build();
    }
}
