package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Mock
    private AdminConfig adminConfig;

    @Test
    void shouldFetchTrelloBoards() {
        // Given
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("id", "name", List.of());
        when(trelloClient.getTrelloBoards()).thenReturn(List.of(trelloBoardDto));

        // When
        List<TrelloBoardDto> trelloBoardDtos = trelloService.fetchTrelloBoards();

        // Then
        assertEquals(trelloBoardDto, trelloBoardDtos.get(0));
    }

    @Test
    void shouldCreateTrelloCard() {
        // Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "description", "pos", "listId");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("id", "name", "uri");
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        doNothing().when(emailService).send(any(Mail.class));

        // When
        trelloService.createTrelloCard(trelloCardDto);

        // Then
        verify(emailService, atLeastOnce()).send(any(Mail.class));
        verify(adminConfig, atLeastOnce()).getAdminMail();
    }


}
