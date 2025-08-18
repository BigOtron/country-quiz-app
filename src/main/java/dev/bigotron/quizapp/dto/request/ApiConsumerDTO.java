package dev.bigotron.quizapp.dto.request;

import java.util.List;
import java.util.Map;

public record ApiConsumerDTO(
        Name name,
        List<String> capital,
        boolean landlocked,
        Flags flags,
        CoatOfArms coatOfArms,
        String region,
        String subregion,
        long population
) {
    public record Name(
            String common,
            String official,
            Map<String, NativeName> nativeName
    ) {}

    public record NativeName(
            String official,
            String common
    ) {}

    public record Flags(
            String png,
            String svg,
            String alt
    ) {}

    public record CoatOfArms(
            String png,
            String svg
    ) {}
}