/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.rockpaperscissors;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author board
 */
public class App {

    public static void main(String[] args) {
       
        boolean donePlaying = true;
        while (donePlaying) {
        int roundCount = 0;
        int compScore = 0;
        int humanScore = 0;
        int tied = 0;
        
        int roundsOfRps = numberOfRounds();
        
            for (int i = 0; i < roundsOfRps; i++) {

                String human = isHumanChoice();
                String computer = isCompChoice();

                roundCount++;

                System.out.println(" ^_^ Computer Chose: " + computer);
                System.out.println(" >_< HUMAN Chose: " + human);

                if (computer.equals("rock") && human.equals("scissors")) {
                    compScore++;
                    System.out.println(" Computer wins! Would you like some cake?");
                }
                if (computer.equals("scissors") && human.equals("paper")) {
                    compScore++;
                    System.out.println(" Computer wins! Would you like some cake?");
                }
                if (computer.equals("paper") && human.equals("rock")) {
                    compScore++;
                    System.out.println(" Computer wins! Would you like some cake?");
                }
                if (computer.equals("rock") && human.equals("paper")) {
                    humanScore++;
                    System.out.println(" HUMAN win!!");
                }
                if (computer.equals("scissors") && human.equals("rock")) {
                    humanScore++;
                    System.out.println(" HUMAN win!!");
                }
                if (computer.equals("paper") && human.equals("scissors")) {
                    humanScore++;
                    System.out.println(" HUMAN win!!");
                } else if (computer.equals(human)) {
                    tied++;
                    System.out.println(" Tie! ");

                }
                System.out.println("Rounds played: " + roundCount);
                System.out.println("Computer Score: " + compScore);
                System.out.println("HUMAN Score: " + humanScore);
                System.out.println("Tied Score: " + tied + "\n");
            }
            
            if (roundsOfRps == roundCount && humanScore > compScore) {
                System.out.println("HUMAN WON MAJORITY OF THE ROUNDS!!");
                
            } else if (roundsOfRps == roundCount && humanScore < compScore) {
                System.out.println("COMPUTER WON MAJORITY OF THE ROUNDS!!");
               
            } else {
                System.out.println("HUMAN TIED THE COMPUTER IN ALL ROUNDS!! GG ^_^");
                
            }
            
            donePlaying = playAgain();
        }
        
        
    }

    public static int numberOfRounds() {
        Scanner sc = new Scanner(System.in);

        System.out.println("How many rounds of |rock, paper, scissors| would you like to play? (1-10)");
        int roundsChosen = sc.nextInt();

        return roundsChosen;
    }

    public static String isHumanChoice() {
        String humanChoice = "The cake is a lie";

        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Let's PLAY! ENTER: rock, paper, or scissors");
            humanChoice = sc.nextLine();
            System.out.println();

        } while (!getValidChoice(humanChoice));

        return humanChoice;
    }

    public static String isCompChoice() {
        String computer = "The cake is NOT a lie";

        Random rng = new Random();
        int compChoice = rng.nextInt(3) + 1;

        if (compChoice == 1) {
            computer = "rock";
        }
        if (compChoice == 2) {
            computer = "paper";
        }
        if (compChoice == 3) {
            computer = "scissors";
        }

        return computer;
    }

    public static boolean getValidChoice(String rps) {
        boolean valid;
        switch (rps) {
            case "rock":
                valid = true;
                break;
            case "paper":
                valid = true;
                break;
            case "scissors":
                valid = true;
                break;
            default:
                valid = false;
                System.out.println("INVALID ENTRY! Please only type: rock, paper or scissors");
                break;
        }
        return valid;
    }

    public static boolean playAgain() {
        Scanner sc = new Scanner(System.in);
        String playAgain;
        boolean donePlaying = true;
        System.out.println("Play Again? yes/no");
        playAgain = sc.nextLine();
        while (donePlaying) {
            
             if (playAgain.equals("yes")) {
                donePlaying = true;
                break;
            } else if (playAgain.equals("no")) {
                donePlaying = false;
                System.out.println("Thanks for Playing!");
                break;
            } 

        }
        return donePlaying;
    }
}
