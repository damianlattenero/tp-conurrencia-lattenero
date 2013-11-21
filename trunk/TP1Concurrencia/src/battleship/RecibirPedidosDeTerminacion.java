package battleship;

import ar.edu.unq.pconc.Channel;

public class RecibirPedidosDeTerminacion extends Thread {
	
	private Channel<Boolean> channelParaTerminar;
	private ServidorBattleship server;
	
	public RecibirPedidosDeTerminacion(ServidorBattleship s, int id) {
		channelParaTerminar = new Channel<>(id, true);
		server = s;
	}

	@Override
	public void run() {
		while(true){
			this.channelParaTerminar.receive();
			boolean b = this.server.terminoElJuego();
			this.channelParaTerminar.send(b);
			if(b){
				break;
			}
		}
		this.server.eliminarChannelDeJugador(this);
	}

}
