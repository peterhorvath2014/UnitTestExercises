package basics;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ClientTestMockito {

	private static final String TEST_SERVICE_NAME = "testservice";
	private static final String TEST_CONTENT = "testContent";
	private static final String TEST_DATE_FORMAT = "dateformat";
	private static final String TEST_TIMESTAMP = "2011/09/23:13:28.01";

	private Client underTest;

	@Mock
	private Service service;

	@BeforeMethod
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		underTest = new Client(service);
	}

	@Test
	public void testReleaseShouldReleaseService() {
		// GIVEN in setup
		// WHEN
		underTest.release();
		// THEN
		Mockito.verify(service).release();
	}

	@Test
	public void testGetServiceNameShouldReturnServiceName() {
		// GIVEN
		Mockito.when(service.getName()).thenReturn(TEST_SERVICE_NAME);
		// WHEN
		String result = underTest.getServiceName();
		// THEN
		Mockito.verify(service).getName();
		Assert.assertEquals(TEST_SERVICE_NAME, result);
	}

	@Test
	public void testGetStartupShouldReturnStartupTime() {
		// GIVEN
		Mockito.when(service.getTimestamp(Mockito.anyString())).thenReturn(
				TEST_TIMESTAMP);
		// WHEN
		String result = underTest.getStartup(TEST_DATE_FORMAT);
		// THEN
		Mockito.verify(service).getTimestamp(TEST_DATE_FORMAT);
		Assert.assertEquals(TEST_TIMESTAMP, result);
	}

	@Test(expectedExceptions = RuntimeException.class)
	public void testGetStartupShouldThrowExceptionWhenServiceNotConnected() {
		// GIVEN
		Mockito.when(service.getTimestamp(Mockito.anyString())).thenThrow(
				new RuntimeException());
		// WHEN
		underTest.getStartup(TEST_DATE_FORMAT);
		// THEN exception thrown
	}

	@Test
	public void testGetContentShouldReturnContentProperly() {
		// GIVEN
		Long ident = 12L;
		Mockito.when(service.getContent(Mockito.anyLong())).thenReturn(
				TEST_CONTENT);
		// WHEN
		String result = underTest.getContent(ident);
		// THEN
		Mockito.verify(service).getContent(ident);
		Mockito.verify(service).connect();

		Mockito.verify(service).release();

		Assert.assertEquals(TEST_CONTENT, result);
	}

}
