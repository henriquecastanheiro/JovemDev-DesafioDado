package jv.triersistemas.desafio_dados.controller;

import jv.triersistemas.desafio_dados.dto.DadoDTO;
import jv.triersistemas.desafio_dados.service.DadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/desafio")
public class DadoController  {

	@Autowired
	private DadoService dadoService;

	@PostMapping
	public ResponseEntity<String> verificarDados(@RequestBody DadoDTO dadoDto) {
		return dadoService.verificarDados(dadoDto.getQtddDados(), dadoDto.getValorAposta());
	}
}