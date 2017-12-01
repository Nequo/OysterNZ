package test;

import com.tfl.billing.*;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class JourneyPriceCalculatorTest
{
    private final FakeJourneyCreator fakeJourneyCreator = new FakeJourneyCreator();

    @Test
    public void  chargesTheCorrectPriceForSingleOffPeakLongJourney()
    {
        assertThat(JourneyPriceCalculator.TotalJourneyPrice(fakeJourneyCreator.getFakeJourneys(1, false, fakeJourneyCreator.longJourneys)),
                is(JourneyPriceCalculator.OFF_PEAK_LONG_JOURNEY_PRICE));
    }

    @Test
    public void  chargesTheCorrectPriceForSinglePeakLongJourney()
    {
        assertThat(JourneyPriceCalculator.TotalJourneyPrice(fakeJourneyCreator.getFakeJourneys(1, true, fakeJourneyCreator.longJourneys)),
                is(JourneyPriceCalculator.PEAK_LONG_JOURNEY_PRICE));
    }

    @Test
    public void  chargesTheCorrectPriceForSingleOffPeakShortJourney()
    {
        assertThat(JourneyPriceCalculator.TotalJourneyPrice(fakeJourneyCreator.getFakeJourneys(1, false, fakeJourneyCreator.shortJourneys)),
                is(JourneyPriceCalculator.OFF_PEAK_SHORT_JOURNEY_PRICE));
    }

    @Test
    public void  chargesTheCorrectPriceForPeakShortJourney()
    {
        assertThat(JourneyPriceCalculator.TotalJourneyPrice(fakeJourneyCreator.getFakeJourneys(1, true, fakeJourneyCreator.shortJourneys)),
                is(JourneyPriceCalculator.PEAK_SHORT_JOURNEY_PRICE));
    }

    @Test
    public void  chargesTheCorrectPriceForCappedOffPeak()
    {
        assertThat(JourneyPriceCalculator.TotalJourneyPrice(fakeJourneyCreator.getFakeJourneys(5, false, fakeJourneyCreator.shortJourneys)),
                is(JourneyPriceCalculator.OFF_PEAK_CAP_PRICE));
    }

    @Test
    public void  chargesTheCorrectPriceForCappedPeak()
    {
        assertThat(JourneyPriceCalculator.TotalJourneyPrice(fakeJourneyCreator.getFakeJourneys(5, true, fakeJourneyCreator.shortJourneys)),
                is(JourneyPriceCalculator.PEAK_CAP_PRICE));
    }
}
