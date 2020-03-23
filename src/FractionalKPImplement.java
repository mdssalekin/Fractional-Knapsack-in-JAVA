import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FractionalKPImplement {
    public static void initialize(){
        createFile();
        readFile();
    }

    private static void createFile(){
        try{
            File myFile = new File("data.txt");
            if(myFile.createNewFile()){
                if(myFile.length() == 0){
                    System.out.println("File created: " + myFile.getName());
                    writeInFile();
                } else {
                    System.out.println("File is not empty");
                }
            }
        } catch(IOException e){
            System.out.println("An error occurred!");
            e.printStackTrace();
        }
    }

    private static void writeInFile(){
        try {
            int numberOfJars = 1333;
            double capacityOfKnapsack = 23.4;
            FileWriter writer = new FileWriter("data.txt");
            writer.write(numberOfJars + " & " + capacityOfKnapsack + "\r\n");
            for(int i = 1; i <= numberOfJars; i++){
                long price = Math.round((Math.random()*(101) + 20));
                double size = (Math.random() * (51.3) + 1.3);
                writer.write("Jar"+i + " " + price + " " + String.format("%.1f", size) + "\r\n");
            }
            writer.flush();
            writer.close();
            System.out.println("File has been written successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static  void readFile(){
        ArrayList<Item> itemList = extractDataFromFile();
        Collections.sort(itemList, Comparator.comparingDouble(Item::getRatio).reversed());
        knapsackAlgorithm(itemList);
        //Print_Helper.printAll(itemList, itemList.size());
    }

    private static void knapsackAlgorithm(ArrayList<Item> items){
        double knapsackCapacity = Math.round((Math.random() * 41) + 40);
        System.out.println("Knapsack Size: " + knapsackCapacity);
        double totalWeight = 0, totalProfit = 0;
        double wt, value;
        for(int i = 0; i < items.size(); i++){
            if(items.get(i).getSize() + totalWeight <= knapsackCapacity){
                totalWeight += items.get(i).getSize();
                totalProfit += items.get(i).getValue();
                Print_Helper.print(items, i, totalWeight, totalProfit);
            } else {
                wt = (knapsackCapacity - totalWeight);
                value = wt * items.get(i).getRatio();

                totalWeight += wt;
                totalProfit += value;
                Print_Helper.print(items, i, totalWeight, totalProfit);
                break;
            }
        }
        System.out.println("Total Weight: " + String.format("%.1f", totalWeight));
        System.out.println("Total Profit: " + String.format("%.1f", totalProfit));

    }

    public static ArrayList<Item> extractDataFromFile(){
        ArrayList<Item> itemList = null;
        try{
            itemList = new ArrayList<Item>();
            BufferedReader reader = new BufferedReader(new FileReader("data.txt"));
            reader.readLine();
            String line;
            while((line = reader.readLine()) != null){
                String[] str = line.split(" ");
                Item item = new Item(str[0], Integer.parseInt(str[1]), Double.parseDouble(str[2]));
                itemList.add(item);
            }
            reader.close();
        } catch (IOException e){
            System.out.println(e);
        } finally {
            return itemList;
        }
    }


}

class Print_Helper{
    public static void printAll(ArrayList<Item> inputArray, int size){
        for(int index = 0; index < size; index++){
            System.out.print(inputArray.get(index));
        }
        System.out.println();
    }

    public static void print(ArrayList<Item> item, int i, double tWeight, double tProfit){
        System.out.println("Selected Item: " +item.get(i).getName()
                +"\tWeight: " + item.get(i).getSize()
                +"\tValue: " + item.get(i).getValue()
                + "\tTotal Weight: " + String.format("%.1f", tWeight)
                + "\tTotal Profit: " + String.format("%.1f", tProfit));
    }
}