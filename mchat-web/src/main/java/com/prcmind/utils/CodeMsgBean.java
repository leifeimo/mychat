package com.prcmind.utils;



/**
 *
 * @description:
 * @author: leichang
 * @param <T>
 * @date: 2015年11月9日 下午2:17:37
 */
public class CodeMsgBean<T> {
	private int code;
	private String msg;
	private T data;

	public CodeMsgBean() {

	}

	public CodeMsgBean(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public CodeMsgBean(int code, String msg, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
//	@SuppressWarnings("rawtypes")
//	public static CodeMsgBean fromModel(CodeMsg codeMsg){
//		CodeMsgBean codeMsgBean = new CodeMsgBean();
//		if(codeMsg != null){
//			codeMsgBean.setCode(codeMsg.getCode());
//			codeMsgBean.setMsg(codeMsg.getMsg());
//		}
//		return codeMsgBean;
//	}
}

