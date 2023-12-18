package com.test.credibanca.controller;
import com.test.credibanca.dto.CardDTO;
import com.test.credibanca.entity.Card;
import com.test.credibanca.entity.User;
import com.test.credibanca.service.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CardControllerTest {

    private CardController cardController;
    private CardService cardService;

    @BeforeEach
    void setUp() {
        cardService = mock(CardService.class);
        cardController = new CardController(cardService);
    }
    private CardDTO createMockCardDTO() {
        CardDTO mockCardDTO = new CardDTO();
        mockCardDTO.setCardId("1234541639223000");
        mockCardDTO.setName("testname");
        mockCardDTO.setLastName("testlastname");
        mockCardDTO.setState(false);
        mockCardDTO.setExpirationDate(new Date());
        mockCardDTO.setBalance("0.0");
        return mockCardDTO;
    }

    private Card createMockCardEntity() {
        Card card = new Card();
        User user = new User();
        card.setUser(user);
        card.setId("1234541639223000");
        card.getUser().setName("testname");
        card.getUser().setLastName("testlastname");
        card.setState(0);
        card.setBalance(0.0);
        card.setExpirationDate(new Date());
        return card;
    }

    @Test
    void testGetCardById() {

        CardDTO mockCardDTO = createMockCardDTO();
        when(cardService.getCardById("123454")).thenReturn(mockCardDTO);
        ResponseEntity<?> response = cardController.getCardById("123454");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("1234541639223000", ((CardDTO) response.getBody()).getCardId());
    }

    @Test
    void testCreateCard() {
        CardDTO mockCardDTO = createMockCardDTO();
        when(cardService.createCard("123454", new User())).thenReturn(mockCardDTO);
        ResponseEntity<?> response = cardController.createCard("123454", new User());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("1234541639223000", ((CardDTO) response.getBody()).getCardId());
    }

    @Test
    void testActiveCard() {

        CardDTO mockCardDTO = createMockCardDTO();
        Card card= createMockCardEntity();
        when(cardService.activatedCard("1234541639223000")).thenReturn(mockCardDTO);
        ResponseEntity<?> response = cardController.activeCard(new CardDTO().convertEntityToAPI(card));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockCardDTO, ((CardDTO) response.getBody()));
    }

    @Test
    void testRechargeBalance() {
        CardDTO mockCardDTO = createMockCardDTO();
        Card card= createMockCardEntity();
        when(cardService.RechargeBalance("1234541639223000", "0.0")).thenReturn(mockCardDTO);
        ResponseEntity<?> response = cardController.RechargeBalance(mockCardDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockCardDTO, ((CardDTO) response.getBody()));
    }

    @Test
    void testDeleteCard() {
        CardDTO mockCardDTO = createMockCardDTO();
        Card card= createMockCardEntity();
        when(cardService.deleteCard(mockCardDTO.getCardId())).thenReturn("the card has been blocked");
        ResponseEntity<?> response = cardController.deleteCard(new CardDTO().convertEntityToAPI(card).getCardId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("the card has been blocked", response.getBody());
    }
}