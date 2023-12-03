package entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Livro {
	
	private String nome;
	private Autor autor;
	private Editora editora;
	private LocalDate ano;
	private int id;
	private boolean disponibilidade;
	private String genero;

	public String getNome() { 
        
        return this.nome;
    
    }
    
    public void setNome(String n) { 
        
        this.nome = n;
    
    }
    
    public Autor getAutor() { 
        
        return this.autor;
    
    }
    
    public void setAutor(Autor a) { 
        
        this.autor = a;
    
    }
    
    public Editora getEditora() { 
        
        return this.editora;
    
    }
    
    public void setEditora(Editora e) { 
        
        this.editora = e;
    
    }
    
    public int getId() { 
        
        return this.id;
    
    }
    
    public void setId(int i) { 
        
        this.id = i;
    
    }
    
    public boolean getDisponibilidade() { 
        
        return this.disponibilidade;
    
    }
    
    public void setDisponibilidade(boolean d) { 
        
        this.disponibilidade = d;
    
    }
    
    public String getGenero() { 
        
        return this.genero;
    
    }
    
    public void setGenero(String g) { 
        
        this.genero = g;
    
    }
    
    public LocalDate getAno() {
        
        return this.ano;
    
    }
    
    public void setAno(String sAno, DateTimeFormatter dtfa) {
        
        LocalDate a = LocalDate.parse(sAno, dtfa);
        this.ano = a;
    
    }
}