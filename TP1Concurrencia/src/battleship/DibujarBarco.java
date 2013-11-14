package battleship;

import matriz.Lector;
import ar.edu.unq.pconc.Channel;

final class DibujarBarco implements Lector<Barco, Void> {
	private final Channel<String> ch;
	private final int i;
	private final int j;

	DibujarBarco(Channel<String> ch, int i, int j) {
		this.ch = ch;
		this.i = i;
		this.j = j;
	}

	@Override
	public Void read(Barco e) {
		if (e != null) {
			if (e.estaHundido) {
				ch.send("sink" + "-" + i + "-" + j);
			} else {
				ch.send("ship" + "-" + i + "-" + j);
			}
		}
		return null;
	}
}