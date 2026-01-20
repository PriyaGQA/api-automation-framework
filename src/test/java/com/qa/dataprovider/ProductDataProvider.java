package com.qa.dataprovider;

import org.testng.annotations.DataProvider;

public class ProductDataProvider {
	
	@DataProvider(name = "productTestData")
	public Object[][] getProductTestData() {

	    return new Object[][]{
	    	{1, 200},
            {2, 200},
            {3, 200}
	    };
	}

	
	@DataProvider(name = "ProductIDs")
	public Object[][] getProductIDs(){
		
		return new Object[][] {
			
			{1},
			{2},
			{3}
			
		};
		
	}
	

}
