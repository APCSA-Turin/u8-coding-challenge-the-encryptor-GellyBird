package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;

public class Encryptor {
    
    public static int determineColumns(int messageLen, int rows){
        int columns = (int)Math.ceil((double)messageLen / rows);
        if (messageLen == 0) {
            columns = 1;
        }
        return columns;
    }
    
    public static String[][] generateEncryptArray(String message, int rows) {
        String[][] newArray = new String[rows][determineColumns(message.length(), rows)];
        
        int currentchar = 0;
        for (int i = 0; i < newArray.length; i ++) {
            for (int j = 0; j < newArray[0].length; j ++) {
                if (currentchar == message.length()) {
                    break;
                } else {
                    newArray[i][j] = Character.toString(message.charAt(currentchar));
                    currentchar ++;
                }
            }
        }

        for (int i = 0; i < newArray.length; i ++) {
        for (int j = newArray[0].length - 1; j > -1; j --) {
            if (newArray[i][j] == null) {
                newArray[i][j] = "=";
            } else {
                break;
            }
        }
        }
        return newArray;
    }

    public static String encryptMessage(String message, int rows){
        String[][] newArray = generateEncryptArray(message, rows);
        String encrypted = "";

        for (int i =  newArray[0].length - 1; i > -1; i --) {
            for (int j = 0; j < newArray.length; j ++) {
                encrypted += newArray[j][i];
            }
        }
        return encrypted;
    }

    public static String decryptMessage(String encryptedMessage, int rows) {
        int columns = determineColumns(encryptedMessage.length(), rows);
        
        String[][] newArray = new String[rows][columns];
        String decrypted = "";
        int currentidx = encryptedMessage.length() -1 ;
        
        for (int i = 0 ; i < newArray[0].length; i ++ ) {
            if (currentidx == -1) {
                break;
            }
            for (int j = newArray.length - 1; j >-1; j --) {
                if (currentidx == -1) {
                    break;
                } else {
                    newArray[j][i] = Character.toString(encryptedMessage.charAt(currentidx));
                    currentidx --;
                }
            }
        }

        for (int i = 0; i < newArray.length; i ++) {
            for (int j = 0; j < newArray[0].length; j ++) {
                if (newArray[i][j] != null && !newArray[i][j].equals("=")) {
                    decrypted += newArray[i][j];
                }
            }
        }
        return decrypted;
    }
}