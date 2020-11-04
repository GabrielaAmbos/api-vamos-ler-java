package exemplo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import exemplo.repository.ResponsavelRepository;

import exemplo.model.Responsavel;

@Controller
@RequestMapping(path="")
public class ResponsavelController {
	
	@Autowired
	private ResponsavelRepository responsavelRepository;
	
	//----------------------------------------------------------------------------------------------------------------------------
	//VALIDA LOGIN DO RESPONSÁVEL
	 @RequestMapping(value = "/responsavel/{email}/{senha}", method = RequestMethod.POST)
		public ResponseEntity<Responsavel> autentica(@PathVariable String email,@PathVariable String senha){
			 
			 Responsavel usuarioVindoBD = this.responsavelRepository.findByEmailLikeAndSenhaLike(email, senha);
			 
		        if (usuarioVindoBD == null) {
		        	
		            return new ResponseEntity(new CustomErrorType("Login/Senha invalidados!"), HttpStatus.NOT_FOUND);
		        }
		        return new ResponseEntity<Responsavel>(usuarioVindoBD, HttpStatus.OK);
		    }
		 
	//----------------------------------------------------------------------------------------------------------------------------
	
	//CADASTRAR RESPONSÁVEL
	@RequestMapping(value = "/responsavel/", method = RequestMethod.POST)
	public ResponseEntity<Responsavel> postCadastrarResposavel(@RequestBody Responsavel responsavel){
		responsavel = this.responsavelRepository.save(responsavel);
		return new ResponseEntity<Responsavel>(responsavel,HttpStatus.CREATED);
	}
	//----------------------------------------------------------------------------------------------------------------------------
		 
	//BUSCAR TODOS OS RESPONSÁVEIS
	@RequestMapping(value = "/responsavel/",method = RequestMethod.GET)
	public ResponseEntity<List<Responsavel>> buscarTodosResponsaveis(){
		List<Responsavel> responsavel = responsavelRepository.findAll();
		if(responsavel.isEmpty()) {
			return new ResponseEntity(new CustomErrorType("Nao foi possivel buscar todos os responsáveis .Usuario"),HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Responsavel>>(responsavel, HttpStatus.OK);
	}
	//------------------------------------------------------------------------------------------------------------------------------
	
	//BUSCAR RESPONSAVEL POR ID
	@RequestMapping(value = "/responsavel/{idResponsavel}", method = RequestMethod.GET)
	public ResponseEntity<Responsavel> buscarResponsavelId(@PathVariable("idResponsavel") Integer idResponsavel){
		Responsavel r = responsavelRepository.findOne(idResponsavel);
		if(r == null) {
			return new ResponseEntity(new CustomErrorType("Responsavel com id " + idResponsavel  + " nao encontrado"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(r, HttpStatus.OK);
	}
	//------------------------------------------------------------------------------------------------------------------------------
	//DELETAR CONTA RESPONSÁVEL
	@RequestMapping(value = "/responsavel/{idResponsavel}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletePorId(@PathVariable int idResponsavel) {
		if(this.responsavelRepository.findOne(idResponsavel)==null) {
			   return new ResponseEntity(new CustomErrorType("Nao foi possivel deletar. Responsavel com id " + idResponsavel + " nao encontrado."),
	                    HttpStatus.NOT_FOUND);
		}
		
		this.responsavelRepository.delete(idResponsavel);
		
		return new ResponseEntity<>(HttpStatus.OK);	
		
	}
	//------------------------------------------------------------------------------------------------------------------------------

	//-------------------------------------------------------------------------------------------------------------------------------
	
	//ATUALIZAR DADOS RESPONSÁVEL
	@RequestMapping(value = "/responsavel/", method = RequestMethod.PUT)
	public ResponseEntity<?> putAtualizar(@RequestBody Responsavel responsavel) {
		
		if(this.responsavelRepository.findOne(responsavel.getIdResponsavel())==null) {
			   return new ResponseEntity(new CustomErrorType("Nao foi possivel atualizar. Responsavel com id " + responsavel.getIdResponsavel() + " nao encontrado."),
	                    HttpStatus.NOT_FOUND);
		}	
		
		this.responsavelRepository.save(responsavel);
		
		return new ResponseEntity<>(HttpStatus.CREATED);		
	}
	//---------------------------------------------------------------------------------------------------------------------------
	

}

