package exemplo.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
	
	public class Resposta implements Serializable{
		private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int idResposta;
		private Boolean resposta;
		
		
		@JsonIgnore
		@OneToOne
		private Nota nota;
		
		@OneToOne
		@JoinColumn(name="idAtividade")
		private Atividade atividade;

		public int getIdResposta() {
			return idResposta;
		}

		public void setIdResposta(int idResposta) {
			this.idResposta = idResposta;
		}

		public Boolean getResposta() {
			return resposta;
		}

		public void setResposta(Boolean resposta) {
			this.resposta = resposta;
		}

		public Nota getNota() {
			return nota;
		}

		public void setNota(Nota nota) {
			this.nota = nota;
		}

		public Atividade getAtividade() {
			return atividade;
		}

		public void setAtividade(Atividade atividade) {
			this.atividade = atividade;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		public Resposta(Boolean resposta, Nota nota, Atividade atividade) {
			super();
			this.resposta = resposta;
			this.nota = nota;
			this.atividade = atividade;
		}

		public Resposta() {
			
		}
		
	
}
