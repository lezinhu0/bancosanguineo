package br.com.lsoft.BancoSanguineo.utils;

import java.util.LinkedList;
import java.util.List;

public class PieDataset {
	
	private String label;
	private List<Double> data;
	private List<String> backgroundColor;
	private Integer hoverOffset = 4;
	
	public PieDataset(String label, List<Double> data) {
		this.backgroundColor = new LinkedList<>();
		this.backgroundColor.add("rgb(54, 162, 235)");
		this.label = label;
		this.data = data;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<Double> getData() {
		return data;
	}

	public void setData(List<Double> data) {
		this.data = data;
	}

	public List<String> getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(List<String> backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public Integer getHoverOffset() {
		return hoverOffset;
	}

	public void setHoverOffset(Integer hoverOffset) {
		this.hoverOffset = hoverOffset;
	}

}
