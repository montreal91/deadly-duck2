package com.example.pokker;

import com.example.pokker.core.TournamentSeriesQueries;
import com.example.pokker.core.TournamentSeriesRepository;
import com.example.pokker.core.TournamentService;
import com.example.pokker.core.TournamentTypeRepository;
import com.example.pokker.infrastructure.StubTournamentSeriesRepository;
import com.example.pokker.infrastructure.StubTournamentTypeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration("PokkerConfiguration")
public class Config {
  @Bean
  TournamentTypeRepository tournamentTypeRepository() {
    return new StubTournamentTypeRepository();
  }

  @Bean
  TournamentSeriesRepository tournamentSeriesRepository() {
    return new StubTournamentSeriesRepository();
  }

  @Bean
  TournamentService tournamentService(
      TournamentTypeRepository typeRepository,
      TournamentSeriesRepository seriesRepository,
      TournamentSeriesQueries tournamentSeriesQueries
  ) {
    return new TournamentService(
        typeRepository, seriesRepository, tournamentSeriesQueries
    );
  }
}
