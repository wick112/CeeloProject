import java.util.Scanner;
public class Ceelo{
    private Scanner scanner;
    private Scanner scan;
    private Player player1;
    private Player player2;
    private Player player3;
    private int wager1;
    private int wager2;
    private int wager3;
    private Banker bank;
    private boolean ifPlayerTurn;
    private int round;
    private boolean gameOver;
    private String winner;

    public Ceelo(){
        this.scanner = new Scanner(System.in);
        this.scan = new Scanner(System.in);
        round = 1;
        ifPlayerTurn = true;
        gameOver = false;
        winner = "blank";
    }

    public void play(){
        introduce();
        System.out.println();
        mainMenu();
        // if ((player1.inGame() == false && player2.inGame() == false && player3.inGame() == false) || (bank.getChips() <= 0)){
        //   gameOver = true;
        // }
        // while (!gameOver){
        //   playRound();
        //   if (gameOver){
        //     checkWinner();
        //     end();
        //   }
        // }

    }

    public void introduce(){
        System.out.println("-----------------------------WELCOME TO THE CEELO DICE-----------------------------");
        System.out.println("Player 1, please enter your name: ");
        String p1 = scanner.nextLine();
        System.out.println("Player 2, please enter your name: ");
        String p2 = scanner.nextLine();
        System.out.println("Player 3, please enter your name: ");
        String p3 = scanner.nextLine();

        player1 = new Player(p1);
        player2 = new Player(p2);
        player3 = new Player(p3);
        bank = new Banker();

    }

    public void playRound(){
        ifPlayerTurn = true;
        System.out.println(ConsoleUtility.PURPLE + "Round " + round + ConsoleUtility.RESET + ": " + "\n");
        wager();
        bankersTurn();
        if(bank.ifWon() == false && bank.ifNotWon() == false){
            playersTurn();
        }
        isGameOver();
        round++;

    }


    public void wager(){
        if (player1.inGame() == true){
            System.out.println(player1.getName() + ", how many chips do you want to wager (Between 0 and " + player1.getChips() + "): ");
            wager1 = scanner.nextInt();
            while (wager1 < 0 || wager1 > player1.getChips()){
                System.out.println("Please enter a valid wager Player 1 (Between 0 and " + player1.getChips() + "): ");
                wager1 = scanner.nextInt();
                player1.setWagerAmt(wager1);
            }
        }

        if (player2.inGame() == true){
            System.out.println(player2.getName() + ", how many chips do you want to wager (Between 0 and " + player2.getChips() + "): ");
            wager2 = scanner.nextInt();
            while (wager2 < 0 || wager2 > player2.getChips()){
                System.out.println("Please enter a valid wager Player 2 (Between 0 and " + player2.getChips() + "): ");
                wager2 = scanner.nextInt();
                player2.setWagerAmt(wager2);
            }
        }

        if (player3.inGame() == true){
            System.out.println(player3.getName() + ", how many chips do you want to wager (Between 0 and " + player3.getChips() + "): ");
            wager3 = scanner.nextInt();
            while (wager3 < 0 || wager3 > player3.getChips()){
                System.out.println("Please enter a valid wager Player 3 (Between 0 and " + player3.getChips() + "): ");
                wager3 = scanner.nextInt();
                player3.setWagerAmt(wager3);
            }
        }
        System.out.println();
    }


    public void bankersTurn(){
        try {
            Thread.sleep(2000);  // 2000 milliseconds, or 2 seconds
        } catch (Exception e) {
            System.out.println("error");
        }

        ifPlayerTurn = true;
        bank.setWon();
        bank.setNotWon();
        bank.bankRoll();
        System.out.println(bank.checkDice());
        System.out.println("Banker rolled a " + bank.getD1() + ", " + bank.getD2() + ", " + bank.getD3() + "\n");
        if (bank.ifWon()){
            player1.addChips(-1 * wager1);
            bank.changeChipAmt(wager1);
            player2.addChips(-1 * wager2);
            bank.changeChipAmt(wager2);
            player3.addChips(-1 * wager3);
            bank.changeChipAmt(wager3);
        }else if (bank.ifWon() == false && bank.ifNotWon()){
            player1.addChips(wager1);
            bank.changeChipAmt(-1 * (wager1));
            player2.addChips(wager2);
            bank.changeChipAmt(-1 * (wager2));
            player3.addChips(wager3);
            bank.changeChipAmt(-1 * (wager3));
        }else{
            ifPlayerTurn = true;
        }
        System.out.println("The bankers balance is " + bank.getChips() + " chips" + "\n");


    }

    public void playersTurn(){
        try {
            Thread.sleep(2000);  // 2000 milliseconds, or 2 seconds
        } catch (Exception e) {
            System.out.println("error");
        }
        if (player1.inGame() == true){
            player1.playerRoll();
            System.out.println(player1.checkDice());
            System.out.println(player1.getName() + " rolled a " + player1.getD1() + ", " + player1.getD2() + ", " + player1.getD3());
            if (player1.getScore() >= bank.getScore() && player1.ifScored() ){
                player1.addChips(wager1);
                bank.changeChipAmt(-1 * (wager1));
                System.out.println(player1.getName() + " scored higher than the banker and gained " + wager1 + " chips.");
            }else if (player1.getScore() < bank.getScore() && player1.ifScored()){
                System.out.println(player1.getName() + " scored less than the banker and didn't gain any chips");
            }else if (player1.ifWon() && player1.ifScored() == false){
                player1.addChips(wager1);
                bank.changeChipAmt(-1 * (wager1));
                System.out.println(player1.getName() + " won the round and gained " + wager1 + " chips.");
            }else if (player1.ifWon() == false && player1.ifScored() == false){
                player1.addChips(-1 * wager1);
                bank.changeChipAmt(wager1);
                System.out.println(player1.getName() + " lost the round and gave up " + wager1 + " chips.");
            }
            System.out.println("Current Chip Balance: " + player1.getChips());
            System.out.println();
        }
        try {
            Thread.sleep(2000);  // 2000 milliseconds, or 2 seconds
        } catch (Exception e) {
            System.out.println("error");
        }

        if (player2.inGame() == true){
            player2.playerRoll();
            System.out.println(player2.checkDice());
            System.out.println(player2.getName() + " rolled a " + player2.getD1() + ", " + player2.getD2() + ", " + player2.getD3());
            if (player2.getScore() >= bank.getScore() && player2.ifScored()){
                player2.addChips(wager2);
                bank.changeChipAmt(-1 * (wager2));
                System.out.println(player2.getName() + " scored higher than the banker and gained " + wager2 + " chips.");
            }else if (player2.getScore() < bank.getScore() && player2.ifScored()){
                System.out.println(player2.getName() + " scored less than the banker and didn't gain any chips");
            }else if (player2.ifWon() && player2.ifScored() == false){
                player2.addChips(wager2);
                bank.changeChipAmt(-1 * (wager2));
                System.out.println(player2.getName() + " won the round and gained " + wager2 + " chips.");
            }else if (player2.ifWon() == false && player2.ifScored() == false){
                player2.addChips(-1 * wager2);
                bank.changeChipAmt(wager2);
                System.out.println(player2.getName() + " lost the round and gave up " + wager2 + " chips.");
            }
            System.out.println("Current Chip Balance: " + player2.getChips());
            System.out.println();

        }
        try {
            Thread.sleep(2000);  // 2000 milliseconds, or 2 seconds
        } catch (Exception e) {
            System.out.println("error");
        }

        if (player3.inGame() == true){
            player3.playerRoll();
            System.out.println(player3.checkDice());
            System.out.println(player3.getName() + " rolled a " + player3.getD1() + ", " + player3.getD2() + ", " + player3.getD3());
            if (player3.getScore() >= bank.getScore() && player3.ifScored()){
                player3.addChips(wager3);
                bank.changeChipAmt(-1 * (wager3));
                System.out.println(player3.getName() + " scored higher than the banker and gained " + wager3 + " chips.");
            }else if (player3.getScore() < bank.getScore() && player3.ifScored()){
                System.out.println(player3.getName() + " scored less than the banker and didn't gain any chips");
            }else if (player3.ifWon() && player3.ifScored() == false){
                player3.addChips(wager3);
                bank.changeChipAmt(-1 * (wager3));
                System.out.println(player3.getName() + " won the round and gained " + wager3 + " chips.");
            }else if (player3.ifWon() == false && player3.ifScored() == false){
                player3.addChips(-1 * wager3);
                bank.changeChipAmt(wager3);
                System.out.println(player3.getName() + " lost the round and gave up " + wager3 + " chips.");

            }
            System.out.println("Current Chip Balance: " + player3.getChips());
            System.out.println();

        }
    }


    public void end(){
        System.out.println("Would you like to play again? (y/n)");
        String playAgain = scan.nextLine();
        System.out.println();
        if (playAgain.equals("y")){
            player1.reset();
            player2.reset();
            player3.reset();
            bank.reset();
            gameOver = false;
            round = 1;
            mainMenu();


        }else if (playAgain.equals("n")){
            System.out.println("Thank you for playing! Come back soon. 💜");
        }
    }

    public void checkWinner(){
        if(bank.getChips() <= 0 && player1.inGame() && player1.getChips() >= player2.getChips() && player1.getChips() >= player3.getChips()){
            System.out.println("The banker has run out of chips");
            winner = "Player 1";
            player1.addWin();
        }else if(player2.inGame() && player2.getChips() >= player1.getChips() && player2.getChips() >= player3.getChips()){
            System.out.println("The banker has run out of chips");
            winner = "Player 2";
            player2.addWin();
        }else if(player3.inGame() && player3.getChips() >= player2.getChips() && player3.getChips() >= player1.getChips()){
            System.out.println("The banker has run out of chips");
            winner = "Player 3";
            player3.addWin();
        }else if (player1.inGame() == false && player2.inGame() == false && player3.inGame() == false){
            System.out.println("All players have lost their chips and have been eliminated");
            winner = "banker";
        }

        System.out.println(ConsoleUtility.YELLOW + "Winner: " + ConsoleUtility.RESET + winner);
    }


    public boolean isGameOver(){
        if ((player1.inGame() == false && player2.inGame() == false && player3.inGame() == false) || (bank.getChips() <= 0)){
            gameOver = true;
        }
        return gameOver;
    }

    public void mainMenu(){
        System.out.println("-------" + ConsoleUtility.CYAN + "Main Menu" + ConsoleUtility.RESET + "-------");
        System.out.println("(" + ConsoleUtility.GREEN + "a" + ConsoleUtility.RESET + ") - Start new Game ✅");
        System.out.println("(" + ConsoleUtility.YELLOW + "b" + ConsoleUtility.RESET + ") - View Top Score 🏆");
        System.out.println("(" + ConsoleUtility.RED + "c" + ConsoleUtility.RESET + ") - Quit ❌");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(ConsoleUtility.CYAN + "-----------------------" + ConsoleUtility.RESET);
        String choice = scan.nextLine();
        while (!(choice.equals("a") || (choice.equals("b") || (choice.equals("c") )))){
            System.out.println("Please enter a valid option");
            choice = scan.nextLine();
        }
        if (choice.equals("a")){
            if ((player1.inGame() == false && player2.inGame() == false && player3.inGame() == false) || (bank.getChips() <= 0)){
                gameOver = true;
            }
            while (!gameOver){
                playRound();
                if (gameOver){
                    checkWinner();
                    end();
                }
            }
        }
        else if (choice.equals("b")){
            checkTop();
            System.out.println();
            mainMenu();
        }else if (choice.equals("c")){
            System.out.println("Thanks for playing! Come back soon. 💜");
            System.exit(0);
        }
    }

    public void checkTop(){
        if(player1.getWins() >= player2.getWins() && player1.getWins() >= player3.getWins()){
            System.out.println("Top Score: Player 1: " + player1.getName());
        }else if(player2.getWins() >= player1.getWins() && player2.getWins() >= player3.getWins()){
            System.out.println("Top Score: Player 2: " + player2.getName());
        }else if(player3.getWins() >= player1.getWins() && player3.getWins() >= player2.getWins()){
            System.out.println("Top Score: Player 3: " + player3.getName());
        }
    }


}
