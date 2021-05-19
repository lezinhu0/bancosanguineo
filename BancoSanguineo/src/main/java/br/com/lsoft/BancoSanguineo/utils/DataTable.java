package br.com.lsoft.BancoSanguineo.utils;

import java.lang.reflect.Method;
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

	public static <T> DataTable fromIterable(Iterable<T> iterable) {
		if (!iterable.iterator().hasNext()) {
			return null;
		}
		
		LinkedList<Object> headers = new LinkedList<>();
		T tempObj = iterable.iterator().next();
		for (Method method : tempObj.getClass().getMethods()) {
			String methodName = method.getName();
			if (methodName.startsWith("get") && !methodName.equals("getClass"))
				headers.add(methodName.substring(3));
		}
		
		LinkedList<LinkedList<Object>> data = new LinkedList<>();
		for (Object obj : iterable) {
			LinkedList<Object> linha = new LinkedList<>();
			for (Object header : headers) {
				try {
					Object methodReturn = obj.getClass().getMethod("get" + header.toString(), null).invoke(obj);
					if (methodReturn != null)
						linha.add(methodReturn.toString());
					else
						linha.add("null");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			data.add(linha);
		}
		return new DataTable(data, headers);
	}

}
