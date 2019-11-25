package ohtu;

public class TennisGame {
    
    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1")
            m_score1 += 1;
        else
            m_score2 += 1;
    }

    public String getScore() {
        String score = "";
        int tempScore=0;
        if (m_score1==m_score2)
        {
            score = even(m_score1);
        }
        else if (m_score1>=4 || m_score2>=4)
        {
            score = moreThanFour(m_score1, m_score2);
        }
        else
        {
            return normalSituation(m_score1, m_score2);
        }
        return score;
    }
    private String even(int score) {
	switch (score)
            {
                case 0:
                        return "Love-All";
                case 1:
                        return "Fifteen-All";
                case 2:
                        return "Thirty-All";
                case 3:
                        return "Forty-All";
                default:
                        return "Deuce";
                
            }
    }

    private String moreThanFour(int score1, int score2) {
	int minusResult = score1 - score2;
        if (minusResult==1) {
	    return "Advantage player1";
	}
        if (minusResult ==-1) {
	    return "Advantage player2";
	}
        if (minusResult>=2) {
	    return "Win for player1";
	}
        return "Win for player2";
    }

    private String normalSituation(int score1, int score2) {
	String score = "";
	int tempScore= 0;
	for (int i=1; i<3; i++) {
            if (i==1) {
		tempScore = score1;
            } else { 
		score+="-"; tempScore = score2;
	    }
            switch(tempScore) {
                case 0:
                    score+="Love";
                    break;
                case 1:
                    score+="Fifteen";
                    break;
                case 2:
                    score+="Thirty";
                    break;
                case 3:
                    score+="Forty";
                    break;
             }
	
        }
	return score;
    }
}
