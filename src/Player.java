public class Player{
    private int chips;
    private int score;
    private String name;
    private boolean won;
    private boolean inGame;
    private Die d = new Die();
    private int wagerAmt;
    private boolean scored;
    private int wins;

    public Player(String name){
        this.name = name;
        chips = 100;
        won = false;
        inGame = true;
        scored = false;
        wins = 0;
    }


    public void playerRoll(){
        d.rollDice1();
        d.rollDice2();
        d.rollDice3();
    }

    public String checkDice(){
        while ((won != true || won != false) || score < 1){
            if ((getD1() == getD2()) && (getD1() == getD3())){
                won = true;
                scored = false;
                return name + " wins the round!";
            }else if((getD1() == 4 && getD2() == 5 && getD3() == 6)||
                    (getD1() == 4 && getD2() == 6 && getD3() == 5)||
                    (getD1() == 5 && getD2() == 4 && getD3() == 6) ||
                    (getD1() == 5 && getD2() == 6 && getD3() == 4) ||
                    (getD1() == 6 && getD2() == 4 && getD3() == 5) ||
                    (getD1() == 6 && getD2() == 5 && getD3() == 4)) {
                won = true;
                scored = false;
                return name + " wins the round!";
            }else if((getD1() == getD2()) || (getD2() == getD3()) || (getD1() == getD3())){
                if (getD1() == getD2()){
                    score = getD3();
                }
                if (getD2() == getD3()){
                    score = getD1();
                }
                if (getD1() == getD3()){
                    score = getD2();
                }
                scored = true;
                return name + " gets a score of " + score;
            }else if((getD1() == 1 && getD2() == 2 && getD3() == 3)||
                    (getD1() == 1 && getD2() == 3 && getD3() == 2)||
                    (getD1() == 2 && getD2() == 1 && getD3() == 3) ||
                    (getD1() == 2 && getD2() == 3 && getD3() == 1) ||
                    (getD1() == 3 && getD2() == 1 && getD3() == 2) ||
                    (getD1() == 3 && getD2() == 2 && getD3() == 1)) {
                won = false;
                scored = false;
                return name + " loses the round!";
            }else{
                playerRoll();
            }
        }
        return "";
    }

    public boolean ifScored(){
        return scored;
    }
    public boolean inGame(){
        if (chips <= 0){
            inGame = false;
        }
        return inGame;
    }

    public boolean ifWon(){
        return won;
    }

    public int getD1(){
        return d.getDice1();
    }
    public int getD2(){
        return d.getDice2();
    }
    public int getD3(){
        return d.getDice3();
    }

    public int getChips(){
        return chips;
    }

    public String getName(){
        return name;
    }

    public int getScore(){
        return score;
    }

    public void playerRollTest(){
        d.rollDice1();
        d.rollDice2();
        d.rollDice3();
    }

    public void setWagerAmt(int amt){
        wagerAmt = amt;
    }

    public int getWagerAmt(){
        return wagerAmt;
    }

    public void addChips(int amt){
        chips += amt;
    }

    public void setInGame(boolean bool){
        inGame = bool;
    }

    public void reset(){
        chips = 100;
        won = false;
        inGame = true;
        scored = false;
    }

    public void addWin(){
        wins++;
    }

    public int getWins(){
        return wins;
    }



}
