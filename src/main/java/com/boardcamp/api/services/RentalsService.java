package com.boardcamp.api.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.boardcamp.api.Exception.RentalNotFoundException;
import com.boardcamp.api.dtos.RentalsDTO;
import com.boardcamp.api.models.CustomersModel;
import com.boardcamp.api.models.GamesModel;
import com.boardcamp.api.models.RentalsModel;
import com.boardcamp.api.repositories.CustomersRepository;
import com.boardcamp.api.repositories.GamesRepository;
import com.boardcamp.api.repositories.RentalsRepository;

@Service
public class RentalsService {
    final RentalsRepository rentalsRepository;
    final GamesRepository gamesRepository;
    final CustomersRepository customersRepository;

    RentalsService(RentalsRepository rentalsRepository, GamesRepository gamesRepository, CustomersRepository customersRepository) {
        this.rentalsRepository = rentalsRepository;
        this.gamesRepository = gamesRepository;
        this.customersRepository = customersRepository;
    }

    public Optional<RentalsModel> save(RentalsDTO dto) {
        Optional<GamesModel> game = gamesRepository.findById(dto.getGameId());
        Optional<CustomersModel> customer = customersRepository.findById(dto.getCustomerId());

        if(!game.isPresent() || !customer.isPresent()) {
            return Optional.empty();
        }

        RentalsModel rental = new RentalsModel(dto, game.get(), customer.get());
        return Optional.of(rentalsRepository.save(rental));
    }

    public List<RentalsModel> findAll() {
        return rentalsRepository.findAll();
    }

    public RentalsModel findById(Long id) {
        return rentalsRepository.findById(id).orElseThrow(
                () -> new RentalNotFoundException("Rental not found by this id!"));
    }

    public RentalsModel finish(Long id) {
        RentalsModel rental = this.findById(id);

        if (rental.getReturnDate() != null) {
            return null;
        }

        LocalDate currentDate = LocalDate.now();
        LocalDate returnDate = rental.getRentDate().plusDays(rental.getDaysRented());
        int delayDays = Math.max(0, currentDate.compareTo(returnDate));
        Integer delayFee = delayDays * rental.getGame().getPricePerDay();

        rental.setReturnDate(currentDate);
        rental.setDelayFee(delayFee);

        return rentalsRepository.save(rental);
    }
}
