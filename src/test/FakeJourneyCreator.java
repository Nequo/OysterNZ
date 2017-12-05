package test;

import com.tfl.billing.Journey;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FakeJourneyCreator
{
    protected static final int longJourneys = 0;
    protected static final int shortJourneys = 1;
    protected static final int mixJourneys = 2;


    public FakeJourneyCreator()
    {
    }


    //do not add more then 8 long off-peak journeys, in order not get peak time
    protected List<Journey> getFakeJourneys(int numberOfJourneys, boolean inPeakTime, int typesOfJourneys)
    {
        List<Journey> journeys = new ArrayList<Journey>();
        long fakeCurrentTime;

        if (!inPeakTime)
            fakeCurrentTime = 1493717400000L;
        else
            fakeCurrentTime = 1493701200000L;


        switch (typesOfJourneys)
        {
            case longJourneys:
                for (int i = 0; i < numberOfJourneys; i++)
                {
                    journeys.add(createFakeJourney(fakeCurrentTime, 26));
                    fakeCurrentTime += 26 * 60 * 1000;
                }
                break;

            case shortJourneys:
                for (int i = 0; i < numberOfJourneys; i++)
                {
                    journeys.add(createFakeJourney(fakeCurrentTime, 5));
                    fakeCurrentTime += 5 * 60 * 1000;
                }
                break;

            case mixJourneys:
                for (int i = 0; i < numberOfJourneys / 2; i++)
                {
                    journeys.add(createFakeJourney(fakeCurrentTime, 26));
                    fakeCurrentTime += 26 * 60 * 1000;
                    journeys.add(createFakeJourney(fakeCurrentTime, 5));
                    fakeCurrentTime += 5 * 60 * 1000;
                }
                break;
        }

        return journeys;
    }

    protected Journey createFakeJourney(long setStartTime, int setJourneyInMin)
    {
        UUID cardExampleID = UUID.randomUUID();
        FakeJourneyEvent journeyStart = new FakeJourneyEvent(cardExampleID, UUID.randomUUID(), setStartTime);
        FakeJourneyEvent journeyEnd = new FakeJourneyEvent(cardExampleID, UUID.randomUUID(), setStartTime + setJourneyInMin * 60 * 1000);
        return new Journey(journeyStart, journeyEnd);
    }

    protected Journey createFakeJourney(long setStartTime, int setJourneyInMin, UUID cardExampleID, UUID readerOriginID, UUID readerDestinationID)
    {
        FakeJourneyEvent journeyStart = new FakeJourneyEvent(cardExampleID, readerOriginID, setStartTime);
        FakeJourneyEvent journeyEnd = new FakeJourneyEvent(cardExampleID, readerDestinationID, setStartTime + setJourneyInMin * 60 * 1000);
        return new Journey(journeyStart, journeyEnd);
    }
}