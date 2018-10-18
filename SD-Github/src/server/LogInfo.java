/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fagun
 */
public class LogInfo {

    private static final String URL_LOG = "log.txt";

    public static void add(String message) {
        File file = new File(URL_LOG);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                System.out.println("LogInfo - Add - Exception: " + ex.getMessage());
            }
        }

        FileWriter fw;
        BufferedWriter bw;
        Date date = new Date();

        try {
            fw = new FileWriter(file, true);
            bw = new BufferedWriter(fw);

            bw.write("[" + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + "] " + message + "\n");
            bw.flush();
            bw.close();
        } catch (IOException ex) {
            System.out.println("LogInfo - Add - Exception: " + ex.getMessage());
        }
    }
}
