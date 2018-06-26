package com.lmz.exception;

/**
 * solr检查异常
 *
 */
public class SolrCheckException extends Exception {
	private static final long serialVersionUID = 1L;

	public SolrCheckException() {
		super();
	}

	public SolrCheckException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SolrCheckException(String message, Throwable cause) {
		super(message, cause);
	}

	public SolrCheckException(String message) {
		super(message);
	}

	public SolrCheckException(Throwable cause) {
		super(cause);
	}

	
}
