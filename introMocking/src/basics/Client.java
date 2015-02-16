package basics;

public class Client {
    private static final int IDENT = 2;
    private Service service;
    private ContentFormat format;

    public Client(Service service) {
        this.service = service;
    }

    public Client(Service service, ContentFormat format) {
        this(service);
        this.format = format;
    }

    public void release() {
        service.release();
    }

    public String getServiceName() {
        return service.getName();
    }

    public String getContent(Long ident) {
        service.connect();
        String result = service.getContent(ident);
        service.release();
        return result;
    }

    public String getContentFormatted(Long ident) {
        String content = getContent(ident);
        String result = format.format(content);
        return result;
    }

    public String getClientContentFormatted() {
        StringBuilder content = new StringBuilder();
        content.append("This is a client ");
        content.append(IDENT);
        content.append("!");
        return format.format(content);
    }

    public String getStartup(String dateFormat) {
        String result = service.getTimestamp(dateFormat);
        return result;
    }
}
