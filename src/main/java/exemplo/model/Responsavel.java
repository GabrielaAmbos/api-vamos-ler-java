package exemplo.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

	@Entity
	public class Responsavel implements Serializable{
		
		private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		
		private Integer idResponsavel;
		private String nomeResponsavel;
		private String email;
		private String senha;
		
		@JsonIgnore
		@OneToMany(mappedBy="responsavel")
		private List<Crianca> crianca;

		public Integer getIdResponsavel() {
			return idResponsavel;
		}

		public void setIdResponsavel(Integer idResponsavel) {
			this.idResponsavel = idResponsavel;
		}

		public String getNomeResponsavel() {
			return nomeResponsavel;
		}

		public void setNomeResponsavel(String nomeResponsavel) {
			this.nomeResponsavel = nomeResponsavel;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getSenha() {
			return senha;
		}

		public void setSenha(String senha) {
			this.senha = senha;
		}

		public List<Crianca> getCrianca() {
			return crianca;
		}

		public void setCrianca(List<Crianca> crianca) {
			this.crianca = crianca;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		public Responsavel() {
			super();
			// TODO Auto-generated constructor stub
		}
		
	}