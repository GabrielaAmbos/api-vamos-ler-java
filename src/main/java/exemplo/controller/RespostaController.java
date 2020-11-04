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

import exemplo.model.Responsavel;
import exemplo.model.Resposta;
import exemplo.repository.RespostaRepository;

@Controller
@RequestMapping(path="")
public class RespostaController {

	@Autowired
	private RespostaRepository respostaRepository;
	
	//CADASTRAR RESPOSTA 
		/*@RequestMapping(value = "/resposta/", method = RequestMethod.POST)
		public ResponseEntity<?> cadastrarResposta(@RequestBody Resposta rn){
			Resposta temp = respostaRepository.save(rn);
			return new ResponseEntity(temp,HttpStatus.CREATED);
		}*/
		
		@RequestMapping(value = "/resposta/", method = RequestMethod.POST)
		public ResponseEntity<?> postCadastrarResposta(@RequestBody Resposta resposta){
			resposta = this.respostaRepository.save(resposta);
			return new ResponseEntity<Resposta>(resposta,HttpStatus.CREATED);
		}
	//-------------------------------------------------------------------------------------------------------------------------------
		
	//CONSULTAR TODAS RESPOSTAS
		@RequestMapping(value = "/resposta/", method = RequestMethod.GET)
		public ResponseEntity<List<Resposta>> consultarResposta(){
			List<Resposta> respostaAtividade = respostaRepository.findAll();
			if(respostaAtividade.isEmpty()) {
				return new ResponseEntity(new CustomErrorType("Resposta não encontrada"),HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Resposta>>(respostaAtividade,HttpStatus.OK);	
		}
	//-------------------------------------------------------------------------------------------------------------------------------
		
	//CONSULTAR RESPOSTA POR ID
		@RequestMapping(value = "/resposta/{idResposta}", method = RequestMethod.GET)
		public ResponseEntity<Resposta> buscarRespostaId(@PathVariable("idResposta") Integer idResposta){
			Resposta rn = respostaRepository.findOne(idResposta);
			if(rn == null) {
				return new ResponseEntity(new CustomErrorType("Resposta com id " + idResposta + " nao encontrado"),HttpStatus.NOT_FOUND);
			}
				return new ResponseEntity(rn, HttpStatus.OK);
			}
	//--------------------------------------------------------------------------------------------------------------------------------
		
	//DELETAR RESPOSTA
		@RequestMapping(value = "/resposta/{idResposta}", method = RequestMethod.DELETE)
		public ResponseEntity<?> deletarResposta(@PathVariable("idResposta") Integer idResposta){
			Resposta rn = respostaRepository.findOne(idResposta);
			if(rn == null) {
				return new ResponseEntity(new CustomErrorType("Nao foi possivel deletar. Resposta com Id " + idResposta + " nao encontrado."),HttpStatus.NOT_FOUND);
			}
			respostaRepository.delete(idResposta);
			return new ResponseEntity("Resposta deletada!",HttpStatus.OK);
		}
		//------------------------------------------------------------------------------------------------------------------------------
			
		//ATUALIZAR RESPOSTA
			@RequestMapping(value = "/resposta/{idResposta}", method = RequestMethod.PUT)
			public ResponseEntity<?> updateResposta(@PathVariable("idResposta") Integer idResposta, @RequestBody Resposta rn){
				Resposta updateResposta = respostaRepository.findOne(idResposta);
				if(updateResposta == null){
					return new ResponseEntity(new CustomErrorType("Nao foi possivel atualizar"),HttpStatus.NOT_FOUND);
				}
			//	updateResposta.setPergunta(rn.getPergunta());
			//	updateResposta.setAlternativas(rn.getAlternativas());
				updateResposta.setResposta(rn.getResposta());
				respostaRepository.save(updateResposta);
				return new ResponseEntity<Resposta>(HttpStatus.OK);
			}
		//------------------------------------------------------------------------------------------------------------------

	
}

