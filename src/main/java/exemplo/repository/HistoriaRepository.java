package exemplo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import exemplo.model.Historia;


public interface HistoriaRepository extends JpaRepository<Historia, Integer>{

}

