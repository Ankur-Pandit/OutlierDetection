/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fallids;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author ankurpandit
 */
public class Outlier {
    public static void main(String[] args) throws IOException {
        File file = new File("output.csv");
        ArrayList<Integer> intArray = new ArrayList<>();
        ArrayList<String> ipArray = new ArrayList<>();
        int i = 0;
        int sum = 0;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lineWords = line.split(",");
                intArray.add(Integer.parseInt(lineWords[9]));
                ipArray.add(lineWords[0]);
                sum += Integer.parseInt(lineWords[9]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        double average = sum / intArray.size();
        double sd = 0;
        for(int j=0;j<intArray.size();j++) {
            sd += ((intArray.get(j) - average)*(intArray.get(j) - average))/intArray.size();
        }
        double std = Math.sqrt(sd);
        double diff;
        for(int j=0;j<intArray.size();j++) {
            diff = Math.abs(intArray.get(j) - average);
            // Increase or decrease the multiplier of standard deviation as per confidence requirements
            if(diff > (3*std)) {
                System.out.println("Outlier detected from "+ipArray.get(j)+" with "+intArray.get(j)+" bytes requested.");
            }
        }
    }
}
