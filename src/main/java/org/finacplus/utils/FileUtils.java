package org.finacplus.utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class FileUtils {
    private static final Logger logger = LogManager.getLogger(FileUtils.class);

    public static void writeToFile(String fileName,String context){
        logger.info("Writing to file: {}", fileName);
       try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,true))){
           writer.write(context);
           writer.newLine();
           logger.debug("Successfully wrote: {}", context);
       } catch (Exception e) {
           logger.error("Failed to write to file: {}", fileName, e);
       }
   }
}
