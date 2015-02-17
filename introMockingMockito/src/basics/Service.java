package basics;

public interface Service {
	void release();

	String getName();

	void connect();

	String getContent(Long ident);

	String getTimestamp(String dateFormat);
}
