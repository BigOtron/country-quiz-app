package dev.bigotron.quizapp.service.impl;

import dev.bigotron.quizapp.dto.request.ApiConsumerDTO;
import dev.bigotron.quizapp.dto.response.AnswerOptionResponseDTO;
import dev.bigotron.quizapp.dto.response.QuestionResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final RestTemplate restTemplate;
    private static final List<String> COUNTRY_CODES = new ArrayList<>(List.of(
            "AF", "AR", "AU", "AZ", "CA", "CN", "FR", "KZ",
            "KG", "MY", "TJ", "TR", "TM", "UZ"
    ));

    public List<QuestionResponseDTO> generateQuestions() {
        List<String> shuffled = new ArrayList<>(COUNTRY_CODES);
        Collections.shuffle(shuffled);
        List<String> chosenCountryCodes = shuffled.stream()
                .limit(3)
                .toList();
        String chosenCountries = String.join(",", chosenCountryCodes);

        ResponseEntity<List<ApiConsumerDTO>> countryInfo = restTemplate.exchange(
                "https://restcountries.com/v3.1/alpha?codes=" + chosenCountries,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        return buildQuestions(countryInfo);
    }

    private List<QuestionResponseDTO> buildQuestions(ResponseEntity<List<ApiConsumerDTO>> countryInfo) {
        List<ApiConsumerDTO> countries = countryInfo.getBody();
        if (countries == null) {
            return Collections.emptyList();
        }

        List<QuestionResponseDTO> output = new ArrayList<>();
        long idCounter = 1L;
        Random random = new Random();

        for (ApiConsumerDTO country : countries) {
            String capital = (country.capital() != null && !country.capital().isEmpty())
                    ? country.capital().get(0)
                    : "Unknown";

            List<String> capitalsPool = countries.stream()
                    .map(c -> c.capital() != null && !c.capital().isEmpty() ? c.capital().get(0) : "Unknown")
                    .filter(cap -> !cap.equals(capital))
                    .toList();

            List<String> options = new ArrayList<>();
            options.add(capital);
            while (options.size() < 4 && !capitalsPool.isEmpty()) {
                options.add(capitalsPool.get(random.nextInt(capitalsPool.size())));
            }
            Collections.shuffle(options);

            List<AnswerOptionResponseDTO> answerOptions = new ArrayList<>();
            long optionId = 1L;
            for (String option : options) {
                answerOptions.add(new AnswerOptionResponseDTO(optionId++, option));
            }

            output.add(new QuestionResponseDTO(
                    idCounter++,
                    "What is the capital of " + country.name().common(),
                    "multiple-choice",
                    answerOptions
            ));
        }

        return output;
    }
}
