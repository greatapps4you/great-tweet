package us.greatapps4you.greattweet.application.utils;

import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.ZoneId;

@Service
public final class ClockService {
    private final Clock CENTRAL_EUROPE = Clock.system(ZoneId.of("Europe/Paris"));

    public Clock CENTRAL_EUROPE() {
        return CENTRAL_EUROPE;
    }
}
