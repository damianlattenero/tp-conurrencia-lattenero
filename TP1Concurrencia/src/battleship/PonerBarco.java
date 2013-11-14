package battleship;

import matriz.Celda;
import matriz.Escritor;

final class PonerBarco implements Escritor<Barco> {
	@Override
	public void write(Celda<Barco> celda) {
		celda.setContenido(new Barco());
	}
}