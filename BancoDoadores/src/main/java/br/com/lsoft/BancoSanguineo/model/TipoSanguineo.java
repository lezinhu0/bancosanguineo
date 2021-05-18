package br.com.lsoft.BancoSanguineo.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class TipoSanguineo {

	@Id
	private String codigo;
	private String descricao;
	@ManyToMany
	private List<TipoSanguineo> doaPara;
	
	public TipoSanguineo() {
	}

	public TipoSanguineo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDoaPara(LinkedList<TipoSanguineo> doaPara) {
		this.doaPara = doaPara;
	}

	@Override
	public String toString() {
		return "TipoSanguineo [codigo=" + codigo + ", descricao=" + descricao + "]";
	}

}
