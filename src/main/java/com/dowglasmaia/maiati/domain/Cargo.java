package com.dowglasmaia.maiati.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cargo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Informe o Nome do Cargo")
	@Size(min = 3, max = 60, message = "O nome do Cargo deve ter entre {min} e {max} caracteres.")
	@Column(length = 50, nullable = false, unique = true)
	private String nome;

	@NotNull(message = "Selecione o Departemento relativo ao Cargo.")
	@JoinColumn(nullable = false)
	@ManyToOne
	private Departamento departamento;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "cargo")
	private List<Funcionario> funcionarios = new ArrayList<>();

	public Cargo() {
		// TODO Auto-generated constructor stub
	}

	public Cargo(Long id,
			@NotBlank(message = "Informe o Nome do Cargo") @Size(min = 3, max = 60, message = "O nome do Cargo deve ter entre {min} e {max} caracteres.") String nome,
			@NotNull(message = "Selecione o Departemento relativo ao Cargo.") Departamento departamento) {
		super();
		this.id = id;
		this.nome = nome;
		this.departamento = departamento;
	}

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

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cargo other = (Cargo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
