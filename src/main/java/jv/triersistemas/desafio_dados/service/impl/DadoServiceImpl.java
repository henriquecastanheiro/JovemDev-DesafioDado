package jv.triersistemas.desafio_dados.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import jv.triersistemas.desafio_dados.service.DadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class DadoServiceImpl implements DadoService {

	@Override
	public ResponseEntity<String> verificarDados(Integer qtddDados, Integer valorAposta) {
		if( qtddDados < 1 || qtddDados > 4) {
			return ResponseEntity.badRequest().body(
					"A quantidade de dados deve ser entre 1 e 4.");
		}
		if (valorAposta <= 0) {
			return ResponseEntity.badRequest().body(
					"O valor da aposta deve ser positivo.");
		}
		int apostaMax = qtddDados * 6;
		if(valorAposta > apostaMax || valorAposta < qtddDados) {
			return ResponseEntity.badRequest().body(
					"O valor da aposta deve ser entre " + qtddDados + " e " + valorAposta + ".");
		}
		Map<Integer, Integer> mapaDados = lancarDados(qtddDados);
		int somaDosDados = somarDados(mapaDados);
		BigDecimal percentual = calcularPercentual(valorAposta, somaDosDados);
		String body = gerarBody(mapaDados, somaDosDados, percentual);
		return ResponseEntity.ok(body);
	}

	private Map<Integer, Integer> lancarDados(Integer qtddDados){
		Map<Integer, Integer> mapaDados = new HashMap<>();
		Random numeroAleatorio = new Random();
		for(int i = 1; i <= qtddDados; i++) {
			mapaDados.put(i, numeroAleatorio.nextInt(6)+1);
		}
		return mapaDados;
	}

	private int somarDados(Map<Integer, Integer> mapaDados) {
		return mapaDados.values().stream().mapToInt(Integer::intValue).sum();
	}

	private	BigDecimal calcularPercentual(Integer valorAposta,int somaDosDados) {

		if(somaDosDados < valorAposta) {
			return BigDecimal.valueOf(somaDosDados)
					.multiply(BigDecimal.valueOf(100))
					.divide(BigDecimal.valueOf(valorAposta),2, RoundingMode.HALF_UP);
		}else{
			return BigDecimal.valueOf(valorAposta)
					.multiply(BigDecimal.valueOf(100))
					.divide(BigDecimal.valueOf(somaDosDados),2, RoundingMode.HALF_UP);
		}
	}

	private String gerarBody(Map<Integer, Integer> mapaDados, int somaDosDados, BigDecimal percentual) {
		StringBuilder sb = new StringBuilder();
		sb.append("Resultados dos Dados: \n");

		for (Map.Entry<Integer, Integer> entry : mapaDados.entrySet()) {
			sb.append("Dado ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
		}

		sb.append("Soma dos Resultados: ").append(somaDosDados).append("\n");
		sb.append("Percentual da Aposta: ").append(String.format("%.2f", percentual)).append("%");

		return sb.toString();
	}

	private Map<Integer, Integer> geraDados(Integer qtd) {
		Map<Integer, Integer> mapDados = new HashMap<>();
		Random r = new Random();
		for (int i = 1; i <= qtd; i++) {
			mapDados.put(i, (r.nextInt(6)+1));
		}
		return mapDados;
	}

}
