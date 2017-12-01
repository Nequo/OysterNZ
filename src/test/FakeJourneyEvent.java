package test;

import com.tfl.billing.journey_event.JourneyEventInterface;

import java.util.UUID;

public class FakeJourneyEvent
        implements JourneyEventInterface
{
    private final UUID cardId;
    private final UUID readerId;
    private final long time;

    public FakeJourneyEvent(UUID cardId, UUID readerId, long time)
    {
        this.cardId = cardId;
        this.readerId = readerId;
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
}