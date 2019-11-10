import java.util.Scanner;
import java.util.regex.*;

public class example1 {
    public static void main(String[] args) {
        while (true){
            System.out.print("Enter the temperature values (Example: 26C, 299K, or 79F) - ");

            String scales = "FfCcKk";  
            
            Scanner input = new Scanner(System.in);
            String inputTemperature = input.next();

            //Sorting the temperature scale and temperature from the entered data
            Pattern sortLeter = Pattern.compile("[A-z]");                  
            Pattern sortNumber = Pattern.compile("[-]?[0-9]+(.[0-9])?");  
            Matcher leters = sortLeter.matcher(inputTemperature);
            Matcher numbers = sortNumber.matcher(inputTemperature);
            leters.find();
            numbers.find();

            CharSequence temperatureScales = leters.group();
            Double temperature = Double.valueOf(numbers.group());

            if (scales.contains(temperatureScales));
            else {
                System.out.print("Incorrect temperature designation!");
                break;
            }

            String farenheit = "Ff";
            String celsium = "CcСс";
            String kelvin = "Kk";

            if (farenheit.contains(temperatureScales)) {
                System.out.println("{C:" + (((temperature - 32) / 2) + 0.1 * ((temperature - 32) / 2)) + ", K:"
                        + (((temperature - 32) / 2) + 0.1 * ((temperature - 32) / 2) + 273) + "}");
            } else if (celsium.contains(temperatureScales)) {
                System.out.println("{F:" + (temperature * (9.0 / 5.0) + 32) + ", K:" + (temperature + 273) + "}");
                //kelvin can't be less than zero
            } else if (kelvin.contains(temperatureScales) && temperature>0) {       
                System.out.println("{F:" + (temperature * (9.0 / 5.0) + 32 - 273) + ", C:" + (temperature - 273) + "}");
            }
            else {
                System.out.print("Incorrect temperature designation!");
                break;
            }

            System.out.println("Exit?y/n");
            Scanner ex = new Scanner(System.in);
            String exit = ex.next();

            if(exit.equals("n"));
             else{break;}
        }


    }
}