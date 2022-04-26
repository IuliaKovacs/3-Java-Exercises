import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.print.attribute.standard.PDLOverrideSupported;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Main {



    public static void main (String[] argv){


        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.initializeVendingMachine();
        System.out.println(vendingMachine);

        String ans="Y";
        try {

            while (ans.equals("Y") || ans.equals("y"))
            {   System.out.println(vendingMachine.startUsingVendingMachine());
                System.out.println("Do you want to buy another product? Y/N");
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                ans = in.readLine();
            }
        } catch (IOException e) {
            System.out.println("Input-Output Error");
            System.exit(1);
        }

        System.out.println(vendingMachine);

    }
}
