package entity;

import java.time.LocalDate;
import java.util.Currency;

public class Devolucao {
	private static int id;
	private LocalDate data;
	private float multa;
	private boolean status;
	
	public Devolucao() {
	}
	
	public float calcMulta(LocalDate emprestimo, int prazo) {
		LocalDate devolucao = emprestimo.plusDays(prazo) ;
		int dias = devolucao.compareTo(data);
		if (dias > 0) {
			return (float) (dias*1.50); //o Aluguel/Dia é 1 real, na multa eles cobram 1.50 por dia a mais
		} else {
			return (float) (dias *1.00); //o saldo é negativo pq eles devolvem esse valor para o cliente
		}
		
		
	}
	
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public float getMulta() {
		return multa;
	}
	public void setMulta(float multa) {
		this.multa = multa;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	

}
