package com.boardcamp.api.Unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.boardcamp.api.dtos.GamesDTO;
import com.boardcamp.api.models.GamesModel;
import com.boardcamp.api.repositories.GamesRepository;
import com.boardcamp.api.services.GamesService;

@SpringBootTest
class GamesUnitTests {

	@InjectMocks
	private GamesService gamesService;

	@Mock
	private GamesRepository gamesRepository;

	@Test
	void givenGame_whenCreatingGame_thenCreatesGame() {
		GamesDTO gamesDTO = new GamesDTO("Banco Imobiliário", "http://www.imagem.com.br/banco_imobiliario.jpg", 3,
				1000);
		GamesModel newGame = new GamesModel(gamesDTO);

		doReturn(false).when(gamesRepository).existsByName(any());
		doReturn(newGame).when(gamesRepository).save(any());

		GamesModel result = gamesService.save(gamesDTO);

		assertNotNull(result);
		assertEquals(newGame, result);
	}

}
