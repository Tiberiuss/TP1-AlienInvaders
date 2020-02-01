package pr2.game.Interfaces;

import pr2.game.Game;

public interface IExecuteRandomActions {

	static boolean canGenerateRandomOvni(Game game) {
		return game.getRandom().nextDouble() < game.getLevel().getOvniFrequency();
	}

	static boolean canGenerateRandomBomb(Game game) {
		return game.getRandom().nextDouble() < game.getLevel().getShootFrequency();
	}
	
	static boolean canGenerateExplosiveShip(Game game) {
		return game.getRandom().nextDouble() < game.getLevel().getTurnExplodeFrequency();
	}

	static boolean canDescendSmartBomb(Game game) {
		return game.getRandom().nextDouble() < game.getLevel().getTurnSmartBombFrequency();
	}

	static String chooseSmartBombNextMove(Game game,int fila, int columna) {
		double rand=game.getRandom().nextDouble();
		double probabilidadPosicion=game.getLevel().getNumMovesSmartBomb();	//33%	
		String moveTo="";
		if(rand<=probabilidadPosicion) {
			//izquierda
			fila+=1;
			columna-=1;
		}else if(rand>probabilidadPosicion && rand <=probabilidadPosicion*2) {
			fila+=1;
		}else {
			fila+=1;
			columna+=1;
		}
		moveTo=fila+","+columna;
		return moveTo;
	}

}
