package com.boardcamp.api.services;

import org.springframework.stereotype.Service;

import com.boardcamp.api.dtos.GamesDTO;
import com.boardcamp.api.models.GamesModel;
import com.boardcamp.api.repositories.GamesRepository;

@Service
public class GamesService {
    final GamesRepository gamesRepository;

    GamesService(GamesRepository gamesRepository) {
        this.gamesRepository = gamesRepository;
    }

    public GamesModel save(GamesDTO dto) {
        GamesModel game = new GamesModel(dto);
        return gamesRepository.save(game);
    }

    public boolean existsByName(String name) {
        return gamesRepository.existsByName(name);
    }
}
