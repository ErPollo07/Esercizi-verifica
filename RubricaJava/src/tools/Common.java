package tools;

import java.util.Scanner;

public class Common {
    static Scanner scanner = new Scanner(System.in);

    public static void clrScr() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void wait(int milliSecond) {
        try {
            Thread.sleep(milliSecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int printMenu(String[] opzioni) {
        int scelta = 0;
        boolean continueToInsert;

        System.out.println("=== " + opzioni[0] + " ===");

        for (int i = 1; i < opzioni.length; i++) {
            System.out.println("[" + i + "] - " + opzioni[i]);
        }

        do {
            continueToInsert = false;

            System.out.print("Insert your choose: ");

            try {
                scelta = Integer.parseInt(scanner.next());

                if (scelta < 1 || scelta > opzioni.length - 1) {
                    System.out.println("Valore errato. Riprova");
                    continueToInsert = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Il valore deve essere numerico");
                continueToInsert = true;
            }
        } while (continueToInsert);

        return scelta;
    }
}
