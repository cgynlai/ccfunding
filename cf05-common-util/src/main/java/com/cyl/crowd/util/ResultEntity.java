package com.cyl.crowd.util;

/**
 * 
 * 统一Ajax请求返回的结果
 * @param <T>
 */
public class ResultEntity<T> {
	public static final String SUCCESS = "SUCCESS";
	public static final String FAILED = "FAILED";

	/**
	 * è¿”å›žæ“�ä½œç»“æžœä¸ºæˆ�åŠŸï¼Œä¸�å¸¦æ•°æ�®
	 * 
	 * @return
	 */
	public static <E> ResultEntity<E> successWithoutData() {
		return new ResultEntity<E>(SUCCESS, null, null);
	}

	/**
	 * è¿”å›žæ“�ä½œç»“æžœä¸ºæˆ�åŠŸï¼Œæ�ºå¸¦æ•°æ�®
	 * 
	 * @param data
	 * @return
	 */
	public static <E> ResultEntity<E> successWithData(E data) {
		return new ResultEntity<E>(SUCCESS, null, data);
	}

	/**
	 * è¿”å›žæ“�ä½œç»“æžœä¸ºå¤±è´¥ï¼Œä¸�å¸¦æ•°æ�®
	 * 
	 * @param message
	 * @return
	 */
	public static <E> ResultEntity<E> failed(String message) {
		return new ResultEntity<E>(FAILED, message, null);
	}

	private String result;
	private String message;
	private T data;

	public ResultEntity() {
	}

	public ResultEntity(String result, String message, T data) {
		super();
		this.result = result;
		this.message = message;
		this.data = data;
	}

	@Override
	public String toString() {
		return "AjaxResultEntity [result=" + result + ", message=" + message
				+ ", data=" + data + "]";
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
