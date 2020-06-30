package com.cvgenerator.utils;

import com.opencsv.exceptions.CsvValidationException;
import lombok.extern.slf4j.Slf4j;
import com.opencsv.CSVReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Load data from CSV file
 */

@Slf4j
public class PhoneNumberAreaCodeProvider {

    private final String path;

    public PhoneNumberAreaCodeProvider(String path) {
        this.path = path;
    }

    @PostConstruct
    public Map<String, String> loadAreaCode() {

        Resource resource = new ClassPathResource(path);
        Map<String, String> mapOfAreaCode = new LinkedHashMap<>();
       try{
           File file = resource.getFile();
           CSVReader csvReader = new CSVReader(new FileReader(file));

           String[] values;
           while ((values = csvReader.readNext()) != null){
               mapOfAreaCode.put(values[0], values[1]);
           }

       }catch (FileNotFoundException exception){
           log.error("File {} does not exist", path);
       }catch (IOException exception){
           log.error("Cannot read file: {}", path);
       } catch (CsvValidationException e) {
           log.error("Problem with CSV file: {}", path);
       }
       return mapOfAreaCode;
    }
}
