package com.tfl.billing;

import java.util.UUID;

public interface JourneyEventInterface
{
    public UUID cardId();

    public UUID readerId();

    public long time();

    public int getEventType();
}
