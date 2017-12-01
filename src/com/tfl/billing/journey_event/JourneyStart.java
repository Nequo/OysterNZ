package com.tfl.billing.journey_event;

import java.util.UUID;

public class JourneyStart extends JourneyEvent
{
    public JourneyStart(UUID cardId, UUID readerId) {
        super(cardId, readerId);
    }
}
