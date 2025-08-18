package dev.bigotron.quizapp.repository;

import dev.bigotron.quizapp.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}