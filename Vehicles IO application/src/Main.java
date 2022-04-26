
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void sortCarsByPrice (ArrayList<Car> cars)
    {  boolean sorted;
        do { sorted = true;
            for(int i = 0; i<cars.size()-1; i++)
                if(cars.get(i).getPrice()>cars.get(i+1).getPrice())
                {   Car tmp = cars.get(i);
                    cars.set(i,cars.get(i+1));
                    cars.set(i+1,tmp);;
                    sorted = false;
                }
        }
        while(!sorted);

        System.out.println("\nCars sorted by price:");
        for (Car c:cars)
        { System.out.print(c);
        }
    }

    public static void sortChoppersByTopSpeed (ArrayList<Motorcycle> motorcycles)
    {
        ArrayList<Motorcycle> aux = new ArrayList<Motorcycle>();
        for (Motorcycle m:motorcycles)
        {  if (m.getShape().equals("Chopper"))
           {    aux.add(m);
           }
        }

        boolean sorted;
        do { sorted = true;
            for(int i = 0; i<aux.size()-1; i++)
                if(aux.get(i).getTopSpeed()>aux.get(i+1).getTopSpeed())
                {   Motorcycle tmp = aux.get(i);
                    aux.set(i,aux.get(i+1));
                    aux.set(i+1,tmp);;
                    sorted = false;
                }
        }
        while(!sorted);

        System.out.println("\nChoppers sorted by top speed:");
        for (Motorcycle m:aux)
        { System.out.print(m);
        }
    }


    //counting how many vehicles of each brand are there
    public static void countVehiclesByBrand (ArrayList<Vehicle> vehicles)
    {
        HashMap<String,Integer> brand_cnt = new HashMap<String, Integer>();

        for (Vehicle v:vehicles)
        { if (brand_cnt.containsKey(v.getBrand()))
          {   brand_cnt.put(v.getBrand(), brand_cnt.get(v.getBrand()) + 1);
          }
          else { brand_cnt.put(v.getBrand(), 1);
               }
        }

        System.out.println("\nNumber of vehicles for each brand:");
        for (String s:brand_cnt.keySet())
        {  System.out.print(s+" -> "+brand_cnt.get(s));
           if (brand_cnt.get(s)==1)
               System.out.println(" vehicle");
           else System.out.println(" vehicles");
        }
    }


    public static void main (String[] argv) {

        ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

        try {
            BufferedReader file_in = new BufferedReader(new InputStreamReader(new FileInputStream("vehicles.txt")));
            String line;

            while ((line = file_in.readLine()) != null) {
                String[] arg = line.split(" ");
                switch (arg.length) {
                    case 6:
                        Car car_tmp = new Car(arg[0], arg[1], Integer.parseInt(arg[2]), Integer.parseInt(arg[3]), arg[4], arg[5]);
                        vehicles.add(car_tmp);
                        break;
                    case 5:
                        Motorcycle moto_tmp = new Motorcycle(arg[0], arg[1], Integer.parseInt(arg[2]), Integer.parseInt(arg[3]), arg[4]);
                        vehicles.add(moto_tmp);
                        break;
                    case 4:
                        Tractor tractor_tmp = new Tractor(arg[0], arg[1], Integer.parseInt(arg[2]), Integer.parseInt(arg[3]));
                        vehicles.add(tractor_tmp);
                        break;
                }
            }

            file_in.close();

        } catch (IOException e) {
            System.out.println("Input-Output Error");
            System.exit(1);
        }

        ArrayList<Car> cars = new ArrayList<Car>();
        ArrayList<Motorcycle> motorcycles = new ArrayList<Motorcycle>();
        ArrayList<Tractor> tractors = new ArrayList<Tractor>();

        //counting the number of cars, motorcycles and tractors
        for (Vehicle v : vehicles)
        {   if (v.getClass().equals(Car.class))
            {   cars.add((Car)v);
            }
            else if (v.getClass().equals(Motorcycle.class))
                 {  motorcycles.add((Motorcycle) v);
                 }
                 else { tractors.add((Tractor) v);
                      }
        }

        System.out.println("Number of cars: " + cars.size());
        System.out.println(cars);
        System.out.println("Number of motorcycles: " + motorcycles.size());
        System.out.println(motorcycles);
        System.out.println("Number of tractors: " + tractors.size());
        System.out.println(tractors);


        countVehiclesByBrand(vehicles);

        sortCarsByPrice(cars);
        sortChoppersByTopSpeed(motorcycles);


        try { PrintStream file_out = new PrintStream(new FileOutputStream("Cars.txt"));
              file_out.println(cars);
              file_out = new PrintStream(new FileOutputStream("Motorcycles.txt"));
              file_out.println(motorcycles);
              file_out = new PrintStream(new FileOutputStream("Tractors.txt"));
              file_out.println(tractors);
        } catch (IOException e) {
            System.out.println("Input-Output Error");
            System.exit(1);
        }
    }
}
