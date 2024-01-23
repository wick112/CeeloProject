public class Banker{
    private int chips;
    private int score;
    private boolean won;
    private Die d = new Die();
    private boolean scored;
    private boolean notWon;

    public Banker(){
        chips = 1000;
        won = false;
        scored = false;
        notWon = false;

    }

    public void changeChipAmt(int amt){
        chips += amt;
    }

    public int getChips(){
        return chips;
    }

    public boolean ifWon(){
        return won;
    }

    public boolean ifNotWon(){
        return notWon;
    }

    public int getScore(){
        return score;
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

    public void setDice1(int val){
        d.setDice1(val);
    }
    public void setDice2(int val){
        d.setDice2(val);
    }
    public void setDice3(int val){
        d.setDice3(val);
    }

    public void setWon(){
        won = false;
    }

    public void setNotWon(){
        notWon = false;
    }

    public void reset(){
        chips = 1000;
        won = false;
        scored = false;
    }


    public void bankRoll(){
        d.rollDice1();
        d.rollDice2();
        d.rollDice3();
    }


    public String checkDice(){
        while ((won != true || won != false) || score < 1){
            if ((getD1() == getD2()) && (getD1() == getD3())){
                won = true;
                return "Banker wins the round, player turns skipped";
            }else if((getD1() == 4 && getD2() == 5 && getD3() == 6)||
                    (getD1() == 4 && getD2() == 6 && getD3() == 5)||
                    (getD1() == 5 && getD2() == 4 && getD3() == 6) ||
                    (getD1() == 5 && getD2() == 6 && getD3() == 4) ||
                    (getD1() == 6 && getD2() == 4 && getD3() == 5) ||
                    (getD1() == 6 && getD2() == 5 && getD3() == 4)) {
                won = true;
                return "Banker wins the round, players turns skipped and wagers lost.";
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
                return "Banker gets a score of " + score + ", it is now the players turn";
            }else if((getD1() == 1 && getD2() == 2 && getD3() == 3)||
                    (getD1() == 1 && getD2() == 3 && getD3() == 2)||
                    (getD1() == 2 && getD2() == 1 && getD3() == 3) ||
                    (getD1() == 2 && getD2() == 3 && getD3() == 1) ||
                    (getD1() == 3 && getD2() == 1 && getD3() == 2) ||
                    (getD1() == 3 && getD2() == 2 && getD3() == 1)) {
                won = false;
                notWon = true;
                return "Banker lost the round, all players receive their wagered amount";
            }else{
                bankRoll();
            }
        }
        return "";
    }

    public void bankRollTest(){
        d.rollDice1();
        d.rollDice2();
        d.rollDice3();
    }
}
