package com.bing.funread.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Map;

/**
 * 统一返回结果集
 * @param <T>
 */
@ApiModel(value="返回结果对象")
public class Result<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 返回状态码
	 */
	@ApiModelProperty(value = "返回状态码")
	private String code;

	/**
	 * 返回信息
	 */
	@ApiModelProperty(value = "返回信息")
	private String message;

	/**
	 * 返回值
	 */
	@ApiModelProperty(value = "返回值")
	private T value;

	/**
	 * 额外返回信息
	 */
	@ApiModelProperty(value = "额外返回信息")
	private Map<String, Object> extraInfo;
	
	public Result() {
		super();
	}
	
	public Result(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public Result(String code, String message, T value) {
		super();
		this.code = code;
		this.message = message;
		this.value = value;
	}

	public Result(String code, String message, T value, Map<String, Object> extraInfo) {
		super();
		this.code = code;
		this.message = message;
		this.value = value;
		this.extraInfo = extraInfo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public Map<String, Object> getExtraInfo() {
		return extraInfo;
	}

	public void setExtraInfo(Map<String, Object> extraInfo) {
		this.extraInfo = extraInfo;
	}

	@Override
	public String toString() {
		return "Result{" +
				"code='" + code + '\'' +
				", message='" + message + '\'' +
				", value=" + value +
				", extraInfo=" + extraInfo +
				'}';
	}
}
