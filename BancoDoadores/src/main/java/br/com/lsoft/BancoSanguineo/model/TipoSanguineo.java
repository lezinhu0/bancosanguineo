package br.com.lsoft.BancoSanguineo.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OrderColumn;

@Entity
public class TipoSanguineo {

	@Id
	private String codigo;
	private String descricao;
	@ElementCollection
	@OrderColumn
	private List<String> doaPara;
	@ElementCollection
	@OrderColumn
	private List<String> recebeDe;

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

}
