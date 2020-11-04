package exemplo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import exemplo.model.Atividade;
import exemplo.repository.AtividadeRepository;

@Controller
@RequestMapping(path="")
public class AtividadeController {

	@Autowired
	private AtividadeRepository atividadeRepository;
	
	//-----------------------------------------------------------------------------------------------------------------------
	
	//CADASTRAR ATIVIDADE
		@RequestMapping(value = "/atividade/", method = RequestMethod.POST)
		public ResponseEntity<?> cadastrarAtividade(@RequestBody Atividade at){
			Atividade temp = atividadeRepository.save(at);
			return new ResponseEntity(temp,HttpStatus.CREATED);
		}
	//------------------------------------------------------------------------------------------------------------------------
	
	//CONSULTAR TODAS AS ATIVIDADES
		@RequestMapping(value = "/atividade/", method = RequestMethod.GET)
		public ResponseEntity<List<Atividade>> consultarAtividades(){
			List<Atividade> atividade = atividadeRepository.findAll();
			if(atividade.isEmpty()) {
				return new ResponseEntity(new CustomErrorType("Atividades não encontradas"),HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Atividade>>(atividade, HttpStatus.OK);
			
		}
	//--------------------------------------------------------------------------------------------------------------------------
		
	//CONSULTAR ATIVIDADE POR ID
	@RequestMapping(value = "/atividade/{idAtividade}", method = RequestMethod.GET)
	public ResponseEntity<Atividade> buscarAtividadeId(@PathVariable("idAtividade") Integer idAtividade){
		Atividade at = atividadeRepository.findOne(idAtividade);
		if(at == null) {
			return new ResponseEntity(new CustomErrorType("Atividade com id " + idAtividade + " nao encontrado"),HttpStatus.NOT_FOUND);
		}
			return new ResponseEntity(at, HttpStatus.OK);
		}
	//----------------------------------------------------------------------------------------------------------------------------
	
	//DELETAR ATIVIDADE
		@RequestMapping(value = "/atividade/{idAtividade}", method = RequestMethod.DELETE)
		public ResponseEntity<?> deletarAtividade(@PathVariable("idAtividade") Integer idAtividade){
			Atividade at = atividadeRepository.findOne(idAtividade);
			if(at == null) {
				return new ResponseEntity(new CustomErrorType("Nao foi possivel deletar. Ativdade com Id " + idAtividade + " nao encontrado."),HttpStatus.NOT_FOUND);
			}
			atividadeRepository.delete(idAtividade);
			return new ResponseEntity("Atividade deletada!",HttpStatus.OK);
		}
		//------------------------------------------------------------------------------------------------------------------------------
			
	//ATUALIZAR HISTÓRIA
			@RequestMapping(value = "/atividade/{idAtividade}", method = RequestMethod.PUT)
			public ResponseEntity<?> updateAtividade(@PathVariable("idAtividade") Integer idAtividade, @RequestBody Atividade at){
				Atividade atualizaAtividade = atividadeRepository.findOne(idAtividade);
				if(atualizaAtividade == null){
					return new ResponseEntity(new CustomErrorType("Nao foi possivel atualizar"),HttpStatus.NOT_FOUND);
				}
				atualizaAtividade.setSalvaProgresso(at.getSalvaProgresso());
				atualizaAtividade.setDesempenho(at.getDesempenho());
				atualizaAtividade.setNivel(at.getNivel());
				atualizaAtividade.setQuestao(at.getQuestao());
				atividadeRepository.save(atualizaAtividade);
				return new ResponseEntity<Atividade>(at,HttpStatus.OK);
			}
			//------------------------------------------------------------------------------------------------------------------
	
}
