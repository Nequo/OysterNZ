package src.test;

import com.tfl.billing.TravelTracker;
import org.junit.Rule;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;



public class TravelTrackerTest {
    TravelTracker travelTracker = new TravelTracker();

    @Rule public JUnitRuleMockery context = new JUnitMockery();


}
