package exemplo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import exemplo.model.Nota;
import exemplo.repository.NotaRepository;

@Controller
@RequestMapping(path="")
public class NotaController {
	
	@Autowired
	private NotaRepository notaRepository;
	//-------------------------------------------------------------------------------------------------------------------------------
	
	//CADASTRAR NOTA
		@RequestMapping(value = "/nota/", method = RequestMethod.POST)
		public ResponseEntity<?> cadastrarNota(@RequestBody Nota n){
			Nota temp = notaRepository.save(n);
			return new ResponseEntity(temp,HttpStatus.CREATED);
		}
	//-------------------------------------------------------------------------------------------------------------------------------
	
	//CONSULTAR TODAS AS NOTAS
		@RequestMapping(value = "/nota/", method = RequestMethod.GET)
		public ResponseEntity<List<Nota>> consultarNota(){
			List<Nota> nota = notaRepository.findAll();
			if(nota.isEmpty()) {
				return new ResponseEntity(new CustomErrorType("Nota não encontrada"),HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Nota>>(nota, HttpStatus.OK);	
		}
	//-------------------------------------------------------------------------------------------------------------------------------
	
	//CONSULTAR NOTA POR ID
	@RequestMapping(value = "/nota/{idNota}", method = RequestMethod.GET)
	public ResponseEntity<Nota> buscarNotaId(@PathVariable("idNota") Integer idNota){
		Nota n = notaRepository.findOne(idNota);
		if(n == null) {
			return new ResponseEntity(new CustomErrorType("Nota com id " + idNota + " nao encontrado"),HttpStatus.NOT_FOUND);
		}
			return new ResponseEntity(n, HttpStatus.OK);
		}
	//--------------------------------------------------------------------------------------------------------------------------------
	
	////DELETAR NOTA
	@RequestMapping(value = "/nota/{idNota}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletarNota(@PathVariable("idNota") Integer idNota){
		Nota n = notaRepository.findOne(idNota);
		if(n == null) {
			return new ResponseEntity(new CustomErrorType("Nao foi possivel deletar. Nota com Id " + idNota + " nao encontrado."),HttpStatus.NOT_FOUND);
		}
		notaRepository.delete(idNota);
		return new ResponseEntity("Nota deletada!",HttpStatus.OK);
	}
	//------------------------------------------------------------------------------------------------------------------------------
		
	//ATUALIZAR NOTA
		@RequestMapping(value = "/nota/{idNota}", method = RequestMethod.PUT)
		public ResponseEntity<?> updateNota(@PathVariable("idNota") Integer idNota, @RequestBody Nota n){
			Nota atualizaNota = notaRepository.findOne(idNota);
			if(atualizaNota == null){
				return new ResponseEntity(new CustomErrorType("Nao foi possivel atualizar"),HttpStatus.NOT_FOUND);
			}
			atualizaNota.setDesempenho(n.getDesempenho());
			atualizaNota.setAcertos(n.getAcertos());
			notaRepository.save(atualizaNota);
			return new ResponseEntity<Nota>(n,HttpStatus.OK);
		}
	//------------------------------------------------------------------------------------------------------------------

}

