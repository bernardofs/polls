package br.com.ufba.votacao.telas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TipoEnquete implements Comparable<TipoEnquete> {
	
	public String id, titulo, nop, op1, qtdOp1, op2, qtdOp2, op3, qtdOp3, op4, qtdOp4, op5, qtdOp5, dtf;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	LocalDate dateTime;
	
	TipoEnquete(){
		id = "";
		titulo = nop = op1 = qtdOp1 = op2 = qtdOp2 = op3 = qtdOp3 = op4 = qtdOp4 = op5 = qtdOp5 = "";
		qtdOp1 = qtdOp2 = qtdOp3 = qtdOp4 = qtdOp5 = "0";
		dtf = "01/01/0001";
		dateTime = LocalDate.parse(this.dtf, formatter);
	}
	
	LocalDate toLocDat() {
		return this.dateTime = LocalDate.parse(this.dtf, formatter);
	}
	
	@Override
	public int compareTo(TipoEnquete o) {
	  return (toLocDat().compareTo(o.toLocDat()));
	}
	
}
