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

import exemplo.model.Crianca;
import exemplo.model.Historia;
import exemplo.repository.HistoriaRepository;

@Controller
@RequestMapping(path="")
public class HistoriaController {
	@Autowired
	private HistoriaRepository historiaRepository;
	//------------------------------------------------------------------------------------------------------------------------
	
	//CADASTRAR HISTÓRIA
	@RequestMapping(value = "/historia/", method = RequestMethod.POST)
	public ResponseEntity<?> cadastrarHistoria(@RequestBody Historia h){
		Historia temp = historiaRepository.save(h);
		return new ResponseEntity(HttpStatus.CREATED);
	}
	//-----------------------------------------------------------------------------------------------------------------------
	
	//BUCAR TODAS AS HISTÓRIAS
	@RequestMapping(value = "/historia/", method = RequestMethod.GET)
	public ResponseEntity<List<Historia>> consultaTodasHistorias(){
		List<Historia> historia = historiaRepository.findAll();
		if(historia.isEmpty()) {
			return new ResponseEntity(new CustomErrorType("Histórias não encontradas"),HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Historia>>(historia, HttpStatus.OK);
	}
	//-------------------------------------------------------------------------------------------------------------------------
	
	//BUSCAR HISTÓRIA POR ID
	@RequestMapping(value = "/historia/{idHistoria}", method = RequestMethod.GET)
	public ResponseEntity<Historia> buscarHistoriaId(@PathVariable("idHistoria") Integer idHistoria){
		Historia h = historiaRepository.findOne(idHistoria);
		if(h == null) {
			return new ResponseEntity(new CustomErrorType("História com id " + idHistoria  + " nao encontrado"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(h, HttpStatus.OK);
	}
	//----------------------------------------------------------------------------------------------------------------------------
	
	//DELETAR HISTÓRIA
		@RequestMapping(value = "/historia/{idHistoria}", method = RequestMethod.DELETE)
		public ResponseEntity<?> deletarHistoria(@PathVariable("idHistoria") Integer idHistoria){
			Historia h = historiaRepository.findOne(idHistoria);
			if(h == null) {
				return new ResponseEntity(new CustomErrorType("Nao foi possivel deletar. Historia com Id " + idHistoria + " nao encontrado."),HttpStatus.NOT_FOUND);
			}
			historiaRepository.delete(idHistoria);
			return new ResponseEntity("Historia deletada!",HttpStatus.OK);
		}
	//------------------------------------------------------------------------------------------------------------------------------
		
	//ATUALIZAR HISTÓRIA
			@RequestMapping(value = "/historia/{idHistoria}", method = RequestMethod.PUT)
			public ResponseEntity<?> updateHistoria(@PathVariable("idHistoria") Integer idHistoria, @RequestBody Historia h){
				Historia atualizaHistoria = historiaRepository.findOne(idHistoria);
				if(atualizaHistoria == null){
					return new ResponseEntity(new CustomErrorType("Nao foi possivel atualizar"),HttpStatus.NOT_FOUND);
				}
				atualizaHistoria.setPaginas(h.getPaginas());
				atualizaHistoria.setNomeHistoria(h.getNomeHistoria());
				atualizaHistoria.setProgressoHistoria(h.getProgressoHistoria());
				atualizaHistoria.setConteudoHistoria(h.getConteudoHistoria());
				historiaRepository.save(atualizaHistoria);
				return new ResponseEntity<Historia>(atualizaHistoria,HttpStatus.OK);
			}
	//----------------------------------------------------------------------------------------------------------------------------
		
}
