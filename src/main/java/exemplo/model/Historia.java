package exemplo.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;


	@Entity
	public class Historia implements Serializable{
		
	
		private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int idHistoria;
		
		private int progressoHistoria;
		private int paginas;
		private String nomeHistoria;
		private String conteudoHistoria;
		
		@JsonIgnore
		@OneToMany(mappedBy="historia")
		private List<Atividade> atividade;
		
		@JsonIgnore
		@OneToMany
		private List<Crianca> crianca;

		public int getIdHistoria() {
			return idHistoria;
		}

		public void setIdHistoria(int idHistoria) {
			this.idHistoria = idHistoria;
		}

		public int getProgressoHistoria() {
			return progressoHistoria;
		}

		public void setProgressoHistoria(int progressoHistoria) {
			this.progressoHistoria = progressoHistoria;
		}

		public int getPaginas() {
			return paginas;
		}

		public void setPaginas(int paginas) {
			this.paginas = paginas;
		}

		public String getNomeHistoria() {
			return nomeHistoria;
		}

		public void setNomeHistoria(String nomeHistoria) {
			this.nomeHistoria = nomeHistoria;
		}

		public String getConteudoHistoria() {
			return conteudoHistoria;
		}

		public void setConteudoHistoria(String conteudoHistoria) {
			this.conteudoHistoria = conteudoHistoria;
		}

		public List<Atividade> getAtividade() {
			return atividade;
		}

		public void setAtividade(List<Atividade> atividade) {
			this.atividade = atividade;
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

		public Historia() {
			
		}

		
}