package tennis;

public class Set {

	private int p1wins;
	private int p0wins;
	private int gamestowin;
	private boolean serverpick;
	private TennisGame A = new TennisGame();

	public Set(int minimumGamesToWin, boolean player1ServesFirst) {
		gamestowin = minimumGamesToWin;
		serverpick = player1ServesFirst;

	}

	public void serve(boolean serviceFault) {
		if (helpisSetOver() == false) {
			if (winCheckS() == false && winCheckR() == false) {
				A.serve((serviceFault));
				if (winCheckS() == true && serverpick == false) {
					p0wins++;
				} else if (winCheckS() == true && serverpick == true) {
					p1wins++;
				} else if (winCheckR() == true && serverpick == true) {
					p0wins++;

				}
			}
		}
	}

	public void hit(boolean fault, boolean outOfBounds) {

		if (helpisSetOver() == false) {
			if (winCheckS() == false && winCheckR() == false) {
				A.hit(fault, outOfBounds);
				if (winCheckS() == true && serverpick == false) {
					p0wins++;
				} else if (winCheckS() == true && serverpick == true) {
					p1wins++;
				}
				if (winCheckR() == true && serverpick == false) {
					p1wins++;
				} else if (winCheckR() == true && serverpick == true) {
					p0wins++;
				}
			}
		}

	}

	public void miss() {

		if (helpisSetOver() == false) {
			if (winCheckS() == true && serverpick == false) {
				p0wins++;
			} else if (winCheckS() == true && serverpick == true) {
				p1wins++;
			}
			if (winCheckR() == true && serverpick == false) {
				p1wins++;
			} else if (winCheckR() == true && serverpick == true) {
				p0wins++;
			}
		}

	}

	public void fastForward(int serverScore, int receiverScore) {

		if (helpisSetOver() == false) {
			if (winCheckS() == false && winCheckR() == false) {

				A.setScore(serverScore, receiverScore);
				if (winCheckS() == true && serverpick == false) {
					p0wins++;
				} else if (winCheckS() == true && serverpick == true) {
					p1wins++;
				}
				if (winCheckR() == true && serverpick == false) {
					p1wins++;
				} else if (winCheckR() == true && serverpick == true) {
					p0wins++;
				}
			}
		}

	}

	public void newGame() {
		if (helpisSetOver() == false) {
			if (A.isOver() == true) {
				A = new TennisGame();
				if (serverpick == false) {
					serverpick = true;
				} else {
					serverpick = false;
				}
			}
		}

	}

	public boolean isCurrentGameOver() {
		if (A.isOver() == true) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isSetOver() {

		return helpisSetOver();
	}

	public java.lang.String getCurrentStatus(boolean useCallString) {

		String p1w = "" + p1wins;
		String p0w = "" + p0wins;
		if (useCallString == false) {
			if (serverpick == false) {

				String set = "Set: " + p0w + "-" + p1w + "Game: " + A.getScore();
				return set;
			} else {
				String set = "Set: " + p1w + "-" + p0w + "Game: " + A.getScore();
				return set;
			}
		} else {
			if (serverpick == false) {
				String set = "Set: " + p0w + "-" + p1w + A.getCallString();
				return set;
			} else {
				String set = "Set: " + p1w + "-" + p0w + A.getCallString();
				return set;
			}
		}

	}

	public int whoIsServing() {
		if (serverpick == false) {
			return 0;
		} else {
			return 1;
		}

	}

	public int player0GamesWon() {
		return p0wins;

	}

	public int player1GamesWon() {
		return p1wins;

	}

	private boolean helpisSetOver() {
		if (p1wins == gamestowin && p1wins >= p0wins + 2 || p0wins == gamestowin && p0wins >= p0wins + 2) {
			return true;
		} else {
			return false;
		}
	}

	private boolean winCheckS() {
		if (A.serverWon() == true) {
			return true;
		} else {
			return false;
		}
	}

	private boolean winCheckR() {
		if (A.receiverWon() == true) {
			return true;
		} else {
			return false;
		}
	}

}
