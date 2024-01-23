public class Die{
    private static int dice1;
    private static int dice2;
    private static int dice3;


    public Die(){
        dice1 = -1;
        dice2 = -1;
        dice3 = -1;

    }

    public int getDice1(){
        return dice1;
    }

    public int getDice2(){
        return dice2;
    }

    public int getDice3(){
        return dice3;
    }

    public void setDice1(int newNum){
        dice1 = newNum;
    }

    public void setDice2(int newNum){
        dice2 = newNum;
    }

    public void setDice3(int newNum){
        dice3 = newNum;
    }


    public void rollDice1(){
        int d1Roll = (int)(Math.random() * 6) + 1;
        dice1 = d1Roll;
    }

    public void rollDice2(){
        int d2Roll = (int)(Math.random() * 6) + 1;
        dice2 = d2Roll;
    }

    public void rollDice3(){
        int d3Roll = (int)(Math.random() * 6) + 1;
        dice3 = d3Roll;
    }
}