package exemplo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import exemplo.model.Resposta;

public interface RespostaRepository extends JpaRepository<Resposta, Integer> {

}
