package data_providers;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderClass {
    @DataProvider(name= "SingleValue")
    public Object[][] storeSingleValue() {
        return new Object[][] {
                {"Rifat"},
                {"Mohammad"},
                {"Ashraf"}
        };
    }
    @DataProvider(name ="MultipleValues")
    public Object[][] storeMultipleValues() {
        return new Object[][] {
                {"Rifat", "Florida" , 33018},
                {"Farid", "New York", 12458},
                {"Ashraf", "Bangladesh", 1205}
        };
    }
    
    public void run (String name) {
        System.out.println("[Single Value] name is : " + name);
    }

    @Test (dataProvider = "MultipleValues")
    public void run (String name, String state, int zipCode) {
        System.out.println("[Multiple Value] name is : " + name);
        System.out.println("[Multiple Value] state is : " + state);
        System.out.println("[Multiple Value] zipCode is : " + zipCode);
    }
}