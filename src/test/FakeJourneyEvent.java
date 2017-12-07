package test;

import com.tfl.billing.JourneyEventInterface;

import java.util.UUID;

public class FakeJourneyEvent
        implements JourneyEventInterface
{
    private final UUID cardId;
    private final UUID readerId;
    private final long time;
    private final int eventType;

    public FakeJourneyEvent(UUID cardId, UUID readerId, int eventType, long time)
    {
        this.cardId = cardId;
        this.readerId = readerId;
        this.eventType = eventType;
        this.time = time;
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