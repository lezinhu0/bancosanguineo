package br.com.lsoft.BancoSanguineo.utils;

import java.util.Collection;
import java.util.LinkedList;

public class DataTable {

	private LinkedList<LinkedList<Object>> data;
	private LinkedList<Object> headers;

	public DataTable() {
		this.data = new LinkedList<LinkedList<Object>>();
		this.headers = new LinkedList<Object>();
	}

	public DataTable(LinkedList<LinkedList<Object>> data) {
		this.data = data;
	}

	public DataTable(LinkedList<LinkedList<Object>> data, LinkedList<Object> headers) {
		this.data = data;
		this.headers = headers;
	}

	public DataTable setData(LinkedList<LinkedList<Object>> data) {
		this.data = data;
		return this;
	}

	public Collection<LinkedList<Object>> getData() {
		return data;
	}

	public DataTable setHeaders(LinkedList<Object> headers) {
		this.headers = headers;
		return this;
	}

	public LinkedList<Object> getHeaders() {
		return headers;
	}

}
