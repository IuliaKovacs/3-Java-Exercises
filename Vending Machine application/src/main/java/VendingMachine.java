import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class VendingMachine {
    private String phase; //Info about the phase in which the machine is in ex: Ready to use, Product selected etc.
    private HashMap<Coin,Integer> coins = new HashMap<Coin, Integer>(); //The coins that the machine has accumulated, the coins used for remaining change
    private HashMap<Product,Integer> products = new HashMap<Product, Integer>(); //The available products
    private List<Product> productsList;
    private List<Coin> coinsList;
    private List returnedObjects;

    public VendingMachine() {
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public HashMap<Coin, Integer> getCoins() {
        return coins;
    }

    public void setCoins(HashMap<Coin, Integer> coins) {
        this.coins = coins;
    }

    public HashMap<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(HashMap<Product, Integer> products) {
        this.products = products;
    }

    public List<Product> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Product> productsList) {
        this.productsList = productsList;
    }

    public List<Coin> getCoinsList() {
        return coinsList;
    }

    public void setCoinsList(List<Coin> coinsList) {
        this.coinsList = coinsList;
    }

    public List getReturnedObjects() {
        return returnedObjects;
    }

    public void setReturnedObjects(List returnedObjects) {
        this.returnedObjects = returnedObjects;
    }

    @Override
    public String toString() {
        return "--- Vending Machine State ---\n" + "Phase: " + phase +  "\nCoins stored: " + coins +"\nProducts: "+products;
    }

    private void initializeProductData () {
        ObjectMapper mapper = new ObjectMapper();

        try {
            productsList = mapper.readValue(new File("products.json"), new TypeReference<List<Product>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void  initializeCoinData () {
        ObjectMapper mapper = new ObjectMapper();

        try {
            coinsList = mapper.readValue(new File("coins.json"), new TypeReference<List<Coin>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void displayProductsMenu ()
    {   System.out.println("\n---- Products Menu ----");
        for (int i=0; i<productsList.size(); i++)
        {   System.out.print((1+i));  System.out.println(". "+productsList.get(i));
        }
    }


    public void displayAcceptedCoins ()
    {   System.out.println("\n---- Accepted Coins ----");
        for (Coin c:coinsList)
        {   System.out.println(c);
        }
    }

    public void initializeVendingMachine ()
    {   initializeProductData();
        initializeCoinData();
        for (Coin c: coinsList)
        { coins.put(c,10); //At the beginning the machine will have 10 coins for each value to offer exchanges if necessary
        }
        for (Product p:productsList)
        { products.put(p,15); //At the beginning, the machine will have 15 pieces of each product (for example)
        }
        phase="Ready to use";
    }

    private void selectProduct () throws NonExistingProductSelected, NoProductsLeft
    {
        try {
            System.out.println("Please enter the number of the product you want to buy");
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            int number = Integer.parseInt(in.readLine());
            if (!(number >= 1 && number <= products.size()))
            {   throw new NonExistingProductSelected("Invalid number selected! Please enter another number!");
            }
            number--;
            if ((products.get(productsList.get(number)))==0)
            {   throw new NonExistingProductSelected("There are no more pieces of the chosen product! If you want, you can buy another product!");
            }
            System.out.println("You selected: "+productsList.get(number));
            phase="Product Selected - Waiting for pay";
            insertCoins(productsList.get(number));
        } catch (IOException e) {
            System.out.println("Input-Output Error");
            System.exit(1);
        }

    }

    public List startUsingVendingMachine ()
    {   displayProductsMenu();
        displayAcceptedCoins();
        returnedObjects = new ArrayList<>();

        do {
            try {
                selectProduct();
                break;
            } catch (NonExistingProductSelected e) {
                System.out.println(e.getMessage());
            } catch (NoProductsLeft e)
                {  System.out.println(e.getMessage());
                   break;
                }
        }
        while (true);

        this.saveVendingMachineState();
        phase = "Ready to use";
        return returnedObjects;
    }

    private void insertCoins (Product product)
    {  displayAcceptedCoins();
        try {
            System.out.println("Please enter the coins one by one");
            double sum = 0;
            List<Coin> tmp = new ArrayList<Coin>();
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            while (sum<product.getPrice())
            {   Coin coin = new Coin(Double.parseDouble(in.readLine()));
                tmp.add(coin);
                sum+=coin.getValue();
            }

            phase = "Paid for the product";
            System.out.println("Are you sure you want to continue? Y/N");
            String ans = in.readLine();
            if (ans.equals("N") || ans.equals("n"))
            {   for (Coin c: tmp)
                {   returnedObjects.add(c);
                }
            }
            else {  updateMachine(tmp, product, sum);
                    returnedObjects.add(product);
                 }

        } catch (IOException e) {
            System.out.println("Input-Output Error");
            System.exit(1);
        }
    }

    private void updateMachine(List<Coin> tmp, Product product, double sum)
    {   for (Coin c:tmp)
        { coins.put(c,coins.get(c)+1);
        }
        products.put(product,products.get(product)-1);

        //preparing the change
        if (product.getPrice()<sum)
        {   double aux=sum-product.getPrice();
            boolean hasChange=true;
            int i=coinsList.size()-1;
            do {
                   Coin c=coinsList.get(i);
                   if (c.getValue()<=aux)
                   { aux-=c.getValue();
                     returnedObjects.add(c);
                     coins.put(c,coins.get(c)-1);
                   }
                   else { i--;
                        }
                   if (i==-1)
                      hasChange=false;
            } while (aux!=0 && hasChange);
        }

        phase="The product is ready";
    }

    private void saveVendingMachineState () {
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(new File("stateOfVendingMachine.json"), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
