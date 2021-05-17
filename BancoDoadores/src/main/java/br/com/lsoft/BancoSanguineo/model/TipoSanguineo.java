package br.com.lsoft.BancoSanguineo.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;

@Entity
public class TipoSanguineo {

	@Id
	private String codigo;
	private String descricao;
	@ElementCollection(fetch = FetchType.LAZY)
	private List<TipoSanguineo> doaPara;
	@ElementCollection(fetch = FetchType.LAZY)
	private List<TipoSanguineo> recebeDe;

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
