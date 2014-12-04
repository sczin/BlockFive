package com.bayviewglen.blackjack;
import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;


public class Blackjack {


    public static int DEALER_MUST_HIT = 17;
    public static int BUST = 21;
    public static int ACE_REDUCTION = 10;
    public static int FACE_CARD_POINTS = 10;
    public static int CARDS_PER_SUIT = 13;
    public static int NUMBER_OF_SUITS = 4;

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        boolean done = false;
        while (!done) {
            int wallet = 500;
            String name = getUserName(keyboard);
            if (name.equalsIgnoreCase("quit")) done = true;
            else {
                Locale locale = getLocale();
                NumberFormat nFormat = NumberFormat.getCurrencyInstance(locale);
                boolean playAgain = true;
                while (playAgain) {
                    int bet = getBet(wallet, locale);
                    wallet = wallet - bet;
                    String word = "Beginning";
                    System.out.println(getHeader(word));
                    String dealerHand = "";
                    String playerHand = "";
                    int dealerSum = 0;
                    int playerSum = 0;
                    int dealerAceNum = 0;
                    int playerAceNum = 0;
                    //dealer getting a card
                    int card = dealtCard();
                    char suit = dealtSuit();
                    dealerSum = dealerSum + dealtValue(card);
                    if (dealtAce(card)) dealerAceNum++;
                    if (card != 1 && card <= 10) {
                        dealerHand = dealerHand + card + suit + " ";
                    }
                    if (card == 11) {
                        dealerHand = dealerHand + 'J' + suit + " ";
                    }
                    if (card == 12) {
                        dealerHand = dealerHand + 'Q' + suit + " ";
                    }
                    if (card == 13) {
                        dealerHand = dealerHand + 'K' + suit + " ";
                    }
                    if (card == 1) {
                        dealerHand = dealerHand + 'A' + suit + " ";
                    }
                    //dealer got card and card was added to the string
                    System.out.println("Dealer: " + dealerHand + "XX" + "..." + dealerSum);
                    //player getting two cards
                    for (int i = 0; i < 2; i++) {
                        card = dealtCard();
                        suit = dealtSuit();
                        playerSum = playerSum + dealtValue(card);
                        if (dealtAce(card)) playerAceNum++;
                        if (card != 1 && card <= 10) {
                            playerHand = playerHand + card + suit + " ";
                        }
                        if (card == 11) {
                            playerHand = playerHand + 'J' + suit + " ";
                        }
                        if (card == 12) {
                            playerHand = playerHand + 'Q' + suit + " ";
                        }
                        if (card == 13) {
                            playerHand = playerHand + 'K' + suit + " ";
                        }
                        if (card == 1) {
                            playerHand = playerHand + 'A' + suit + " ";
                        }
                    }
                    //player got card and card was added to the string
                    System.out.println("Player: " + playerHand + "..." + playerSum);
                    boolean doubleddown = getDoubledownChoice(bet, wallet);
                    if (doubleddown) {
                        wallet = wallet - bet;
                        bet = bet * 2;
                        card = dealtCard();
                        suit = dealtSuit();
                        playerSum = playerSum + dealtValue(card);
                        if (dealtAce(card)) playerAceNum++;
                        if (card != 1 && card <= 10) {
                            playerHand = playerHand + card + suit + " ";
                        }
                        if (card == 11) {
                            playerHand = playerHand + 'J' + suit + " ";
                        }
                        if (card == 12) {
                            playerHand = playerHand + 'Q' + suit + " ";
                        }
                        if (card == 13) {
                            playerHand = playerHand + 'K' + suit + " ";
                        }
                        if (card == 1) {
                            playerHand = playerHand + 'A' + suit + " ";
                        }
                        if (playerSum > 21 && playerAceNum != 0) {
                            while (playerSum > 21 && playerAceNum != 0) {
                                playerSum = playerSum - 10;
                                playerAceNum--;
                            }
                        }
                        while (dealerSum < 17 && dealerSum < playerSum) {
                            card = dealtCard();
                            suit = dealtSuit();
                            dealerSum = dealerSum + dealtValue(card);
                            if (dealtAce(card)) dealerAceNum++;
                            if (card != 1 && card <= 10) {
                                dealerHand = dealerHand + card + suit + " ";
                            }
                            if (card == 11) {
                                dealerHand = dealerHand + 'J' + suit + " ";
                            }
                            if (card == 12) {
                                dealerHand = dealerHand + 'Q' + suit + " ";
                            }
                            if (card == 13) {
                                dealerHand = dealerHand + 'K' + suit + " ";
                            }
                            if (card == 1) {
                                dealerHand = dealerHand + 'A' + suit + " ";
                            }
                        }
                    } else {
                        boolean hit = getHitChoice();
                        while (hit) {
                            card = dealtCard();
                            suit = dealtSuit();
                            playerSum = playerSum + dealtValue(card);
                            if (dealtAce(card)) playerAceNum++;
                            if (card != 1 && card <= 10) {
                                playerHand = playerHand + card + suit + " ";
                            }
                            if (card == 11) {
                                playerHand = playerHand + 'J' + suit + " ";
                            }
                            if (card == 12) {
                                playerHand = playerHand + 'Q' + suit + " ";
                            }
                            if (card == 13) {
                                playerHand = playerHand + 'K' + suit + " ";
                            }
                            if (card == 1) {
                                playerHand = playerHand + 'A' + suit + " ";
                            }
                            if (playerSum > 21 && playerAceNum != 0) {
                                while (playerSum > 21 && playerAceNum != 0) {
                                    playerSum = playerSum - 10;
                                    playerAceNum--;
                                }

                            }
                            if (playerSum > 21) {
                                while (dealerSum < 17 && dealerSum < playerSum) {
                                    card = dealtCard();
                                    suit = dealtSuit();
                                    dealerSum = dealerSum + dealtValue(card);
                                    if (dealtAce(card)) dealerAceNum++;
                                    if (card != 1 && card <= 10) {
                                        dealerHand = dealerHand + card + suit + " ";
                                    }
                                    if (card == 11) {
                                        dealerHand = dealerHand + 'J' + suit + " ";
                                    }
                                    if (card == 12) {
                                        dealerHand = dealerHand + 'Q' + suit + " ";
                                    }
                                    if (card == 13) {
                                        dealerHand = dealerHand + 'K' + suit + " ";
                                    }
                                    if (card == 1) {
                                        dealerHand = dealerHand + 'A' + suit + " ";
                                    }
                                }
                                hit = false;

                            } else {
                                System.out.println("Player: " + playerHand + "..." + playerSum);
                                System.out.println("Dealer: " + dealerHand + "XX" + "..." + dealerSum);
                                hit = getHitChoice();
                            }
                        }
                        if (playerSum > 21 && playerAceNum != 0) {
                            while (playerSum > 21 && playerAceNum != 0) {
                                playerSum = playerSum - 10;
                                playerAceNum--;
                            }
                            if (playerSum > 21) {

                                while (dealerSum < 17 && dealerSum < playerSum) {
                                    card = dealtCard();
                                    suit = dealtSuit();
                                    dealerSum = dealerSum + dealtValue(card);
                                    if (dealtAce(card)) dealerAceNum++;
                                    if (card != 1 && card <= 10) {
                                        dealerHand = dealerHand + card + suit + " ";
                                    }
                                    if (card == 11) {
                                        dealerHand = dealerHand + 'J' + suit + " ";
                                    }
                                    if (card == 12) {
                                        dealerHand = dealerHand + 'Q' + suit + " ";
                                    }
                                    if (card == 13) {
                                        dealerHand = dealerHand + 'K' + suit + " ";
                                    }
                                    if (card == 1) {
                                        dealerHand = dealerHand + 'A' + suit + " ";
                                    }
                                }

                            }

                        }

                    }
                    while (dealerSum < 17 && dealerSum < playerSum) {
                        card = dealtCard();
                        suit = dealtSuit();
                        dealerSum = dealerSum + dealtValue(card);
                        if (dealtAce(card)) dealerAceNum++;
                        if (card != 1 && card <= 10) {
                            dealerHand = dealerHand + card + suit + " ";
                        }
                        if (card == 11) {
                            dealerHand = dealerHand + 'J' + suit + " ";
                        }
                        if (card == 12) {
                            dealerHand = dealerHand + 'Q' + suit + " ";
                        }
                        if (card == 13) {
                            dealerHand = dealerHand + 'K' + suit + " ";
                        }
                        if (card == 1) {
                            dealerHand = dealerHand + 'A' + suit + " ";
                        }
                        if (dealerSum > 21 && dealerAceNum != 0) {
                            while (dealerSum > 21 && dealerAceNum != 0) {
                                dealerSum = dealerSum - 10;
                                dealerAceNum--;
                            }
                        }
                    }
                    System.out.println("Player: " + playerHand + "..." + playerSum);
                    System.out.println("Dealer: " + dealerHand + "..." + dealerSum);
                    if ((playerSum > dealerSum && playerSum < 22) || (playerSum < 22 && dealerSum > 21)) {
                        wallet = wallet + 2 * bet;
                        System.out.println("You won. Wallet: " + nFormat.format(wallet));
                    } else {
                        System.out.println("You lost. Wallet: " + nFormat.format(wallet));
                    }
                    boolean play = true;
                    if (wallet < 1) {
                        playAgain = false;
                    } else if (play) {
                        playAgain = getPlay();
                    }
                }
            }
        }
    }

    private static boolean getPlay() {
        boolean y = false;
        try {
            Scanner keyboard = new Scanner(System.in);
            boolean validinput = false;
            while (!validinput) {
                System.out.println("would you like to play another round? (Y/N)");
                String answer = keyboard.nextLine();
                if (answer.equalsIgnoreCase("Y")) {
                    y = true;
                    validinput = true;
                } else if (answer.equalsIgnoreCase("N")) {
                    y = false;
                    validinput = true;
                }
            }
        } catch (InputMismatchException ex) {

        }
        return y;
    }

    private static boolean getHitChoice() {
        boolean y = false;
        try {
            Scanner keyboard = new Scanner(System.in);
            boolean validinput = false;
            while (!validinput) {
                System.out.println("would you like to hit? (Y/N)");
                String answer = keyboard.nextLine();
                if (answer.equalsIgnoreCase("Y")) {
                    y = true;
                    validinput = true;
                } else if (answer.equalsIgnoreCase("N")) {
                    y = false;
                    validinput = true;
                }
            }
        } catch (InputMismatchException ex) {

        }
        return y;
    }

    private static boolean getDoubledownChoice(int bet, int wallet) {
        boolean y = false;
        if (!(bet > wallet)) {
            try {
                Scanner keyboard = new Scanner(System.in);
                boolean validinput = false;
                while (!validinput) {
                    System.out.println("would you like to double down? (Y/N)");
                    String answer = keyboard.nextLine();
                    if (answer.equalsIgnoreCase("Y")) {
                        y = true;
                        validinput = true;
                    } else if (answer.equalsIgnoreCase("N")) {
                        y = false;
                        validinput = true;
                    }
                }
            } catch (InputMismatchException ex) {

            }

        }
        return y;
    }
    private static char dealtSuit() {
        int suitNumber = getASuitNumber();
        char dealtSuit;
        if (suitNumber == 1) {
            dealtSuit = 'D';
        } else if (suitNumber == 2) {
            dealtSuit = 'S';
        } else if (suitNumber == 3) {
            dealtSuit = 'C';
        } else {
            dealtSuit = 'H';
        }
        return dealtSuit;
    }
    private static boolean dealtAce(int card) {
        boolean dealtAce = false;
        if (card == 1) {
            dealtAce = true;
        }
        return dealtAce;
    }
    private static int dealtValue(int card) {
        int dealtValue = 0;
        if (card == 1) {
            dealtValue = +11;
        } else if (card == 11) {
            dealtValue = +10;
        } else if (card == 12) {
            dealtValue = +10;
        } else if (card == 13) {
            dealtValue = +10;
        } else {
            dealtValue = +card;
        }
        return dealtValue;
    }
    private static int dealtCard() {
        int cardNumber = getACardNumber();
        return cardNumber;
    }

    private static int getACardNumber() {
        int n = (int)((Math.random() * CARDS_PER_SUIT + 1));
        return n;
    }
    private static int getASuitNumber() {
        int n = (int)((Math.random() * NUMBER_OF_SUITS + 1));
        return n;
    }
    private static String getHeader(String word) {
        String s = "";
        for (int i = 0; i < 30; i++) {
            s = s + "-";
        }
        s = s + word;
        for (int i = 0; i < 30; i++) {
            s = s + "-";
        }
        return s;
    }


    private static int getBet(int wallet, Locale locale) {
        boolean validinput = false;
        int n = 0;
        NumberFormat nFormat = NumberFormat.getCurrencyInstance(locale);
        while (!validinput) {
            System.out.print("Please enter a positive integer to place a bet. You have ");
            System.out.println(nFormat.format(wallet));
            try {
                Scanner keyboard = new Scanner(System.in); //i don't close this in the scope because when i do the code doesn't work
                n = keyboard.nextInt();
                if (n <= wallet && n > 0) {
                    validinput = true;
                }
            } catch (InputMismatchException ex) {

            }
        }
        return n;
    }

    private static Locale getLocale() {



        Locale locale = null;
        int n = 0;
        boolean validinput = false;

        while (!validinput) {
            System.out.printf("Please enter the corresponding number for your country\n1. Canada\n2. USA\n3. France\n4. German\n5. China\n6. Italy\n");
            try {
                Scanner keyboard = new Scanner(System.in);
                n = keyboard.nextInt();
                if (!(n < 1 || n > 6)) {
                    validinput = true;
                    if (n == 1) locale = Locale.CANADA;
                    if (n == 2) locale = Locale.US;
                    if (n == 3) locale = Locale.FRANCE;
                    if (n == 4) locale = Locale.GERMAN;
                    if (n == 5) locale = Locale.CHINA;
                    if (n == 6) locale = Locale.ITALY;
                    System.out.println("Your locale is: " + locale);
                } else validinput = false;
            } catch (InputMismatchException ex) {

            }

        }
        return locale;
    }

    private static String getUserName(Scanner keyboard) {
        System.out.println("Please enter your name or \"quit\"");
        String name = keyboard.nextLine();
        return name;
    }

}