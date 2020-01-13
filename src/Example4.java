import java.util.Arrays;
import java.util.Scanner;

public class Example4 {

    public static int numParents = 10;
    public static int qualityOfComb = 10000;
    public static int numOfCoin = 10;
    public static void main(String[] args){
        System.out.println("How many generations you want?");
        Scanner inner = new Scanner(System.in);
        int howManyGen = Integer.parseInt(inner.next());

        int numOfLoop = 0;
        int[][][] gen = new int[2][howManyGen][numOfCoin];
        for(int addGen = 0; addGen < gen[Math.abs((numOfLoop%2)-1)].length; addGen++){
            int[] generation = getNewGeneration(qualityOfComb,combination(numParents));
            gen[Math.abs((numOfLoop%2)-1)][addGen] = generation;
        }
        for(int numGen = 0; numGen < gen[Math.abs((numOfLoop%2)-1)].length; numGen++){
            for(int coin = 0; coin < gen[Math.abs((numOfLoop%2)-1)][numGen].length; coin++){
                System.out.print(gen[Math.abs((numOfLoop%2)-1)][numGen][coin] + "\t");
                if(coin == numOfCoin-1){
                    System.out.println();
                }
            }
        }
        boolean contin = true;
        while(contin) {
            System.out.println("Choose this and the best of this generation?y/n");
            Scanner innerQuestionQuality = new Scanner(System.in);
            String questionQuality = innerQuestionQuality.next();
            if (questionQuality.equals("y")) {
                int[] qualityOfGen = quality(combination(qualityOfComb), gen[Math.abs((numOfLoop%2)-1)]);
                for (int coin = 0; coin < numOfCoin; coin++) {
                    System.out.print(qualityOfGen[coin] + "\t");
                }
                break;
            }
            System.out.println("You want to crossover this generation?y/n");
            Scanner innerQuestion = new Scanner(System.in);
            String question = innerQuestion.next();
            if (question.equals("y")) {
                for (int addGen = 0; addGen < gen[(numOfLoop%2)].length; addGen++) {
                    int[] generation = getNewGeneration(qualityOfComb, gen[Math.abs((numOfLoop%2)-1)]);
                    gen[(numOfLoop%2)][addGen] = generation;
                }
                for (int numGen = 0; numGen < gen[(numOfLoop%2)].length; numGen++) {
                    for (int coin = 0; coin < gen[(numOfLoop%2)][numGen].length; coin++) {
                        System.out.print(gen[(numOfLoop%2)][numGen][coin] + "\t");
                        if (coin == numOfCoin - 1) {
                            System.out.println();
                        }
                    }
                }
            }else{
                contin = false;
            }numOfLoop++;
        }
    }
    private static int[] getNewGeneration(int qualityOfComb, int[][] yourGeneration){
        System.out.println("getting new generation...");
        int[] almostPerfectCombinations;
        int[][] arrayOfKids = new int[10][numOfCoin];

        for(int numberComb = 0; numberComb < arrayOfKids.length; numberComb++) {
            int[] firstParent = quality(combination(qualityOfComb), yourGeneration);
            int[] secondParent = quality(combination(qualityOfComb), yourGeneration);
            arrayOfKids[numberComb] = crossover(firstParent, secondParent);
        }
        int allKidsComb = (arrayOfKids.length/2)*(arrayOfKids.length-1);
        int[][] allCrossoverKids = new int[allKidsComb][numOfCoin];
        int[][] allMutableCrossoverKids = new int[allKidsComb][numOfCoin];
        int crossTheKidVertical = 1;

        int allCrossoverKidsNum = 0;
        for (int crossTheKidHorizontal = 0; crossTheKidHorizontal < arrayOfKids.length-1; crossTheKidHorizontal++) {
            crossTheKidVertical += crossTheKidHorizontal;
            for (; crossTheKidVertical < arrayOfKids.length; crossTheKidVertical++) {
                allCrossoverKids[allCrossoverKidsNum] =
                        crossover(arrayOfKids[crossTheKidHorizontal], arrayOfKids[crossTheKidVertical]);
                allMutableCrossoverKids[allCrossoverKidsNum] = mutations(combination(qualityOfComb),allCrossoverKids[allCrossoverKidsNum]);
                allCrossoverKidsNum++;
            }
        }
        almostPerfectCombinations = quality(combination(qualityOfComb),allMutableCrossoverKids);
        return almostPerfectCombinations;
    }
    private static int[] mutations(int[][] xRC, int[] BestOfDC){
        System.out.println("mutations");
        int[] mutantDriver;
        int sumOfBest = 0;

        for (int RC = 0; RC < xRC.length; RC++) {
            for (int numCoin = 0; numCoin < numOfCoin; numCoin++) {
                sumOfBest = sumOfBest + Math.abs(xRC[RC][numCoin] - BestOfDC[numCoin]);
            }
        }
        int sumOfTest;
        int[] testDC ;
        int rValue;
        int rNumValue;
        do {
            sumOfTest = 0;
            testDC = Arrays.copyOf(BestOfDC, BestOfDC.length);
            rValue = (int) (Math.random() * 10) + 1;
            rNumValue = (int) (Math.random() * 10);
            testDC[rNumValue] = rValue;

            for (int RC = 0; RC < xRC.length; RC++) {
                for (int numValue = 0; numValue < numOfCoin; numValue++) {
                    sumOfTest = sumOfTest + Math.abs(xRC[RC][numValue] - testDC[numValue]);
                }
            }
        }while(sumOfBest/(xRC.length/10) <= sumOfTest/(xRC.length/10));

        mutantDriver = testDC;
        return mutantDriver;
    }
    private static int[] quality(int[][] xRC,int[][] xDC){
        System.out.println("check the quality");
        int[] bestOfDC = new int[numOfCoin];
        int[] sum = new int[xDC.length];
        for(int DC = 0; DC < xDC.length; DC++){
            for(int RC = 0; RC < xRC.length; RC++){
                for(int numCoin = 0; numCoin < numOfCoin; numCoin++){
                    sum[DC] = sum[DC] + Math.abs(xRC[RC][numCoin] - xDC[DC][numCoin]);
                }
            }
        }
        int[] s2 = Arrays.copyOf(sum, sum.length);
        int m = min(s2);
        for(int k = 0; k < xDC.length; k++){
             if(sum[k] == m) {
                 bestOfDC = xDC[k];
             }
        }
        return bestOfDC;
    }
    private static int[] crossover(int[] firstQualityRoadCombinations, int[] secondQualityRoadCombinations){
        System.out.println("making crossover");
        int[][] crossoverCombinations = new int[18][numOfCoin];
        for(int i = 0; i<numOfCoin-1;i++){
            crossoverCombinations[i] = firstQualityRoadCombinations;
            for(int i1 = i+1; i1<numOfCoin;i1++){
                crossoverCombinations[i][i1] = secondQualityRoadCombinations[i1];
            }
        }
        for(int i = numOfCoin-1; i<(numOfCoin-1)*2;i++){
            crossoverCombinations[i] = secondQualityRoadCombinations;
            for(int i1 = i+1; i1<numOfCoin;i1++){
                crossoverCombinations[i][i1] = firstQualityRoadCombinations[i1];
            }
        }
        int[] bestCrossover = quality(combination(qualityOfComb),crossoverCombinations);
        return bestCrossover;
    }
    private static int[][] combination(int numCombinations){
        System.out.println("search combination of " + numCombinations + " values");
        int[][] combinations = new int[numCombinations][numOfCoin];
        for(int numComb = 0; numComb < numCombinations;){
            if(summ(combinations[numComb]) <= 55){
                for (int numValue = 0; numValue < numOfCoin; numValue++) {
                    combinations[numComb][numValue] = (int) (Math.random() * 10) + 1;
                }
            }else numComb++;
        }return combinations;
    }

    private static int summ(int[] x){
        int sum = 0;
        for(int i = 0; i < x.length; i++){
            sum = sum + x[i];
        }return sum;
    }
    private static int min(int[] x){
        int min;

        int[] x1 = x;
        Arrays.parallelSort(x1);
        min = x1[0];
        return min;
    }
}

