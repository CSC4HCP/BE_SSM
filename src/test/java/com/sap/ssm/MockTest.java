/**
 * 
 */
package com.sap.ssm;

import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.springframework.test.annotation.DirtiesContext;

/**
 * @author I326996 David Lin
 */
@DirtiesContext
public abstract class MockTest extends Test {

	/**
	 * Method run before every single test method to inject mocks.
	 */
	@Before
	public void injectMocks() {
		MockitoAnnotations.initMocks(this);
	}
}
