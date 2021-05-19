package br.com.lsoft.BancoSanguineo.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.lsoft.BancoSanguineo.interfaces.DataTableOrder;

@Entity
public class Candidato {

	@Id
	private String rg;
	private String nome;
	private String cpf;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data_nasc;
	private String sexo;
	private String mae;
	private String pai;
	private String email;
	private String cep;
	private String endereco;
	private String numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String telefone_fixo;
	private String celular;
	private BigDecimal altura;
	private BigDecimal peso;
	private String tipo_sanguineo;
	@ManyToOne
	private TipoSanguineo tipoSanguineo;

	@Override
	public String toString() {
		return "Candidato [nome=" + nome + ", cpf=" + cpf + ", rg=" + rg + ", data_nasc=" + data_nasc
				+ ", tipo_sanguineo=" + tipo_sanguineo + ", tipoSanguineo=" + tipoSanguineo + "]";
	}

	@DataTableOrder(value = 3)
	public Integer getIdade() {
		return (int) ((System.currentTimeMillis() - this.data_nasc.getTime()) / 365 / 24 / 60 / 60 / 1000);
	}

	@DataTableOrder(value = 4)
	public BigDecimal getIMC() {
		return this.peso.divide(this.altura, 2, RoundingMode.HALF_EVEN).divide(this.altura, 2, RoundingMode.HALF_EVEN);
	}

	@DataTableOrder(value = 17)
	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	@DataTableOrder(value = 2)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@DataTableOrder(value = 18)
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@DataTableOrder(value = 19)
	public Date getData_nasc() {
		return data_nasc;
	}

	public void setData_nasc(Date data_nasc) {
		this.data_nasc = data_nasc;
	}

	@DataTableOrder(value = 5)
	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	@DataTableOrder(value = 20)
	public String getMae() {
		return mae;
	}

	public void setMae(String mae) {
		this.mae = mae;
	}

	@DataTableOrder(value = 21)
	public String getPai() {
		return pai;
	}

	public void setPai(String pai) {
		this.pai = pai;
	}

	@DataTableOrder(value = 16)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@DataTableOrder(value = 8)
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@DataTableOrder(value = 11)
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@DataTableOrder(value = 12)
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@DataTableOrder(value = 13)
	public String getBairro() {
		return bairro;
	}
	
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@DataTableOrder(value = 10)
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@DataTableOrder(value = 9)
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@DataTableOrder(value = 14)
	public String getTelefone_fixo() {
		return telefone_fixo;
	}

	public void setTelefone_fixo(String telefone_fixo) {
		this.telefone_fixo = telefone_fixo;
	}

	@DataTableOrder(value = 15)
	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	@DataTableOrder(value = 6)
	public BigDecimal getAltura() {
		return altura;
	}

	public void setAltura(BigDecimal altura) {
		this.altura = altura;
	}

	@DataTableOrder(value = 7)
	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	@DataTableOrder(value = 1)
	public String getTipo_sanguineo() {
		return tipo_sanguineo;
	}

	public void setTipo_sanguineo(String tipo_sanguineo) {
		this.tipo_sanguineo = tipo_sanguineo;
	}

	public TipoSanguineo getTipoSanguineo() {
		return tipoSanguineo;
	}

	public void setTipoSanguineo(TipoSanguineo tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}

}
