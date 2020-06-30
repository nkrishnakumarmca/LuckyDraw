package com.select;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class CsvReader {
    private static final Random rand = new Random();;
    public static void main(String[] args) throws Exception {

        if (args.length != 2)
        {
            throw new Exception("Need to pass argumnet of size 2");
        }

        int groupSize = Integer.valueOf(args[0]);
        String csvFile = System.getProperty("user.dir")+ File.separator + args[1];
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        System.out.println();
        System.out.println("Configured group size :: " + groupSize);
        System.out.println("file path :: " + csvFile);
        Set<String> emailIds = new HashSet<>();
        try {
            br = new BufferedReader(new FileReader(new File(csvFile)));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] country = line.split(cvsSplitBy);
                getList(country,emailIds);
            }
            System.out.println("Size email ids read from CSV :: " + emailIds.size());
            System.out.println();
            System.out.println("=========>   Lucky team members   <==========");
            List<String> givenIds = new ArrayList<>();
            givenIds.addAll(emailIds);
            printRandomEmails(givenIds, groupSize);
        }
        catch(Exception e) {
            e.printStackTrace();
        }}

    private static void getList(String[] ids, Set<String> emailIds) {
        Arrays.stream(ids).forEach(e -> {
            emailIds.add(e);
        });
    }

    private static void printRandomEmails(List<String> givenList , int groupSize){

        int group =1;
        for (int i = 0; i < givenList.size(); i++) {

            System.out.println("Group " + group);
            System.out.println("--------------------------");
            for (int j = 0; j < groupSize; j++) {
                if(givenList.size() -1 < 0){
                    break;
                }
                int randomIndex = rand.nextInt(givenList.size());
                String randomElement = givenList.get(randomIndex);
                System.out.println(randomElement);
                givenList.remove(randomIndex);
            }

            group++;
            System.out.println("-------------------------");
            System.out.println();
        }

    }
}


