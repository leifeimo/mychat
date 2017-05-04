package com.prcmind.utils;

import java.util.List;

public class PageData<T> {

	private int total;// 总记录数
	private List<T> rows;// 对应的当前页记录


	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "PageData [total=" + total + ", rows=" + rows + "]";
	}
	
}
