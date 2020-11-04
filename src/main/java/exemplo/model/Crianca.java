package exemplo.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;


	@Entity
	public class Crianca implements Serializable{
		
	
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int idCrinca;
		private String nomeCrianca;
		
		@ManyToOne
		@JoinColumn(name="idResponsavel")
		private Responsavel responsavel;
		
		@ManyToMany
		@JoinColumn(name="idAtividade")
		private List<Atividade> atividade;
		
		@ManyToMany
		@JoinColumn(name ="idHistoria")
		private List<Historia> historia;

		public int getIdCrinca() {
			return idCrinca;
		}

		public void setIdCrinca(int idCrinca) {
			this.idCrinca = idCrinca;
		}

		public String getNomeCrianca() {
			return nomeCrianca;
		}

		public void setNomeCrianca(String nomeCrianca) {
			this.nomeCrianca = nomeCrianca;
		}


		public Responsavel getResponsavel() {
			return responsavel;
		}

		public void setResponsavel(Responsavel responsavel) {
			this.responsavel = responsavel;
		}

		public List<Atividade> getAtividade() {
			return atividade;
		}

		public void setAtividade(List<Atividade> atividade) {
			this.atividade = atividade;
		}

		public List<Historia> getHistoria() {
			return historia;
		}

		public void setHistoria(List<Historia> historia) {
			this.historia = historia;
		}

		public Crianca() {
			
		}
		
		

	
}
