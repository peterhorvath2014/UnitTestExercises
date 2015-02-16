package basics;

import org.easymock.Capture;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClientTest {
    private static final int TEST_CLIENT_IDENT = 2;
    private static final String TEST_SERVICE_NAME = "testservice";
    private static final String TEST_CONTENT = "testContent";
    private static final String TEST_TIMESTAMP = "2011/09/23:13:28.01";

    private Client underTest;

    private Service service;
    private ContentFormat format;

    @Before
    public void setUp() {
        service = EasyMock.createMock(Service.class);
        underTest = new Client(service);
    }

    @Test
    public void testReleaseShouldReleaseService() {
        // GIVEN
        service.release();
        EasyMock.replay(service);
        // WHEN
        underTest.release();
        // THEN
        EasyMock.verify(service);
    }

    @Test
    public void testGetServiceNameShouldReturnServiceName() {
        // GIVEN
        EasyMock.expect(service.getName()).andReturn(TEST_SERVICE_NAME);
        EasyMock.replay(service);
        // WHEN
        String result = underTest.getServiceName();
        // THEN
        EasyMock.verify(service);
        Assert.assertEquals(TEST_SERVICE_NAME, result);
    }

    @Test
    public void testGetStartupShouldReturnStartupTime() {
        // GIVEN
        String dateFormat = "dateformat";
        EasyMock.expect(service.getTimestamp(dateFormat)).andReturn(TEST_TIMESTAMP);
        EasyMock.replay(service);
        // WHEN
        String result = underTest.getStartup(dateFormat);
        // THEN
        EasyMock.verify(service);
        Assert.assertEquals(TEST_TIMESTAMP, result);
    }

    @Test(expected = RuntimeException.class)
    public void testGetStartupShouldThrowExceptionWhenServiceNotConnected() {
        // GIVEN
        EasyMock.expect(service.getTimestamp(EasyMock.anyObject(String.class))).andThrow(new RuntimeException());
        EasyMock.replay(service);
        // WHEN
        underTest.getStartup("dateformat");
        // THEN exception thrown
    }

    @Test
    public void testGetContentShouldReturnContentProperly() {
        // GIVEN
        Long ident = 12L;
        //EasyMock.checkOrder(service, true);
        service.connect();
        EasyMock.expect(service.getContent(ident)).andReturn(TEST_CONTENT);
        service.release();

        EasyMock.replay(service);
        // WHEN
        String result = underTest.getContent(ident);
        // THEN
        EasyMock.verify(service);
        Assert.assertEquals(TEST_CONTENT, result);
    }

    @Test
    public void testGetClientContentShouldReturnContentFormatted() {
        // GIVEN
        format = EasyMock.createMock(ContentFormat.class);
        underTest = new Client(service, format);
        Capture<StringBuilder> content = new Capture<StringBuilder>();
        EasyMock.expect(format.format(EasyMock.capture(content))).andReturn(TEST_CONTENT);
        EasyMock.replay(format);
        // WHEN
        String result = underTest.getClientContentFormatted();
        // THEN
        EasyMock.verify(format);
        Assert.assertEquals(String.format("This is a client %s!", TEST_CLIENT_IDENT), content.getValue().toString());

        Assert.assertEquals(TEST_CONTENT, result);
    }
}
