package com.tfl.billing;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class JourneyPriceCalculator
{
    //Peak long: £3.80 ; Peak short: £2.90 ; Off-peak long: £2.70 ; Off-peak short: £1.60
    static public final BigDecimal OFF_PEAK_SHORT_JOURNEY_PRICE = new BigDecimal(1.60).setScale(2, BigDecimal.ROUND_HALF_UP);
    static public final BigDecimal OFF_PEAK_LONG_JOURNEY_PRICE = new BigDecimal(2.70).setScale(2, BigDecimal.ROUND_HALF_UP);
    static public final BigDecimal PEAK_LONG_JOURNEY_PRICE = new BigDecimal(3.80).setScale(2, BigDecimal.ROUND_HALF_UP);
    static public final BigDecimal PEAK_SHORT_JOURNEY_PRICE = new BigDecimal(2.90).setScale(2, BigDecimal.ROUND_HALF_UP);
    static public final BigDecimal PEAK_CAP_PRICE = new BigDecimal(9.00).setScale(2, BigDecimal.ROUND_HALF_UP);
    static public final BigDecimal OFF_PEAK_CAP_PRICE = new BigDecimal(7.00).setScale(2, BigDecimal.ROUND_HALF_UP);
    static private final int LONG_JOURNEY_MINIMUM_DURATION_IN_MIN = 25;


    static public BigDecimal TotalPriceFor(List<Journey> journeys)
    {
        boolean wasAnyJourneyPeak = false;

        BigDecimal customerTotal = new BigDecimal(0);
        for (Journey journey : journeys)//iterates through journeys
        {
            //add peak/offPeak price for each journey_event to total price
            if (peak(journey))
            {
                wasAnyJourneyPeak = true;
                if (journey.durationSeconds() < 60 * LONG_JOURNEY_MINIMUM_DURATION_IN_MIN)
                {
                    customerTotal = customerTotal.add(PEAK_SHORT_JOURNEY_PRICE);
                } else
                {
                    customerTotal = customerTotal.add(PEAK_LONG_JOURNEY_PRICE);
                }

            } else
            {
                if (journey.durationSeconds() < 60 * LONG_JOURNEY_MINIMUM_DURATION_IN_MIN)
                {
                    customerTotal = customerTotal.add(OFF_PEAK_SHORT_JOURNEY_PRICE);
                } else
                {
                    customerTotal = customerTotal.add(OFF_PEAK_LONG_JOURNEY_PRICE);
                }

            }
        }

        return checkForCaps(customerTotal, wasAnyJourneyPeak);
    }

    static private BigDecimal checkForCaps(BigDecimal customerTotal, boolean wasAnyJourneyPeak)
    {
        if (wasAnyJourneyPeak && customerTotal.compareTo(PEAK_CAP_PRICE) != -1)
        {
            return PEAK_CAP_PRICE;
        } else if (!wasAnyJourneyPeak && customerTotal.compareTo(OFF_PEAK_CAP_PRICE) != -1)
        {
            return OFF_PEAK_CAP_PRICE;
        }

        return customerTotal;
    }

    static private boolean peak(Journey journey)
    {
        return peak(journey.startTime()) || peak(journey.endTime());
    }


    static private boolean peak(Date time)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return (hour >= 6 && hour <= 9) || (hour >= 17 && hour <= 19);
    }
}