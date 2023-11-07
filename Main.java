//Author: Krishna Garg
//Coding the Double Trouble Game (also known as nim game (haha I got the bonus game)
//The solver of this game was Charles Bouton
/*
There are three heaps of markers
There are
3 Green markers
7 Yellow Markers
5 Red Markers

Rules
1. The players whose turn it is can choose to eliminate as many markers from a single color category as they want in one turn
2. They can switch their color of choice for the next turn
3. Whoever gets rid of the last marker (or in other words is the reason the board gets cleared is the winner)



//Winning Conditions
//Whoevers turn it is when the xor_sum is NON ZERO is in the winning position
//if the computer is in the winning position then it should do some calculation to make the xor_sum zero
//
 */

import java.util.Scanner;
import java.util.Random;
class globalVars{
    public static int num_green = 3;
    public static int num_red = 5;
    public static int num_yellow = 7;

    public static int xor_sum = num_green ^ num_red ^ num_yellow;
}

public class Main {
    public static void main(String[] args) {
        //boolean end_game = false;
        int player; // 0 is the user and 1 is the computer

        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Double Trouble (or the nim game)\nRules\n" +
                "1. The players whose turn it is can choose to eliminate as many markers from a single color category as they want in one turn" +
                "\n2. They can switch their color of choice for the next turn" +
                "\n3. Whoever gets rid of the last marker (or in other words is the reason the board gets cleared is the winner)\n");

        System.out.println("Enter [Computer] if you would like the computer to start off with the game\n" +
                "If you want the first turn enter [Me]\n");
        while(true){
            String entered = input.nextLine();
            if (entered.equalsIgnoreCase("computer")) {
                player = 1;
                break;
            }

            else if(entered.equalsIgnoreCase("me")){
                player = 0;
                break;
            }
            System.out.println("Please enter either me or computer to choose the first player");
        }


        //play the user algorithm here in a while loop
        int i;
        String winner;
        for(i = 0; i > -1; i++){
            //print the current state of the game
            System.out.println("Xor Sum = " + globalVars.xor_sum);
            System.out.println("Green = " + globalVars.num_green);
            System.out.println("Yellow = " + globalVars.num_yellow);
            System.out.println("Orange = " + globalVars.num_red +"\n\n");
            boolean replay = false;
            if(player == 0){//the user
                System.out.println("Its your turn now\n" +
                        "Which Color would you like to target");
                String color = input.next();

                System.out.println("How many markers would you like to get rid of from the " + color + " category." +
                        "\nPlease enter the value in number do not write out the word of the number");
                int num = input.nextInt();//the input is the number

                if(color.equalsIgnoreCase("green") && num <= globalVars.num_green && num > 0){
                    globalVars.num_green -= num;
                }

                else if(color.equalsIgnoreCase("orange") && num <= globalVars.num_red && num > 0){
                    globalVars.num_red -= num;
                }

                else if(color.equalsIgnoreCase("yellow") && num <= globalVars.num_yellow && num > 0){
                    globalVars.num_yellow -= num;
                }
                else {
                        System.out.println("You inputted either an invalid color or value");
                        replay = true;
                }

            }//if it is the players turn

            else if(player == 1){//computers turn
                System.out.println("Computer is playing");
                Helpers here = new Helpers();
                if(i == 0){ //we want to choose a random color and remove one from it
                    Random random_gen = new Random();
                    boolean checker = false;
                    //if the color chosen is depleted then it will run the loop
                    while(!checker){
                        int color = random_gen.nextInt(3);
                        //0 is green
                        if(color == 0 && globalVars.num_green > 0){
                            int dec = 1;
                            System.out.println("Removing " + dec + " from green");
                            globalVars.num_green -= dec;
                            checker = true;
                        }
                        //1 is red
                        else if(color == 1 && globalVars.num_red > 0){

                            int dec = 1;
                            System.out.println("Removing " + dec + " from orange");
                            globalVars.num_red -= dec;
                            checker = true;
                        }

                        //2 is yellow
                        else if(color == 2 && globalVars.num_yellow > 0) {
                            int dec = 1;
                            System.out.println("Removing " + dec + " from yellow");
                            globalVars.num_yellow -= dec;
                            checker = true;
                        }
                    }//end while
                }
                else {//randomly generate numbers to deduct fro a random color
                    here.com_play();
                }
                System.out.println("Computer finished its turn");
            }
            globalVars.xor_sum = globalVars.num_green ^ globalVars.num_red ^ globalVars.num_yellow;

            if(globalVars.num_yellow == 0 && globalVars.num_red == 0 && globalVars.num_green == 0){
                break;
            }

            //switch whose turn it is here
            if(player == 1 && replay == false){
                player = 0;//now its the users turn on the next run
            }
            else if(player == 0 && replay == false){
                player = 1;//now its the computers turn on the next run
            }
        }//end of for loop

        if(player == 1){
            winner = "Computer";
        }
        else{
            winner = "You";
        }

        System.out.println("End of Game\nWinner is: " + winner);
    }//end of main function

}//end of Main class
class Helpers{
    public Helpers(){}
    //the inputs are the amount of markers left in each color of the game
    void com_play(){
        boolean checker = false;
        //first check if there is a winning condition
        if(globalVars.num_green != 0 && globalVars.num_red == 0 && globalVars.num_yellow == 0){
            globalVars.num_green -= globalVars.num_green;
        }

        else if(globalVars.num_green == 0 && globalVars.num_red != 0 && globalVars.num_yellow == 0){
            globalVars.num_red -= globalVars.num_red;
        }

        else if(globalVars.num_green == 0 && globalVars.num_red == 0 && globalVars.num_yellow != 0){
            globalVars.num_yellow -= globalVars.num_yellow;
        }
        //other winning conditions
        else if(globalVars.xor_sum != 0){//we want to make the xor sum zero if it is not to maintain the winning position
            if(globalVars.num_green > (globalVars.num_red ^ globalVars.num_yellow)){
                int prev = globalVars.num_green;
                globalVars.num_green = (globalVars.num_red ^ globalVars.num_yellow);
                System.out.println("Removed "+ (prev - globalVars.num_green) + " from green");
            }

            if(globalVars.num_yellow > (globalVars.num_red ^ globalVars.num_green)){
                int prev = globalVars.num_yellow;
                globalVars.num_yellow = (globalVars.num_red ^ globalVars.num_green);
                System.out.println("Removed "+ (prev - globalVars.num_yellow) + " from yellow");
            }

            if(globalVars.num_red > (globalVars.num_green ^ globalVars.num_yellow)){
                int prev = globalVars.num_red;
                globalVars.num_red = (globalVars.num_green ^ globalVars.num_yellow);
                System.out.println("Removed "+ (prev - globalVars.num_red) + " from orange");
            }
        }

        //if there is not a winning condition pick a random color and a random integer to deduct that amount of markers
        //hope that the person makes a mistake
        else if(globalVars.xor_sum == 0){
            //randomly generate a number between 0 and 3
            Random random_gen = new Random();

            //if the color chosen is depleted then it will run the loop
            while(!checker){
                int color = random_gen.nextInt(3);
                //0 is green
                if(color == 0 && globalVars.num_green > 0){

                    int dec = random_gen.nextInt(globalVars.num_green) + 1;
                    System.out.println("Removed "+ dec + " from green");
                    globalVars.num_green -= dec;
                    checker = true;
                }
                //1 is red
                else if(color == 1 && globalVars.num_red > 0){
                    int dec = random_gen.nextInt(globalVars.num_red) + 1;
                    System.out.println("Removed "+ dec + " from orange");
                    globalVars.num_red -= dec;
                    checker = true;
                }

                //2 is yellow
                else if(color == 2 && globalVars.num_yellow > 0) {
                    int dec = random_gen.nextInt(globalVars.num_yellow) + 1;
                    System.out.println("Removed "+ dec + " from yellow");
                    globalVars.num_yellow -= dec;
                    checker = true;
                }
            }//end while
        }//end else

    }//end com_play

}
//Helper Functions