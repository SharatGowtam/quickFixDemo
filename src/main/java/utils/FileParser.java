package utils;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileParser {

    public static List<String> parseFile(String filePath){

        List<String> aList = new ArrayList<String>();

        try(BufferedReader reader = Files.newBufferedReader(Paths.get(filePath)))
        {
            aList =  reader.lines().collect(Collectors.toList());
        }
        catch (Exception e) {
            // TODO: handle exception
        }

        return aList;


    }
}
