package exemplo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


	@Entity
	public class Atividade implements Serializable{

		private static final long serialVersionUID = 1L;
	
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int idAtividade;
		
		private int salvaProgresso;
		private int desempenho;
		private int nivel;
		private String questao;
	
		@OneToMany
		private List<Crianca> crianca;
	
		@OneToOne
		@JoinColumn(name="idHistoria")
		private Historia historia;
		@OneToOne
		@JoinColumn(name="idResposta")
		private Resposta resposta;
		public int getIdAtividade() {
			return idAtividade;
		}
	public void setIdAtividade(int idAtividade) {
		this.idAtividade = idAtividade;
	}
	public int getSalvaProgresso() {
		return salvaProgresso;
	}
	public void setSalvaProgresso(int salvaProgresso) {
		this.salvaProgresso = salvaProgresso;
	}
	public int getDesempenho() {
		return desempenho;
	}
	public void setDesempenho(int desempenho) {
		this.desempenho = desempenho;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public String getQuestao() {
		return questao;
	}
	public void setQuestao(String questao) {
		this.questao = questao;
	}
	public List<Crianca> getCrianca() {
		return crianca;
	}
	public void setCrianca(List<Crianca> crianca) {
		this.crianca = crianca;
	}
	public Historia getHistoria() {
		return historia;
	}
	public void setHistoria(Historia historia) {
		this.historia = historia;
	}
	public Resposta getResposta() {
		return resposta;
	}
	public void setResposta(Resposta resposta) {
		this.resposta = resposta;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void Atividade () {
	
	}

}
