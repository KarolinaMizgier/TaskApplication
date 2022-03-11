package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TrelloClient {

    private final RestTemplate restTemplate;

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;
    @Value("${trello.app.key}")
    private String trelloAppKey;
    @Value("${trello.app.token}")
    private String trelloToken;
    @Value("${trello.app.name}")
    private String trelloUser;

    private URI buildUrl() {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/" + trelloUser + "/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("fields", "name,id")
                .build()
                .encode()
                .toUri();
        return url;
    }

    public List<TrelloBoardDto> getTrelloBoards() {


        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(buildUrl(), TrelloBoardDto[].class);

        return Arrays.stream(boardsResponse)
                .filter(o -> o.getId() != null)
                .filter(o -> !Strings.isEmpty(o.getName()))
                .filter(o -> o.getName().contains(""))
                .collect(Collectors.toList());

    }

}