package test;

import com.tfl.billing.Journey;
import com.tfl.billing.JourneyEnd;
import com.tfl.billing.JourneyStart;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class JourneyTest
{
    private UUID cardExampleID = UUID.randomUUID();
    private UUID readerOriginID = UUID.randomUUID();
    private UUID readerDestinationID = UUID.randomUUID();
    private JourneyStart journeyStart;
    private JourneyEnd journeyEnd;
    private Journey journey;

    private void createFakeJourney(int secondsToSleep) throws InterruptedException
    {
        journeyStart = new JourneyStart(cardExampleID, readerOriginID);
        Thread.sleep(secondsToSleep * 1000);
        journeyEnd = new JourneyEnd(cardExampleID, readerDestinationID);
        journey = new Journey(journeyStart, journeyEnd);
    }

    @Test
    public void JourneyDurationMinutesTest() throws InterruptedException
    {
        createFakeJourney(1);
        assertThat(journey.durationMinutes(), is("0:1"));
    }

    @Test
    public void JourneyDurationSecondsTest() throws InterruptedException
    {
        createFakeJourney(3);
        assertThat(journey.durationSeconds(), is((int) 3));
    }

    @Test
    public void startTimeTest() throws InterruptedException
    {
        createFakeJourney(1);
        assertThat(journey.startTime(), is(new Date(journeyStart.time())));
    }

    @Test
    public void endTimeTest() throws InterruptedException
    {
        createFakeJourney(1);
        assertThat(journey.endTime(), is(new Date(journeyEnd.time())));
    }

    @Test
    public void formattedStartTimeTest() throws InterruptedException
    {
        createFakeJourney(1);
        assertThat(journey.formattedStartTime(), is(SimpleDateFormat.getInstance().format(new Date(journeyStart.time()))));
    }

    @Test
    public void formattedEndTimeTest() throws InterruptedException
    {
        createFakeJourney(1);
        assertThat(journey.formattedEndTime(), is(SimpleDateFormat.getInstance().format(new Date(journeyEnd.time()))));
    }

    @Test
    public void originIdTest() throws InterruptedException
    {
        createFakeJourney(1);
        assertThat(journey.originId(), is(readerOriginID));
    }

    @Test
    public void destinationIdTest() throws InterruptedException
    {
        createFakeJourney(1);
        assertThat(journey.destinationId(), is(readerDestinationID));
    }

}