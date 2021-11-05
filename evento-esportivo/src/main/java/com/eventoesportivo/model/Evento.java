package com.eventoesportivo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Evento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull(message="Nome não pode ser nulo")
	@NotEmpty(message = "Nome não pode ser vazio")
	private String nome;

	@NotNull(message = "Endereço não pode ser nulo")
	@NotEmpty(message = "Endereço não pode ser vazio")
	private String endereco;
	
	@NotNull(message = "A data não pode ser nulo")
	@NotEmpty(message = "A data não pode ser vazio")
	private String data;
	
	@NotNull(message = "A hora não pode ser nulo")
	@NotEmpty(message = "A hora não pode ser vazio")
	private String hora;
	
	@OneToMany(mappedBy="evento", orphanRemoval= true, cascade = CascadeType.ALL)
	private List<Convidado> convidados;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public List<Convidado> getConvidados() {
		return convidados;
	}

	public void setConvidados(List<Convidado> convidados) {
		this.convidados = convidados;
	}
	
}
