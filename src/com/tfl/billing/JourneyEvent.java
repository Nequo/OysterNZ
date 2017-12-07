package com.tfl.billing;

import java.util.UUID;

public class JourneyEvent
        implements JourneyEventInterface
{

    public static final int START = 0;
    public static final int END = 1;

    private final UUID cardId;
    private final UUID readerId;
    private final long time;
    private final int eventType;

    public JourneyEvent(UUID cardId, UUID readerId, int eventType)
    {
        this.cardId = cardId;
        this.readerId = readerId;
        this.time = System.currentTimeMillis();
        this.eventType = eventType;
    }

    public UUID cardId()
    {
        return cardId;
    }

    public UUID readerId()
    {
        return readerId;
    }

    public long time()
    {
        return time;
    }

    public int getEventType()
    {
        return eventType;
    }
}



