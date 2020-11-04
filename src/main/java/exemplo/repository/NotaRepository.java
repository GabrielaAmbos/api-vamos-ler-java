package exemplo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import exemplo.model.Nota;


public interface NotaRepository extends JpaRepository<Nota, Integer> {

}
