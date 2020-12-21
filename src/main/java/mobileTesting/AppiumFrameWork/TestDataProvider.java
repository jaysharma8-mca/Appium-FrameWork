package mobileTesting.AppiumFrameWork;

import org.testng.annotations.DataProvider;

public class TestDataProvider {
	
	@DataProvider(name="InputData")
	public Object[][] getDataforEditField() {
		Object [][] obj = new Object[][]{
			{"Neha Singh"}, {"@#$%"}
		};
		
		return obj;
	}
}
