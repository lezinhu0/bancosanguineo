package br.com.lsoft.BancoSanguineo.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import br.com.lsoft.BancoSanguineo.interfaces.DataTableOrder;

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

	public static <T, E> DataTable fromIterable(Iterable<T> iterable) {
		if (!iterable.iterator().hasNext()) {
			return null;
		}
		
		Method[] methods = iterable.iterator().next().getClass().getMethods();
		ArrayList<Method> list = new ArrayList<>(Arrays.asList(methods));
		
		Collections.sort(list, new Comparator<Method>() {
			@Override
			public int compare(Method arg0, Method arg1) {
				if (arg0 == null && arg1 == null) {
					return 0;
				} else if (arg0.getAnnotation(DataTableOrder.class) == null) {
					return 1;
				} else if (arg1.getAnnotation(DataTableOrder.class) == null) {
					return -1;
				} else if (arg0.getAnnotation(DataTableOrder.class).value() < arg1.getAnnotation(DataTableOrder.class).value()) {
					return -1;
				} else {
					return 1;
				}
			}
		});
		
		
		LinkedList<Object> headers = new LinkedList<>();
		for (Method method : list) {
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
