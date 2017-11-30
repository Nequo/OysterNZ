package test;

import com.tfl.billing.CustomerDBAdapter;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CustomerDBAdapterTest {

    @Test
    public void databaseIsInitialisedEmpty() {
        CustomerDBAdapter database = new CustomerDBAdapter();
        assertTrue(database.getList().isEmpty());
    }

    public void 
}
