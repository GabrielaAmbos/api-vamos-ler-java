package exemplo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import exemplo.repository.CriancaRepository;
import exemplo.repository.ResponsavelRepository;
import exemplo.controller.CustomErrorType;
import exemplo.model.Crianca;
import exemplo.model.Responsavel;

@Controller
@RequestMapping(path="")
public class CriancaController {
	
	@Autowired
	private CriancaRepository criancaRepository;
	
	@Autowired
	private ResponsavelRepository responsavelRepository;
	//---------------------------------------------------------------------------------------------------------------------------

	//CADASTRAR CRIANÇA
	@RequestMapping(value ="/crianca/",method = RequestMethod.POST)
	public ResponseEntity<?> cadastrarContaCrianca(@RequestBody Crianca c){
		if(c != null){
			Crianca temp = criancaRepository.save(c);
			return new ResponseEntity("Conta criada!",HttpStatus.CREATED);
	} 
		else {
		return new ResponseEntity(new CustomErrorType("Erro ao cadastrar conta"),HttpStatus.NOT_FOUND);
	}	
		}
	
	//---------------------------------------------------------------------------------------------------------------------
	
	//BUSCAR TODAS AS CRIANÇAS
	@RequestMapping(value = "/crianca/", method = RequestMethod.GET)
	public ResponseEntity<List<Crianca>> buscarTodos() {
		
		List<Crianca> listaCriancas = this.criancaRepository.findAll();		
		
		return new ResponseEntity<List<Crianca>>(listaCriancas, HttpStatus.OK);
		
	}
	//-------------------------------------------------------------------------------------------------------------------------
		
		//BUSCAR Crianca POR ID
		@RequestMapping(value = "/crianca/{idCrianca}", method = RequestMethod.GET)
		public ResponseEntity<Crianca> buscarCriancaPorId(@PathVariable("idCrianca") Integer idCrianca){
			Crianca r = criancaRepository.findOne(idCrianca);
			if(r == null) {
				return new ResponseEntity(new CustomErrorType("Crianca com id " + idCrianca  + " nao encontrado"), HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity(r, HttpStatus.OK);
		}
	//-------------------------------------------------------------------------------------------------------------------------
	
	//DELETAR CONTA Crianca
		
		@RequestMapping(value = "/crianca/{idCrianca}", method = RequestMethod.DELETE)
		public ResponseEntity<?> deleteCriancaPorId(@PathVariable("idCrianca") Integer idCrianca){
			Crianca r = criancaRepository.findOne(idCrianca);
			if(r == null) {
				return new ResponseEntity(new CustomErrorType("Nao foi possivel deletar. Crianca com Id " + idCrianca + " nao encontrado."),HttpStatus.NOT_FOUND);
			}
			criancaRepository.delete(idCrianca);
			return new ResponseEntity("Conta deletada",HttpStatus.OK);
		}
		
	/*	@RequestMapping(value = "/crianca/{idCrianca}", method = RequestMethod.DELETE)
		public ResponseEntity<?> deleteCriancaPorId(@PathVariable int idCrianca) {
			if(this.criancaRepository.findOne(idCrianca)==null) {
				   return new ResponseEntity(new CustomErrorType("Nao foi possivel deletar. Crianca com id " + idCrianca + " nao encontrado."),
		                    HttpStatus.NOT_FOUND);
			}
			
			this.criancaRepository.delete(idCrianca);
			
			return new ResponseEntity<>(HttpStatus.OK);	
			
		}*/
	//--------------------------------------------------------------------------------------------------------------------------
	
	//ATUALIZAR DADOS CRIANÇA
		@RequestMapping(value = "/crianca/", method = RequestMethod.PUT)
		public ResponseEntity<?> updateCrianca(@RequestBody Crianca crianca){
			if(this.criancaRepository.findOne(crianca.getIdCrinca())==null) {
				   return new ResponseEntity(new CustomErrorType("Nao foi possivel atualizar. Responsavel com id " + crianca.getIdCrinca() + " nao encontrado."),
		                    HttpStatus.NOT_FOUND);
			}	
				this.criancaRepository.save(crianca);
			
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		
	//------------------------------------------------------------------------------------------------------------------------------
	

}
