package by.petrovlad;

import java.util.Random;
import java.util.Scanner;

public class Saimmod {
    private static final int N = 1000000;
    private static final String INITIAL_STATE = "2000";

    public static void main(String[] args) {
        int P2000 = 0, P1000 = 0, P2010 = 0,
                P1010 = 0, P2110 = 0, P1110 = 0,
                P1001 = 0, P2011 = 0, P1011 = 0,
                P2111 = 0, P1111 = 0, P2210 = 0,
                P1210 = 0, P2211 = 0, P1211 = 0;

        int firstChannel = 0, secondChannel = 0,
                queueLength = 0, requestLength = 0,
                processedCount = 0, generatedCount = 0,
                declinedCount = 0;

        String state = INITIAL_STATE;
        double pi1, pi2;
        //Scanner scanner = new Scanner(System.in);
        //System.out.print("Введите pi1: ");
        //pi1 = scanner.nextDouble();
        //System.out.print("Введите pi2: ");
        //pi2 = scanner.nextDouble();
        pi1 = 0.5d;
        pi2 = 0.7d;

        int processed1 = 0;

        int l1 = 0;
        int l2 = 0;

        Random rand = new Random();
        for (int i = 0; i < N; i++) {
            double currPi1 = rand.nextDouble();
            double currPi2 = rand.nextDouble();

            boolean isProcessed1 = isProcessed(currPi1, pi1);
            boolean isProcessed2 = isProcessed(currPi2, pi2);

            switch (state) {
                case "2000":
                    P2000++;
                    state = "1000";
                    break;

                case "1000":
                    P1000++;
                    state = "2010";
                    break;

                case "2010":
                    P2010++;
                    state = isProcessed1 ? "1001" : "1010";
                    if (isProcessed1) processed1++;
                    break;

                case "1010":
                    P1010++;
                    state = isProcessed1 ? "2011" : "2110";
                    if (isProcessed1) processed1++;
                    break;

                case "2110":
                    P2110++;
                    state = isProcessed1 ? "1011" : "1110";
                    if (isProcessed1) processed1++;
                    break;

                case "1110":
                    P1110++;
                    state = isProcessed1 ? "2111" : "2210";
                    if (isProcessed1) processed1++;
                    break;

                case "2210":
                    P2210++;
                    state = isProcessed1 ? "1111" : "1210";
                    if (isProcessed1) processed1++;
                    break;

                case "1210":
                    P1210++;
                    state = isProcessed1 ? "2211" : "2210";
                    if (!isProcessed1) {
                        declinedCount++;
                    }
                    if (isProcessed1) processed1++;
                    break;

                case "1001":
                    P1001++;
                    state = isProcessed2 ? "2010" : "2011";
                    if (isProcessed2) processedCount++;
                    break;

                case "2011":
                    P2011++;
                    if (isProcessed1 && isProcessed2) {
                        state = "1001";
                        processedCount++;
                    }
                    if (!isProcessed1 && isProcessed2) {
                        state = "1010";
                        processedCount++;
                    }
                    if (isProcessed1 && !isProcessed2) {
                        state = "1001";
                        declinedCount++;
                    }
                    if (!isProcessed1 && !isProcessed2) {
                        state = "1011";
                    }
                    if (isProcessed1) processed1++;
                    break;

                case "1011":
                    P1011++;
                    if (isProcessed1 && isProcessed2) {
                        state = "2011";
                        processedCount++;
                    }
                    if (!isProcessed1 && isProcessed2) {
                        state = "2110";
                        processedCount++;
                    }
                    if (isProcessed1 && !isProcessed2) {
                        state = "2011";
                        declinedCount++;
                    }
                    if (!isProcessed1 && !isProcessed2) {
                        state = "2111";
                    }
                    if (isProcessed1) processed1++;
                    break;

                case "2111":
                    P2111++;
                    if (isProcessed1 && isProcessed2) {
                        state = "1011";
                        processedCount++;
                    }
                    if (!isProcessed1 && isProcessed2) {
                        state = "1110";
                        processedCount++;
                    }
                    if (isProcessed1 && !isProcessed2) {
                        state = "1011";
                        declinedCount++;
                    }
                    if (!isProcessed1 && !isProcessed2) {
                        state = "1111";
                    }
                    if (isProcessed1) processed1++;
                    break;

                case "1111":
                    P1111++;
                    if (isProcessed1 && isProcessed2) {
                        state = "2111";
                        processedCount++;
                    }
                    if (!isProcessed1 && isProcessed2) {
                        state = "2210";
                        processedCount++;
                    }
                    if (isProcessed1 && !isProcessed2) {
                        state = "2111";
                        declinedCount++;
                    }
                    if (!isProcessed1 && !isProcessed2) {
                        state = "2211";
                    }
                    if (isProcessed1) processed1++;
                    break;

                case "2211":
                    P2211++;
                    if (isProcessed1 && isProcessed2) {
                        state = "1111";
                        processedCount++;
                    }
                    if (!isProcessed1 && isProcessed2) {
                        state = "1210";
                        processedCount++;
                    }
                    if (isProcessed1 && !isProcessed2) {
                        state = "1111";
                        declinedCount++;
                    }
                    if (!isProcessed1 && !isProcessed2) {
                        state = "1211";
                    }
                    if (isProcessed1) processed1++;
                    break;

                case "1211":
                    P1211++;
                    if (!isProcessed1 && isProcessed2) {
                        state = "2210";
                        processedCount++;
                    } else {
                        state = "2211";
                        declinedCount++;
                        if (isProcessed2)  {
                            processedCount++;
                        }
                    }
                    if (isProcessed1) processed1++;
                    break;
            }
            if (state.charAt(0) == '1') generatedCount++;

            int J = Integer.parseInt(String.valueOf(state.charAt(1)));
            int T1 = Integer.parseInt(String.valueOf(state.charAt(2)));
            int T2 = Integer.parseInt(String.valueOf(state.charAt(3)));
            queueLength += J;
            firstChannel += T1;
            secondChannel += T2;
            requestLength += J + T1 + T2;

            l1 += J + T1;
            l2 += T2;
        }

        System.out.println("P2010 = " + (double)P2010 / N);
        System.out.println("P1001 = " + (double)P1001 / N);
        System.out.println("P1111 = " + (double)P1111 / N);
        System.out.println("P2210 = " + (double)P2210 / N);
        System.out.println("P2011 = " + (double)P2011 / N);
        System.out.println("P1010 = " + (double)P1010 / N);
        System.out.println("P2211 = " + (double)P2211 / N);
        System.out.println("P1210 = " + (double)P1210 / N);
        System.out.println("P1011 = " + (double)P1011 / N);
        System.out.println("P2110 = " + (double)P2110 / N);
        System.out.println("P2111 = " + (double)P2111 / N);
        System.out.println("P1110 = " + (double)P1110 / N);
        System.out.println("P1211 = " + (double)P1211 / N);
        System.out.println("Сумма = " + (double)(P2000 + P1000 + P2010 + P1001 + P1111 + P2210 + P2011 + P1010
                + P2211 + P1210 + P1011 + P2110 + P1111 + P1110 + P1211) / N);

        System.out.println("A = " + (double)processedCount / N);
        System.out.println("Lоч = " + (double)queueLength / N);
        System.out.println("Lс = " + (double)requestLength / N);
        System.out.println("Wqueue = " + (double)queueLength / processed1);
        //System.out.println("Wоч = " + (double)queueLength / ((1 - pi1) * (firstChannel)));
        //System.out.println("Wsys = " + ((double)queueLength / ((1 - pi1) * (firstChannel)) + 1 / (1 - pi1) + 1 / (1 - pi2)));
        //System.out.println("Wc = " + ((double)l1 / processedCount + l2 / processedCount));
        System.out.println("Wc = " + ((double)l1 / processed1 + (double)l2 / processedCount));
        System.out.println("Q = " + (double)processedCount / generatedCount);
        System.out.println("Pотк = " + (double)declinedCount / generatedCount);
        System.out.println("K1 = " + (double)firstChannel / N);
        System.out.println("K2 = " + (double)secondChannel / N);
    }

    private static boolean isProcessed(double currentPi, double pi) {
        return currentPi > pi;
    }
}
