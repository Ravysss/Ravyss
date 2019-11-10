import java.util.Scanner;

public class example2 {
    public static void main(String[] args){


        System.out.print("Enter the number of plates - ");
        Scanner scanForNum = new Scanner(System.in);
        int sizeOfPlate = Integer.parseInt(scanForNum.next());

        //array for the number of steps
        int[] steps = new int[sizeOfPlate]; 

        int formulaForNumberOfSteps = 1;    
        steps[0] = formulaForNumberOfSteps;

        
        //array for plate's action, for example "slot_1 -> slot_3" it's mean "take the plate from slot 1 and transfer it to slot 3"
        String[] slot = {"slot_1 -> slot_3","slot_1 -> slot_2","slot_3 -> slot_2", 
                "slot_2 -> slot_3","slot_2 -> slot_1","slot_3 -> slot_1"};

        
        //cycle to get the interval of the plate and the number of steps to move all the plates
        for(int i = 1; i < sizeOfPlate; i++) {
            formulaForNumberOfSteps = formulaForNumberOfSteps * 2 + 1; 
            steps[i] = formulaForNumberOfSteps;                       
        }

        //queue of plate
        int[] queue = new int[steps[sizeOfPlate-1]];   
        //array to identify the action on the plate
        String[] slotForPlate = new String[steps[sizeOfPlate-1]];  

        
        int loopForEven = 1; 
        int loopForUneven = 1;


        //loop for first plate
        for(int firstPlate = 1; firstPlate < steps[sizeOfPlate-1]; firstPlate = firstPlate + steps[0] + 1){ 

            if(loopForEven == 1) {                             
                queue[firstPlate] = 1;
                slotForPlate[firstPlate] = slot[1];
                loopForEven++;
            }else if(loopForEven == 2){
                queue[firstPlate] = 1;
                slotForPlate[firstPlate] = slot[3];
                loopForEven++;
            }else if(loopForEven == 3){
                queue[firstPlate] = 1;
                slotForPlate[firstPlate] = slot[5];
                loopForEven = 1;
            }
        }

        //loop for other plates
        for(int i = 1; i < sizeOfPlate; i++) {   

            loopForEven = 1;                  
            loopForUneven = 1;

            for (int firstplate = steps[i - 1] + 1; firstplate < steps[sizeOfPlate - 1]; firstplate = firstplate + steps[i] + 1) {
            	//assignment of the place of the plate in the queue and actions with it, for even plates
                if((i+1) % 2==0){                                             
                    if (loopForEven == 1) {
                        queue[firstplate] = i+1;
                        slotForPlate[firstplate] = slot[0];
                        loopForEven++;
                    } else if (loopForEven == 2) {
                        queue[firstplate] = i+1;
                        slotForPlate[firstplate] = slot[2];
                        loopForEven++;
                    } else if (loopForEven == 3) {
                        queue[firstplate] = i+1;
                        slotForPlate[firstplate] = slot[4];
                        loopForEven = 1;
                    }
                //same for the uneven
                }else if((i+1)%2==1){                                          
                    if (loopForUneven == 1) {
                        queue[firstplate] = i+1;
                        slotForPlate[firstplate] = slot[1];
                        loopForUneven++;
                    } else if (loopForUneven == 2) {
                        queue[firstplate] = i+1;
                        slotForPlate[firstplate] = slot[3];
                        loopForUneven++;
                    } else if (loopForUneven == 3) {
                        queue[firstplate] = i+1;
                        slotForPlate[firstplate] = slot[5];
                        loopForUneven = 1;
                    }
                }
            }
        }

        //output the all action
        for(int printLoop = 1; printLoop < steps[sizeOfPlate-1];printLoop++){                               
            System.out.println("num" + printLoop + " #" + queue[printLoop] + " " + slotForPlate[printLoop]); 
        }
        
        //last action to move the tower
        if(sizeOfPlate%2==1){                                                                            
                System.out.print("num" + steps[sizeOfPlate - 1] + " #" + 1 + " " + slot[1]);
        }else if(sizeOfPlate % 2==0){
                System.out.print("num" + steps[sizeOfPlate - 1] + " #" + 1 + " " + slot[3]);
        }
    }
}
