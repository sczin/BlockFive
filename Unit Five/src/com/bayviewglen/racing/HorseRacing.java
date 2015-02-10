package com.bayviewglen.racing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class HorseRacing {
	
	  private static final String[] allHorses = getAllHorses(); //first element (element 0) is number of horses, then horse names follow
	  private static final Locale nformat = getLocale(); 
	  private static final  NumberFormat locale = NumberFormat.getCurrencyInstance(nformat);

	  
    public static void main(String[] args) {
        String[][] allPlayerData = getAllPlayers(); 
        int[] wallets = parseIntArray(allPlayerData[1]); //takes the strings of wallets and parses it into a parallel array 
  
        
        boolean play = true;

        while (play) {

            int numberInRace = numberInRace(allHorses);
            int[] raceHorses = getRaceHorses(allHorses, numberInRace);
            String[] raceHorsesNamed = arrayHoldingIndex(raceHorses, allHorses);



    

            int[][] playerBets = new int[2][allPlayerData[0].length]; //[0] is contains bet amount, [1] contains horse number

            betPrompt(allPlayerData[0], locale, raceHorsesNamed, playerBets, wallets);




            int[] parallelDistances = new int[raceHorsesNamed.length]; //holds the number of " " to be output in order to shift the horse position
            int trackLength = displayHorses(raceHorsesNamed, parallelDistances); 
            int[] winningHorses = winningHorse(parallelDistances, trackLength);
            String[] namedWinners = arrayHoldingIndex(winningHorses, raceHorsesNamed);

            int indexOfWinningHorse = handlingTie(winningHorses, namedWinners);
            System.out.println(raceHorsesNamed[indexOfWinningHorse] + " won");

            
            
            updateWallets(wallets, playerBets, indexOfWinningHorse, allPlayerData[0]);
            System.out.println("Would you like to play again? (N for No)");
            Scanner keyboard = new Scanner(System.in);
            String str = keyboard.nextLine();
            if ("N".equalsIgnoreCase(str)) {
                play = false;
                
                
              
            }
        }
        iterateAsTable(allPlayerData, wallets, locale);
        writingToFile(allPlayerData[0], wallets);
    }

   

    /** takes array of strings containing number of names and names and their corresponding parallel wallets
     *  overwrites file, outputs on each line number of names, then name and wallet
     */
    private static void writingToFile(String[] strings, int[] wallets) {
        PrintWriter writer;
        try {
            writer = new PrintWriter("input/playerData.dat", "UTF-8");
            writer.println(strings.length - 1);
            for (int i = 1; i < wallets.length; i++) {
                writer.println(strings[i] + " " + wallets[i]);
            }
            writer.close();
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        }

    }


    private static void updateWallets(int[] wallets, int[][] playerBets, int indexOfWinningHorse, String[] names) {
    	System.out.println("\nResults:\n");
        for (int i = 1; i < playerBets[0].length; i++) {
        	if(0 != playerBets[0][i]){  //only bother going in if they've bet anything in the first place so that people that don't bet dont get results
        	System.out.print(names[i] + " ");
            if (playerBets[1][i] == indexOfWinningHorse) {
            	System.out.print("won " + locale.format(playerBets[0][i]));
                wallets[i] += playerBets[0][i];
            } else {
            	System.out.print("lost " + locale.format(playerBets[0][i]));
                wallets[i] -= playerBets[0][i];
            }
            System.out.print("\n\n");
        }
        }
    }


    private static int[] parseIntArray(String[] strings) {
        int[] wallets = new int[strings.length];
        for (int i = 1; i < strings.length; i++) {
            wallets[i] = Integer.parseInt(strings[i]);
        }
        return wallets;
    }
    private static void betPrompt(String[] allPlayerData, NumberFormat locale, String[] raceHorsesNamed, int[][] playerBets, int[] wallets) {
        int i = 0;
       while (i >= 0) { //keeps going until they put -1 as the input, allows for everyone to bet if wanted and overwrite their bet
            i = pickPlayer(allPlayerData);
            if (i >= 0 && wallets[i] > 0) {  //since they can enter -1 to skip this part
                playerBets[0][i] = getBet(allPlayerData[i], wallets[i], locale);
            }
            if (i >= 0 && playerBets[0][i] > 0) {
                playerBets[1][i] = getBettedHorse(allPlayerData[i], raceHorsesNamed);
           }
        }
    }
    
    /*
     * takes array of player data, returns the picked player which corresponds to an element
     */
    private static int pickPlayer(String[] allPlayerData) {

        iterateAsTable(allPlayerData);
        boolean validinput = false;
        int pickedPlayer = 0;
        while (!validinput) {
            System.out.println("please enter the number corresponding to the player that would like to place a bet or -1 to move onto the race");
            try {
                Scanner keyboard = new Scanner(System.in); 
                pickedPlayer = keyboard.nextInt();
                if (pickedPlayer <= allPlayerData.length - 1 && pickedPlayer > 0 || pickedPlayer == -1) {
                    validinput = true;
  
                }
            } catch (InputMismatchException ex) {

            }
        }
        return pickedPlayer;

    }

    private static int getBettedHorse(String name, String[] raceHorsesNamed) {
        iterateAsTable(raceHorsesNamed);
        boolean validinput = false;
        int bettedHorse = 0;
        while (!validinput) {
            System.out.println(name + ", please enter the number corresponding to the horse you'd like to bet on (doesn't matter if you bet 0) and press enter.");
            try {
                Scanner keyboard = new Scanner(System.in); 
                bettedHorse = keyboard.nextInt();
                if (bettedHorse <= raceHorsesNamed.length - 1 && bettedHorse > 0) {
                    validinput = true;
                   
                }
            } catch (InputMismatchException ex) {

            }
        }
        return bettedHorse;
    }
    private static int getBet(String name, int wallet, NumberFormat locale) {
        boolean validinput = false;
        int n = 0;
        while (!validinput) {
            System.out.print(name + ", please enter a positive integer to place a bet (0 if you don't want to bet) and press enter. You have ");
            System.out.println(locale.format(wallet));
            try {
                Scanner keyboard = new Scanner(System.in); //i don't close this in the scope because when i do the code doesn't work
                n = keyboard.nextInt();
                if (n <= wallet && n >= 0) {
                    validinput = true;
                
                }
            } catch (InputMismatchException ex) {

            }
        }
        return n;
    }
    private static int handlingTie(int[] indicesOfWinningHorses, String[] namedWinners) {
        int winningHorseIndex = indicesOfWinningHorses[1];
        if (indicesOfWinningHorses.length > 2) {
            System.out.println("Winners:");
            iterateAsTable(namedWinners);
            winningHorseIndex = photoFinish(indicesOfWinningHorses, namedWinners);


        }
        return winningHorseIndex;
    }
    private static int photoFinish(int[] indicesOfWinningHorses, String[] namedWinners) {
        int pick = random(indicesOfWinningHorses.length - 1, 1);
        int winningHorseIndex = indicesOfWinningHorses[pick];
        outputPhotoFinish(pick, namedWinners);
        return winningHorseIndex;
    }
    private static void outputPhotoFinish(int pick, String[] namedWinners) {
        Scanner scanner = new Scanner(System.in);

        char divider = '|';
      

        System.out.println("Press enter to continue to the photo finish");
      scanner.nextLine();
        for (int i = 1; i < namedWinners.length; i++) {
            System.out.print("\n");

            outputTrack(divider, namedWinners[i], i, pick);



        }

        System.out.println();
    }
    private static void outputTrack(char divider, String string, int i, int w) {
        String line = "" + i;
        line += divider;
        if (i == w) {
            line += "--";
            line += divider;

        } else {
            line += "-";
            line += divider;
            line += "-";
        }
        System.out.print(line);
    }
    
    private static int displayHorses(String[] raceHorsesNamed, int[] parallelDistances) {
        Scanner scanner = new Scanner(System.in);
        int trackLength = 10;
        char divider = '|';
        char dash = '-';
        boolean simulate = false;
        boolean raceDone = false;
        while (!raceDone) {
            if (!simulate) {
                System.out.println("Press enter to do one turn or \"simulate\" for the whole race.");
                String blah = scanner.nextLine();
                if ("simulate".equalsIgnoreCase(blah)) {
                    simulate = true;
                }
            }
            for (int i = 1; i < raceHorsesNamed.length; i++) {
                System.out.print("\n");

                outputTrack(divider, raceHorsesNamed[i], i, parallelDistances);

                for (int j = 0; j < trackLength + getLongestString(allHorses).length()+1; j++) //outputs dashes accommodating for the cell containing name and the dash, followed by the actual track length
                    System.out.print(dash);

            }
            raceDone = checkDone(parallelDistances, trackLength);
            System.out.println();
        }
        System.out.println();
        return trackLength;
    }
    private static int[] winningHorse(int[] parallelDistances, int trackLength) {

        int counter = 0;
        int[] temp = new int[parallelDistances.length];
        for (int i = 1; i < parallelDistances.length; i++) {
            if (parallelDistances[i] >= trackLength) {
                counter++;
                temp[counter] = i;
            }
        }
        int[] winningHorses = new int[++counter];
        winningHorses = copyArray(winningHorses, temp);
        return winningHorses;
    }
    public static int[] copyArray(int[] destination, int[] source) {
        for (int i = 1; i < destination.length; i++) {
            destination[i] = source[i];

        }
        return destination;
    }
    private static boolean checkDone(int[] parallelDistances, int trackLength) {
        for (int num: parallelDistances) {
            if (num >= trackLength)
                return true;
        }
        return false;
    }
    private static int outputTrack(char divider, String string, int i, int[] parallelDistances) {
        String line = "";

        line += string;
        line += insertSpaces(line, getLongestString(allHorses).length()); //gets the longest horse name and formats that to cell length
        System.out.print(line);
        System.out.print(divider);

        parallelDistances[i] += increaseDistance();
        String spaces = insertSpaces("", parallelDistances[i]);
        System.out.print(spaces);

        System.out.print(i);
        System.out.println();
        return line.length();
    }
    private static int increaseDistance() {
        int i = random(2, 0);
        return i;
    }
    private static String insertSpaces(String line, int i) {
        int n = i - line.length();
        String str = "";
        for (int j = 0; j < n; j++) {
            str += " ";
        }
        return str;
    }



    private static void iterateAsTable(String[][] array, int[] wallets, NumberFormat locale) {
        char divider = '|';
        char dash = '-';
        int length = 0;
        for (int i = 0; i < array[1].length; i++) {
            System.out.print("\n");
            if (i == 0) {
                length = outputLine(divider, "Player Names/Wallet", i);
            } else outputLine(divider, array, i, locale, wallets);

            for (int j = 0; j < length; j++)
                System.out.print(dash);
        }
        System.out.println();
    }

    private static int outputLine(char divider, String[][] array, int i, NumberFormat locale, int[] wallets) {
        String line = "";
        if (i == 0) {
            line += '#';
        } else {
            line += i;
        }
        line += divider;
        for (int j = 0; j < array.length; j++) {
            if (j == 0) {
                line += array[j][i];
            } else {
                int wallet = (wallets[i]);
                line += "/" + locale.format(wallet);
            }
        }
        System.out.println(line);
        return line.length();
    }
/*
 * iterates array as #|Name followed by -------- on the next line
 *  -------- is the length of longest name used +2 to accommodate for #|
 */
    private static void iterateAsTable(String[] array) {
        char divider = '|';
        char dash = '-';

        for (int i = 0; i < array.length; i++) {
            System.out.print("\n");
            if (i == 0) {
                outputLine(divider, "Name", i);

            } else outputLine(divider, array[i], i);

            for (int j = 0; j < getLongestString(array).length() + 2; j++)  
                System.out.print(dash);
        }
        System.out.println();
    }

    public static String getLongestString(String[] array) // copied and pasted from http://alvinalexander.com/blog/post/java/method-return-longest-string-in-given-array-of-strings
        {
            int maxLength = 0;
            String longestString = null;
            for (int i = 1; i < array.length; i++) {
                String s = array[i]; {
                    if (s.length() > maxLength) {
                        maxLength = s.length();
                        longestString = s;
                    }
                }
            }
            return longestString;
        }

    private static int outputLine(char divider, String string, int i) {
        String line = "";
        if (i == 0) {
            line += '#';
        } else line += i;
        line += divider + string;
        System.out.println(line);
        return line.length();
    }

    private static String[] arrayHoldingIndex(int[] indexHolder, String[] nameHolder) {
        String[] named = new String[indexHolder.length];
        for (int i = 0; i < indexHolder.length; i++)
            named[i] = nameHolder[indexHolder[i]];
        return named;
    }
/* 
 * gets the player data (string in format "name + " " + wallet),  splits on " " into an array of length two, {names, wallet}
 * first element (element 0) is number of players, then names and wallets follow
 */
    private static String[][] getAllPlayers() {
        Scanner scanner = null;
        String[][] playerData = null;

        try {
            scanner = new Scanner(new File("input/playerData.dat"));
            int numberOfPlayers = Integer.valueOf(scanner.nextLine());
            int numberOfDataTypes = 2;
            playerData = new String[numberOfDataTypes][numberOfPlayers + 1];
            for (int i = 1; i <= numberOfPlayers; i++) {
                String[] temp = (scanner.nextLine().split(" ", numberOfDataTypes));
                int j = 0;
                for (String data: temp) {
                    playerData[j++][i] = data;
                }

            }
        } catch (FileNotFoundException e) {

        }
        return playerData;
    }

    private static int numberInRace(String[] horseFile) {
        int numberInRace = random(4, 5); //range of four, 5,6,7,8, minimum of 5.
        return numberInRace;
    }

    private static String[] getAllHorses() {
        Scanner scanner = null;
        String[] horseNames = null;
        try {
            scanner = new Scanner(new File("input/horseData.dat"));
            int numberOfHorses = Integer.valueOf(scanner.nextLine());
            horseNames = new String[numberOfHorses + 1];
            for (int i = 1; i <= numberOfHorses; i++) {
                horseNames[i] = scanner.nextLine();
            }
        } catch (FileNotFoundException e) {

        }
        return horseNames;
    }

    public static void iterateArray(int[] array, String[] allHorses) {
        //for (int i = 0; i < array.length; i++) {
        for (int i: array) {
            //System.out.println(allHorses[array[i]]);
            System.out.println(allHorses[i]);
        }
    }

    public static void iterateArray(String[][] array) {
        for (String[] i: array) {
            for (String data: i)
                System.out.print(data);
            System.out.println();
        }

    }

    public static int[] getRaceHorses(String[] allHorses, int numberInRace) {
        int[] raceHorses = new int[numberInRace + 1];

        for (int i = 1; i <= numberInRace; i++) {
            raceHorses[i] = getHorse(allHorses.length, raceHorses);
        }
        return raceHorses;
    }

    private static boolean search(int element, int[] array) {
        for (int i: array) {
            if (i == element)
                return true;
        }
        return false;
    }

    private static int getHorse(int numberOfHorses, int[] alreadyPicked) {
        boolean stillLooking = true;
        int pick = 0;
        while (stillLooking) {
            pick = random(numberOfHorses - 1, 1);
            stillLooking = search(pick, alreadyPicked);
        }
        return pick;
    }
    
    
    /*
     * range is the amount of possible numbers, lowerbound is the lowest possible number
     */
    public static int random(int range, int lowerbound) {
        int n = (int)(Math.random() * range + lowerbound);
        return n;
    }
    private static Locale getLocale() {



        Locale locale = null;
        int n = 0;
        boolean validinput = false;

        while (!validinput) {
            System.out.printf("Please enter the corresponding number for your country\n1. Canada\n2. USA\n3. France\n4. Germany\n5. China\n6. Italy\n");
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
    
}
