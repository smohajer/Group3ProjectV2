/*
 * Interest Ledger
 * Authore: L. Cindy Doan and Greg Fury
 * 05/01/2021
 * This application calculates the daily compounded interest earned from a checking or savings account balance.
 * Original source code found at https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
 * Code was modified to create a interest earnded ledger app
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Scanner;

class Log {

    Scanner scan;
    static String checkingAmount, savingsAmount, checkingRate, savingsRate, 
            lastDateC, lastDateS, lastChecking, lastCheckingRate, lastSavings, lastSavingsRate, lastCheckingEI, lastSavingsEI,
            checkingDate, savingsDate;
    
    NumberFormat formatter = new DecimalFormat("#0.00");

    public void open() {
        try {
            scan = new Scanner(new File("checkingAccount.txt"));
            System.out.println("it is working");
        } catch (FileNotFoundException e) {
            System.out.println("it is not working");
        }
    }
    
    public void Read() {
        do {
            checkingAmount = scan.next();
        } while (scan.hasNext());
        System.out.println(checkingAmount);
        scan.close();
    }

    public void openSavings() {
        try {
            scan = new Scanner(new File("savingsAccount.txt"));
            System.out.println("it is working");
        } catch (FileNotFoundException e) {
            System.out.println("it is not working");
        }
    }
    
    public void ReadSavings() {
        do {
            savingsAmount = scan.next();
        } while (scan.hasNext());
        System.out.println(savingsAmount);
        scan.close();
    }

    public void openInterestC() {
        try {
            scan = new Scanner(new File("checkingRate.txt"));
            System.out.println("it is working");
        } catch (FileNotFoundException e) {
            System.out.println("it is not working");
        }
    }
    
    public void ReadInterestC() {
        do {
            checkingRate = scan.next();
        } while (scan.hasNext());
        System.out.println(checkingRate);
        scan.close();
    }

    public void openInterestS() {
        try {
            scan = new Scanner(new File("savingsRate.txt"));
            System.out.println("it is working");
        } catch (FileNotFoundException e) {
            System.out.println("it is not working");
        }
    }
    
    public void ReadInterestS() {
        do {
            savingsRate = scan.next();
        } while (scan.hasNext());
        System.out.println(savingsRate);
        scan.close();
    }

    public static void saveData(String filename, Object value) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;
        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        try {
            fw = new FileWriter(filename, true);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
            pw.println("DateTime: " + ts + " " + (value));
            System.out.println("Data Successfully appended into file");
            pw.flush();
        } // print error message if there is one
        catch (IOException io) {
            System.out.println("File IO Exception" + io.getMessage());
        } //close the file
        finally {
            try {
                pw.close();
                bw.close();
                fw.close();
            } //print error message if there is one
            catch (IOException io) {
                System.out.println("Issue closing the File." + io.getMessage());
            }
        }
    }
  
    public void openDateC() {
        try {
            scan = new Scanner(new File("AutoDateC.txt"));
            System.out.println("it is working");
        } catch (FileNotFoundException e) {
            System.out.println("it is not working");
        }
    }

    public void ReadDateC() {
        do {
            lastDateC = scan.next();
        } while (scan.hasNext());
        System.out.println(lastDateC);
        scan.close();
    }
    
    public void openDateS() {
        try {
            scan = new Scanner(new File("AutoDateS.txt"));
            System.out.println("it is working");
        } catch (FileNotFoundException e) {
            System.out.println("it is not working");
        }
    }

    public void ReadDateS() {
        do {
            lastDateS = scan.next();
        } while (scan.hasNext());
        System.out.println(lastDateS);
        scan.close();
    }

    public static void lastAccessed() {
        BufferedReader inputStream = null;
        try {
            File file = new File("AutoDate.txt");
            InputStreamReader streamReader
                    = new InputStreamReader(new FileInputStream(file));
            BufferedReader br = new BufferedReader(streamReader);
            String line = new String();
            while (br.ready()) {
                line = br.readLine();
            }
            System.out.println("\n=====================================");
            System.out.println("Your last login was : ");
            System.out.println(line);
        } catch (IOException io) {
            System.out.println("File IO exception" + io.getMessage());
        } finally {                  
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException io) {
                System.out.println("Issue closing the Files" + io.getMessage());
            }
        }
    }
    
    public void openAutoEarnChecking() {
        try {
            scan = new Scanner(new File("AutoEarnChecking.txt"));
            System.out.println("it is working");
        } catch (FileNotFoundException e) {
            System.out.println("it is not working");
        }
    }

    public void ReadAutoEarnChecking() {
        do {
            lastCheckingEI = scan.next();
        } while (scan.hasNext());
        System.out.println(lastCheckingEI);
        scan.close();
    }
    
    public void openAutoChecking() {
        try {
            scan = new Scanner(new File("AutoChecking.txt"));
            System.out.println("it is working");
        } catch (FileNotFoundException e) {
            System.out.println("it is not working");
        }
    }

    public void ReadAutoChecking() {
        do {
            lastChecking = scan.next();
        } while (scan.hasNext());
        System.out.println(lastChecking);
        scan.close();
    }
    
    public void openAutoCheckingRate() {
        try {
            scan = new Scanner(new File("AutoCheckingRate.txt"));
            System.out.println("it is working");
        } catch (FileNotFoundException e) {
            System.out.println("it is not working");
        }
    }

    public void ReadAutoCheckingRate() {
        do {
            lastCheckingRate = scan.next();
        } while (scan.hasNext());
        System.out.println(lastCheckingRate);
        scan.close();
    }
    
    public void openAutoSavings() {
        try {
            scan = new Scanner(new File("AutoSavings.txt"));
            System.out.println("it is working");
        } catch (FileNotFoundException e) {
            System.out.println("it is not working");
        }
    }

    public void ReadAutoSavings() {
        do {
            lastSavings = scan.next();
        } while (scan.hasNext());
        System.out.println(lastSavings);
        scan.close();
    }
    
    public void openAutoSavingsRate() {
        try {
            scan = new Scanner(new File("AutoSavingsRate.txt"));
            System.out.println("it is working");
        } catch (FileNotFoundException e) {
            System.out.println("it is not working");
        }
    }

    public void ReadAutoSavingsRate() {
        do {
            lastSavingsRate = scan.next();
        } while (scan.hasNext());
        System.out.println(lastSavingsRate);
        scan.close();
    }
    
    public void openAutoEarnSavings() {
        try {
            scan = new Scanner(new File("AutoEarnSavings.txt"));
            System.out.println("it is working");
        } catch (FileNotFoundException e) {
            System.out.println("it is not working");
        }
    }

    public void ReadAutoEarnSavings() {
        do {
            lastSavingsEI = scan.next();
        } while (scan.hasNext());
        System.out.println(lastSavingsEI);
        scan.close();
    }
    
    public void openDate1() {
        try {
            scan = new Scanner(new File("checkingDate.txt"));
            System.out.println("it is working");
        } catch (FileNotFoundException e) {
            System.out.println("it is not working");
        }
    }

    public void ReadDate1() {
        do {
            checkingDate = scan.next();
        } while (scan.hasNext());
        System.out.println(checkingDate);
        scan.close();
    }
    
    public void openDate2() {
        try {
            scan = new Scanner(new File("savingsDate.txt"));
            System.out.println("it is working");
        } catch (FileNotFoundException e) {
            System.out.println("it is not working");
        }
    }

    public void ReadDate2() {
        do {
            savingsDate = scan.next();
        } while (scan.hasNext());
        System.out.println(savingsDate);
        scan.close();
    }    
    
}
