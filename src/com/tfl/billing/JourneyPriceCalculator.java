package com.tfl.billing;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class JourneyPriceCalculator
{
    //Peak long: £3.80 ; Peak short: £2.90 ; Off-peak long: £2.70 ; Off-peak short: £1.60
    static private final BigDecimal OFF_PEAK_SHORT_JOURNEY_PRICE = new BigDecimal(1.60);
    static private final BigDecimal OFF_PEAK_LONG_JOURNEY_PRICE = new BigDecimal(2.70);
    static private final BigDecimal PEAK_LONG_JOURNEY_PRICE = new BigDecimal(3.80);
    static private final BigDecimal PEAK_SHORT_JOURNEY_PRICE = new BigDecimal(2.90);


    static public BigDecimal TotalJourneyPrice(List<Journey> journeys)
    {
        boolean wasAnyJourneyPeak = false;

        BigDecimal customerTotal = new BigDecimal(0);
        for (Journey journey : journeys)//iterates through journeys
        {
            BigDecimal journeyPrice;
            //add peak/offPeak price for each journey to total price
            if (peak(journey))
            {
                wasAnyJourneyPeak = true;
                if (journey.durationSeconds() < 60 * 2)
                {
                    journeyPrice = PEAK_SHORT_JOURNEY_PRICE;
                } else {
                    journeyPrice = PEAK_LONG_JOURNEY_PRICE;
                }

            } else
            {
                if (journey.durationSeconds() < 60 * 2)
                {
                    journeyPrice = OFF_PEAK_SHORT_JOURNEY_PRICE;
                } else {
                    journeyPrice = OFF_PEAK_LONG_JOURNEY_PRICE;
                }

            }
            customerTotal = customerTotal.add(journeyPrice);
        }

        //checks for caps
        if (wasAnyJourneyPeak && customerTotal.compareTo(new BigDecimal(9.00)) != -1)
        {
            return new BigDecimal(9.00);
        } else if (!wasAnyJourneyPeak && customerTotal.compareTo(new BigDecimal(7.00)) != -1)
        {
            return new BigDecimal(7.00);
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