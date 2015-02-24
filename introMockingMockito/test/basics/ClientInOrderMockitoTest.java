package basics;

import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ClientInOrderMockitoTest {
	private static final String TEST_CONTENT = "testContent";
	private static final String TEST_CONTENT_FORMATTED = "<testContent/>";

	private Client underTest;

	@Mock
	private Service service;
	@Mock
	private ContentFormat format;

	@BeforeMethod(groups = {"basic"} )
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		underTest = new Client(service, format);
	}

	@Test(groups = {"basic"} )
	public void testGetContentFormattedShouldReturnContentProperly() {
		// GIVEN
		Long ident = 12L;
		Mockito.when(service.getContent(ident)).thenReturn(TEST_CONTENT);
		Mockito.when(format.format(Mockito.anyString())).thenReturn(
				TEST_CONTENT_FORMATTED);
		ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
		// WHEN
		String result = underTest.getContentFormatted(ident);
		// THEN
		InOrder inorder = Mockito.inOrder(service, format);
		inorder.verify(service).connect();
		inorder.verify(service).getContent(captor.capture());
		inorder.verify(service).release();
		inorder.verify(format).format(TEST_CONTENT);
		Assert.assertEquals(TEST_CONTENT_FORMATTED, result);
		Assert.assertEquals(ident, captor.getValue());
	}
}
