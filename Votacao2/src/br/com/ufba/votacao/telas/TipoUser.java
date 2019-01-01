package br.com.ufba.votacao.telas;

import java.util.ArrayList;

public class TipoUser {
	String id, nome, senha;
	int qtd;
	ArrayList<String> jaVotados;
	
	TipoUser(String x) {
		// System.out.println(x);
		String arr[] = x.split(":");
		id = arr[0];
		nome = arr[1];
		senha = arr[2];
		jaVotados = new ArrayList<String>();
		qtd = Integer.valueOf(arr[3]);
		
		for(int i = 4, j = 0; j < qtd; i++,j++) {
			jaVotados.add(arr[i]);
		}
	}
}
