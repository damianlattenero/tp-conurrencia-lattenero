package battleship;

import java.util.List;

public class ScanAndShoot implements Estrategia {

	@Override
	public void estategia(Jugador jugador) {
		int filas = jugador.getServidor().getMatriz().getCantFilas();

		for (int i = 0; i < filas; i++) {
			this.dispararBarcos(jugador.scanLine(i), i, jugador);
		}

	}

	private void dispararBarcos(List<Integer> scanLine, int fila,
			Jugador jugador) {
		for (Integer i : scanLine) {
			jugador.shoot(fila, i);
		}
	}
}
