package matriz;

import java.util.ArrayList;
import java.util.List;

public class Matriz<E> {

	int cantFilas;
	int cantColumnas;
	List<Fila<E>> estructura;

	public Matriz(int cantFilas, int cantColumnas) {
		super();
		this.cantFilas = cantFilas;
		this.cantColumnas = cantColumnas;
		this.estructura = new ArrayList<Fila<E>>();

		for (int i = 0; i < cantFilas; i++) {
			this.estructura.add(new Fila<E>(cantColumnas));
		}
	}

	public <R> R leerCelda(Lector<E, R> r, int fila, int columna) {
		return this.estructura.get(fila).leerCelda(r, columna);
	}

	public void escribirCelda(Escritor<E> r, int fila, int columna) {
		this.estructura.get(fila).escribirCelda(r, columna);
	}

	public IteradorLector<E> getIteradorLectorDeFila(int nroFila) {
		return this.estructura.get(nroFila).getReaderIterator();

	}

	public IteradorEscritor<E> getIteradorEscritorDeFila(int nroFila) {

		return this.estructura.get(nroFila).getWriterIterator();

	}

	public int getCantFilas() {
		return cantFilas;
	}

	public int getCantColumnas() {
		return cantColumnas;
	}
	
	

}
