package model;

public class IteradorEscritor<E> extends IteradorDeFila<E> {

	public IteradorEscritor(Fila<E> fila) {
		super(fila);
	}

	@Override
	public void liberarCelda() {
		this.celdaActual.terminarDeEscribir();
	}

}
