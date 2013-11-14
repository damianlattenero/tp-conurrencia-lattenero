package matriz;

import java.util.ArrayList;
import java.util.List;

public class Fila<E> {
	
	List<Celda<E>> fila;
	
	public Fila(int cantColumnas){
		this.fila = new ArrayList<Celda<E>>();
		for(int j=0; j < cantColumnas; j++) {
			this.fila.add(new Celda<E>());
		}
	}
	
	public IteradorEscritor<E> getWriterIterator() {
		for(Celda<E> c : this.fila) {
			c.empezarAEscribir();
		}
		return new IteradorEscritor<E>(this);
	}

	public IteradorLector<E> getReaderIterator() {
		for(Celda<E> c : this.fila) {
			c.empezarALeer();
		}
		return new IteradorLector<E>(this);
	}
	
	public Celda<E> getCelda(int i) {
		return this.fila.get(i);
	}

	public int getsize() {
		
		return fila.size();
	}

	public <R> R leerCelda(Lector<E,R> l, int columna) {
		Celda<E> c = this.getCelda(columna);
		c.empezarALeer();
		R result = c.leer(l);
		c.terminarDeLeer();
		return result;
	}
	
	public void escribirCelda(Escritor<E> w, int columna) {
		Celda<E> c = this.getCelda(columna);
		c.empezarAEscribir();
		c.escribir(w);
		c.terminarDeEscribir();
	}

}
