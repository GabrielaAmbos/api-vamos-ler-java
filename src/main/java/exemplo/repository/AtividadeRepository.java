package exemplo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import exemplo.model.Atividade;
import exemplo.model.Responsavel;

public interface AtividadeRepository extends JpaRepository <Atividade, Integer>{
	
	Atividade save(Atividade atividade);

}