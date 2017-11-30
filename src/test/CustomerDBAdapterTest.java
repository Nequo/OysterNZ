package test;

import com.tfl.billing.CustomerDBAdapter;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CustomerDBAdapterTest {

    CustomerDBAdapter database = new CustomerDBAdapter();


    @Test
    public void databaseIsInitialisedEmpty() {
        assertTrue(database.getList().isEmpty());
    }
}
