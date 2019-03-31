package tennis;

import static tennis.BallDirection.*;

public class TennisGame {

	private int serverpoints;
	private int receiverpoints;
	private BallDirection direction;
	private boolean outbound;
	private int numfault;

	public TennisGame() {

		serverpoints = 0;
		receiverpoints = 0;
		direction = NOT_IN_PLAY;

	}

	public BallDirection getBallStatus() {
		return direction;

	}

	public java.lang.String getCallString() {
		if (serverpoints >= 4 && serverpoints == receiverpoints + 1) {
			return "advantage in";
		} else if ((receiverpoints >= 4) && (receiverpoints == (serverpoints + 1))) {
			return "advantage out";
		} else if ((receiverpoints == serverpoints) && ((receiverpoints >= 3) || (serverpoints >= 3))) {
			return "deuce";
		} else if (receiverpoints == serverpoints && serverpoints < 3) {
			if (serverpoints == 0) {
				return "love-all";
			} else if (serverpoints == 1) {
				return "15-all";
			} else {
				return "30-all";
			}
		}

		else if (serverpoints > 3 && serverpoints >= receiverpoints + 2
				|| receiverpoints > 3 && receiverpoints >= serverpoints + 2) {
			return getScore();
		} else {
			String pointserver;
			String pointreceiver;
			if (serverpoints == 0) {
				pointserver = "love";
			} else if (serverpoints == 1) {
				pointserver = "15";
			} else if (serverpoints == 2) {
				pointserver = "30";
			} else {
				pointserver = "40";
			}
			if (receiverpoints == 0) {
				pointreceiver = "love";
			} else if (receiverpoints == 1) {
				pointreceiver = "15";
			} else if (receiverpoints == 2) {
				pointreceiver = "30";
			} else {
				pointreceiver = "40";
			}
			return pointserver + "-" + pointreceiver;
		}
	}

	public int getReceiverPoints() {
		return receiverpoints;

	}

	public java.lang.String getScore() {
		String scorereceiver = "" + receiverpoints;
		String scoreserver = "" + serverpoints;
		String scoretotal = scoreserver+"-"+scorereceiver;
		return scoretotal;
	}

	public int getServerPoints() {

		return serverpoints;

	}

	public void hit(boolean fault, boolean headedOutOfBounds) {
		outbound = headedOutOfBounds;
		if (direction == TOWARD_RECEIVER && fault == false) {
			direction = TOWARD_SERVER;
		} else if (direction == TOWARD_RECEIVER && fault == true) {

			if (headedOutOfBounds == true) {
				receiverpoints++;
				direction = NOT_IN_PLAY;
			} else {
				serverpoints++;
				direction = NOT_IN_PLAY;
			}
		} else if (direction == TOWARD_SERVER && fault == false) {
			direction = TOWARD_RECEIVER;
		} else if (direction == TOWARD_SERVER && fault == true) {
			if (headedOutOfBounds == true) {
				serverpoints++;
				direction = NOT_IN_PLAY;
			} else {
				receiverpoints++;
				direction = NOT_IN_PLAY;
			}
		}

	}

	public boolean isOver() {

		if (((serverpoints > 3) && (serverpoints >= receiverpoints + 2))
				|| (receiverpoints > 3) && (receiverpoints >= (serverpoints + 2))) {
			return true;
		} else {
			return false;
		}

	}

	public void miss() {

		if (direction == TOWARD_RECEIVER) {
			if (outbound == true) {
				receiverpoints = receiverpoints + 1;
				direction = NOT_IN_PLAY;
				outbound = false;
			} else if (outbound == false) {
				serverpoints = serverpoints + 1;
				direction = NOT_IN_PLAY;
				outbound = false;
			}
		}

		if (direction == TOWARD_SERVER) {
			if (outbound == true) {
				serverpoints = serverpoints + 1;
				direction = NOT_IN_PLAY;
				outbound = false;
			} else if (outbound == false) {
				receiverpoints = receiverpoints + 1;
				direction = NOT_IN_PLAY;
				outbound = false;

			}
		}

	}

	public boolean receiverWon() {
		if ((receiverpoints > 3) && (receiverpoints >= (serverpoints + 2))) {
			return true;
		} else {
			return false;
		}

	}

	public void serve(boolean serviceFault) {
		if (serverpoints < 3 && serverpoints < receiverpoints + 2
				|| receiverpoints < 3 && receiverpoints <= serverpoints + 2) {
			if (serviceFault == false && direction == NOT_IN_PLAY) {
				direction = TOWARD_RECEIVER;
				numfault = 0;
			} else if (serviceFault == true && direction == NOT_IN_PLAY) {
				if (numfault < 1) {
					numfault++;
				} else if (numfault == 1) {
					numfault = 0;
					receiverpoints++;
				}

			}
		}

	}

	public boolean serverWon() {

		if ((serverpoints > 3) && (serverpoints >= receiverpoints + 2)) {
			return true;
		} else {
			return false;
		}

	}

	public void setScore(int newServerScore, int newReceiverScore) {
		serverpoints = newServerScore;
		receiverpoints = newReceiverScore;
		direction = NOT_IN_PLAY;

	}
}
