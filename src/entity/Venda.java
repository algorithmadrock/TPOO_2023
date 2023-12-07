package entity;

import java.time.LocalDate;
import java.util.Set;

public class Venda {

	private static int id;
	private int idfunc;
	private int idclie;
	private LocalDate data;
	private float valor;
	private Set<Livro> itens;

	public Venda() {
		id++;
	}

	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		Venda.id = id;
	}

	public int getIdfunc() {
		return idfunc;
	}

	public void setIdfunc(Funcionario funcionario) {
		this.idfunc = funcionario.getId();
	}

	public int getIdclie() {
		return idclie;
	}

	public void setIdclie(Cliente cliente) {
		this.idclie = cliente.getId();
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;

	}

	public float getValor() {
		return valor;
	}

	public void setValor() {
		Livro[] livros = (Livro[]) itens.toArray();
		float valor = (float) 0.00;
		for (Livro livro : livros) {
			valor += livro.getValor();
		}
		this.valor = valor;
	}

	public Set<Livro> getItens() {
		return itens;
	}

	public void setItens(Livro livro) {
		this.itens.add(livro);
	}

}
