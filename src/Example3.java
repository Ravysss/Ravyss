import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class example3 {

    private static final String HIT_PRBABILITY = "hit probability of Knights is";
    private static final String PROCENT ="%";
    private static final String KNIGHT_2 ="\nKnight2 - ";
    private static final String FIRST_KNIGHT ="\nFirstKnight - ";
    private static final String CHOISE = "Choice your move!(step - s ;Attack - a ;)";
    public static boolean doStep = true;

    public static void main(String[] args){

        //choice difficult level
        System.out.println("Enter difficult level: easy - e/ normal - n/hard - h");
        Scanner inputLevel = new Scanner(System.in);
        String inputDifficult = inputLevel.next();

        //chance of Knight hitting
        double randomFor1 = 0;
        double randomFor2 = 0;

        //loop for get different chance in the start of round
        while(randomFor1 == randomFor2) {
            randomFor1 = 0.1 + Math.random() * 0.2;
            randomFor2 = 0.1 + Math.random() * 0.2;
        }
        systemOutResult(randomFor1, randomFor2);
        double chancePerStep1 = getChancePerStep(randomFor1);
        double chancePerStep2 = getChancePerStep(randomFor2);

        //who will make the first choice
        int queue = (int) (Math.random()*2)+1;

        if(queue==1) {
            System.out.println("The first move is granted to the FirstKnight");
        }else{
            System.out.println("The first move is granted to the Knight2");
            doActionKnight2(randomFor1,randomFor2,chancePerStep1,chancePerStep2, setDifficult(inputDifficult));
        }

        //loop for steps of Knights
        while(doStep){
            System.out.println(CHOISE);

            Scanner input = new Scanner(System.in);
            String inputMove = input.next();

            if(inputMove.equals("s")) {
                randomFor1 = randomFor1 + chancePerStep1;
                randomFor2 = randomFor2 + chancePerStep2;
                doActionKnight2(randomFor1,randomFor2,chancePerStep1,chancePerStep2, setDifficult(inputDifficult));

            }else if(inputMove.equals("a")){
                hitResult(randomFor1, randomFor2);
                doStep = false;
            }
        }
    }
    //Knight2's action
    private static void doActionKnight2(double randomFor1, double randomFor2, double chancePerStep1, double chancePerStep2, double difficult){
        try {
            System.out.print("Knight2 do");
            TimeUnit.SECONDS.sleep(1);
            System.out.print(".");
            TimeUnit.SECONDS.sleep(1);
            System.out.print(".");
            TimeUnit.SECONDS.sleep(1);
            System.out.print(".");
            TimeUnit.SECONDS.sleep(1);

            double choiceOfKnight2 = Math.random();
            if(choiceOfKnight2 < difficult) {
                System.out.println("Step!");
                randomFor1 += chancePerStep1;
                randomFor2 += chancePerStep2;
                systemOutResult(randomFor1, randomFor2);
            }else{
                System.out.print("Attack and ");
                hitResult(randomFor2, randomFor1);
                doStep = false;
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    //method for transformation the String difficult to Double difficult for chance in choice Knight2
    public static double setDifficult(String inputDifficult){
        double difficult = 0.5;
        if(inputDifficult == "e"){difficult = 0.9;}
        else if(inputDifficult == "n"){difficult = 0.7;}
        return difficult;
    }
    //every step will be increase the chance of hit
    private static double getChancePerStep(double randomForRandom){
        return (1.0-randomForRandom)/20.0;
    }
    //output the probability of hitting
    private static void systemOutResult(double randomForKnight1, double randomForKnight2){
        System.out.println(HIT_PRBABILITY + FIRST_KNIGHT + (int)(randomForKnight1*100) + PROCENT + KNIGHT_2 + (int)(randomForKnight2*100) + PROCENT);
    }

    //calculates hit success and output result
    private static void hitResult(double randomForKnight1, double randomForKnight2){
        double procKnight = Math.random();
        if(randomForKnight1 >= procKnight) {
            System.out.println("Win!");
            systemOutResult(randomForKnight1, randomForKnight2);
        } else {
            System.out.println("loose!");
            systemOutResult(randomForKnight1, randomForKnight2);
        }
    }
}

