package org.example;

import org.example.actrors.Order;
import org.example.geometry.Point;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try{
            File input = new File("C:\\Users\\Tema\\IdeaProjects\\myFirstMAS\\src\\main\\java\\org\\example\\orders.csv");
            FileReader inputReader = new FileReader(input);
            BufferedReader reader = new BufferedReader(inputReader);

            try(FileWriter output = new FileWriter("C:\\Users\\Tema\\IdeaProjects\\myFirstMAS\\src\\main\\java\\org\\example\\out.txt", false);){
                String time;
                String orderTime;
                String[] inpStart;
                String[] inpEnd;
                String[] strs;
                String line = reader.readLine();
                while(line != null){
                    strs = line.split(", ");
                    time = strs[0];
                    inpStart = strs[1].split(" ");
                    inpEnd = strs[2].split(" ");
                    orderTime = strs[3];

                    Point start = new Point(Double.parseDouble(inpStart[0]), Double.parseDouble(inpStart[1]));
                    Point end = new Point(Double.parseDouble(inpEnd[0]),Double.parseDouble(inpEnd[0]));

                    Order order = new Order(start, end, Double.parseDouble(orderTime));

                    String outLine = "Time: " + time + " " + order.toString() + "\n";
                    output.write(outLine);
                    output.flush();

                    line = reader.readLine();
                }
            } catch (IOException ex){
                ex.printStackTrace();
            }



        } catch (FileNotFoundException ex){
            ex.printStackTrace();
        }
    }
}