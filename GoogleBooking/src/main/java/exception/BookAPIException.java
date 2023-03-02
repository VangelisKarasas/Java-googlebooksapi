package exception;

	public class BookAPIException extends Exception {
		public BookAPIException() {
	        super();
	    }

	    public BookAPIException(String message) {
	        super(message);
	    }

	    public BookAPIException(String message, Throwable cause) {
	        super(message, cause);
	    }

	    public BookAPIException(Throwable cause) {
	        super(cause);
	    }

	    protected BookAPIException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	        super(message, cause, enableSuppression, writableStackTrace);
	    }

}
