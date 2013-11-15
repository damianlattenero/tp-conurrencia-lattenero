package matriz;

public class IteradorLector<E> extends IteradorDeFila<E>{

	public IteradorLector(Fila<E> fila) {
		super(fila);
	}

	@Override
	public void liberarCelda() {
		this.celdaActual.terminarDeLeer();
	}

}
