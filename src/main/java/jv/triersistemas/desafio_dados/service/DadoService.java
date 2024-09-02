package jv.triersistemas.desafio_dados.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface DadoService {

	ResponseEntity<String> verificarDados(@RequestBody Integer qtddDados, @RequestBody Integer valorAposta);
}