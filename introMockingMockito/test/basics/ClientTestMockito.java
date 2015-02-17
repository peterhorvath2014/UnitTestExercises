package basics;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class ClientTestMockito {

	private static final String TEST_SERVICE_NAME = "testservice";
	private static final String TEST_CONTENT = "testContent";
	private static final String TEST_DATE_FORMAT = "dateformat";
	private static final String TEST_TIMESTAMP = "2011/09/23:13:28.01";

	private Client underTest;

	@Mock
	private Service service;

	@Before
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
		BDDMockito.given(service.getName()).willReturn(TEST_SERVICE_NAME);
		// WHEN
		String result = underTest.getServiceName();
		// THEN
		BDDMockito.verify(service).getName();
		Assert.assertEquals(TEST_SERVICE_NAME, result);
	}

	@Test
	public void testGetStartupShouldReturnStartupTime() {
		// GIVEN
		BDDMockito.given(service.getTimestamp(Mockito.anyString())).willReturn(
				TEST_TIMESTAMP);
		// WHEN
		String result = underTest.getStartup(TEST_DATE_FORMAT);
		// THEN
		BDDMockito.verify(service).getTimestamp(TEST_DATE_FORMAT);
		Assert.assertEquals(TEST_TIMESTAMP, result);
	}

	@Test(expected = RuntimeException.class)
	public void testGetStartupShouldThrowExceptionWhenServiceNotConnected() {
		// GIVEN
		BDDMockito.given(service.getTimestamp(Mockito.anyString())).willThrow(
				new RuntimeException());
		// WHEN
		underTest.getStartup(TEST_DATE_FORMAT);
		// THEN exception thrown
	}

	@Test
	public void testGetContentShouldReturnContentProperly() {
		// GIVEN
		Long ident = 12L;
		BDDMockito.given(service.getContent(Mockito.anyLong())).willReturn(
				TEST_CONTENT);
		// WHEN
		String result = underTest.getContent(ident);
		// THEN
		BDDMockito.verify(service).getContent(ident);
		BDDMockito.verify(service).connect();

		BDDMockito.verify(service).release();

		Assert.assertEquals(TEST_CONTENT, result);
	}

}
