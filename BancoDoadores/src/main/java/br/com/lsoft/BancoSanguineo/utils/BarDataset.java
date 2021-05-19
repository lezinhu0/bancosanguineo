package br.com.lsoft.BancoSanguineo.utils;

import java.util.LinkedList;
import java.util.List;

public class BarDataset {

	private String label;
	private String backgroundColor = "rgb(47 203 214)";
	private String borderColor = "rgb(47 203 214)";
	private String hoverBackgroundColor = "rgb(17 173 184)";
	private List<Double> data = new LinkedList<>();
	private Long borderwidth = 2l;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}

	public List<Double> getData() {
		return data;
	}

	public void setData(List<Double> data) {
		this.data = data;
	}

	public Long getBorderwidth() {
		return borderwidth;
	}

	public void setBorderwidth(Long borderwidth) {
		this.borderwidth = borderwidth;
	}

	public String getHoverBackgroundColor() {
		return hoverBackgroundColor;
	}

	public void setHoverBackgroundColor(String hoverBackgroundColor) {
		this.hoverBackgroundColor = hoverBackgroundColor;
	}

}
