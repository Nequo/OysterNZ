package com.tfl.billing.journey_event;

import java.util.UUID;

public interface JourneyEventInterface
{
    public UUID cardId();
    public UUID readerId();
    public long time();
}
