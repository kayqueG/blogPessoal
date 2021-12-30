package br.org.generation.blogpessoal.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity // classe gera uma tabela no banco (create table)
@Table(name = "tb_postagens") // tb_postagens
public class Postagem {

	@Id // informando que é primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // configurando o auto incremento
	private Long id;
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;

	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Usuario usuario;

	@NotBlank(message = "O atributo titulo é obrigatório")
	@Size(min = 5, max = 100, message = "O atributo titulo deve ter no minimo 5 e no máximo 100 caracteres")
	private String titulo;

	
	@NotNull(message = "O atributo texto é obrigatório")
	@Size(min = 10, max = 1000, message = "O atributo texto deve ter no minimo 10 e no máximo 1000 caracteres")
	private String texto;
	
	@UpdateTimestamp
	private LocalDate data;
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Tema getTema() {
		return tema;
	}


	public void setTema(Tema tema) {
		this.tema = tema;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getTexto() {
		return texto;
	}


	public void setTexto(String texto) {
		this.texto = texto;
	}


	public LocalDate getData() {
		return data;
	}


	public void setData(LocalDate data) {
		this.data = data;
	}


}
	