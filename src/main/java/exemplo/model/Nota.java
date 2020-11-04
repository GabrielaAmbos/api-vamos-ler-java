package exemplo.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

	@Entity
	public class Nota implements Serializable{
		
		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int idNota;
		private int desempenho;
		private int acertos;
		
		@JsonIgnore
		@OneToOne
		private Resposta resposta;

		public int getIdNota() {
			return idNota;
		}

		public void setIdNota(int idNota) {
			this.idNota = idNota;
		}

		public int getDesempenho() {
			return desempenho;
		}

		public void setDesempenho(int desempenho) {
			this.desempenho = desempenho;
		}

		public int getAcertos() {
			return acertos;
		}

		public void setAcertos(int acertos) {
			this.acertos = acertos;
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

		public Nota() {
			
		}

		
		
	}
