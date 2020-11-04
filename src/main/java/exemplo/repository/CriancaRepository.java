package exemplo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import exemplo.model.Crianca;


public interface CriancaRepository extends JpaRepository<Crianca, Integer> {

}
