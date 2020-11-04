package exemplo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import exemplo.model.Responsavel;


public interface ResponsavelRepository extends JpaRepository<Responsavel, Integer> {
	
	Responsavel save(Responsavel responsavel);


	Responsavel findByEmailLikeAndSenhaLike(String email, String senha);

}

