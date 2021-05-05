/*
 * Earned Interest Ledger
 * Author: L. Cindy Doan
 * 05/01/2021
 * This application calculates the daily compounded interest earned from a checking or savings account balance.
 * Original source code found at https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
 * Code was modified to create an earnded interest ledger app
 */
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class GUI extends JFrame {

    Log f = new Log();
    //Initializes account objects
    Account checking = new Account(Double.valueOf(Log.checkingAmount));
    Account savings = new Account(Double.valueOf(Log.savingsAmount));
    NumberFormat formatter = new DecimalFormat("#0.00");
    DecimalFormat dc = new DecimalFormat("0.00");
    private int width = 530;
    private int height = 340;
    private int popupWidth = 200;
    private int popupHeight = 100;
    private JLabel myLabel = new JLabel(" Amount $ ");
    private JLabel myLabel2 = new JLabel(" Interest Rate in % ");
    private JLabel balanceLabel = new JLabel(" Balance: ");
    private JLabel dateLabel = new JLabel(" Date: ");
    private JTextField textField = new JTextField("");
    private JTextField textField2 = new JTextField("");
    private JTextField textBalance = new JTextField("");
    private JTextField textDate = new JTextField("");
    private JButton depositButton = new JButton("Deposit");
    private JButton withdrawButton = new JButton("Withdraw");
    //private JButton transferButton = new JButton("Transfer to");
    private JButton SaveRate = new JButton("SaveRate");
    private JButton SaveBalance = new JButton("SaveBalance");
    private JButton reset = new JButton("RESET ALL");
    private JRadioButton checkingButton = new JRadioButton("Checking");
    private JRadioButton savingsButton = new JRadioButton("Savings");
    private JFrame main = new JFrame("Interest Ledger");
    private JButton CalcInterest = new JButton("Calc Interest");
    private JButton AutoInterest = new JButton("Earned Interest");
    private JButton SaveCalc = new JButton("Save Calculation");
    private JOptionPane alerts = new JOptionPane();
    public static LocalDate pastDate = LocalDate.of(2021, 4, 28);
    public static LocalDate nowDate = LocalDate.now();

    public GUI() {

        //sets the size of the main window
        setFrame(main, width, height);
        //initializes border layout in main window
        main.setLayout(new BorderLayout());
        //initializes a panel for button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 10, 40));
        buttonPanel.setMaximumSize(new Dimension(300, 250));
        buttonPanel.add(AutoInterest);
        buttonPanel.add(SaveBalance);
        buttonPanel.add(SaveRate);
        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);
        buttonPanel.add(CalcInterest);
        buttonPanel.add(SaveCalc);
        buttonPanel.add(reset);
        buttonPanel.setBorder(new EmptyBorder(1, 1, 5, 5));
        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        optionPanel.setLayout(new GridLayout(2, 10, 5, 10));
        optionPanel.setMaximumSize(new Dimension(300, 300));
        //Grouping radio buttons to action events.    
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(checkingButton);
        buttonGroup.add(savingsButton);
        buttonGroup.setSelected(checkingButton.getModel(), true);

        //adds the radiobuttons to the option panel
        optionPanel.add(checkingButton);
        optionPanel.add(savingsButton);
        //adds option panel to the main window
        main.add(optionPanel, BorderLayout.NORTH);
        //creates a panel for the textfield and sets the size
        JPanel textPanel = new JPanel();
        main.add(textPanel, BorderLayout.WEST);
        textPanel.setLayout(new GridLayout(5, 1, 5, 10));
        textPanel.setMaximumSize(new Dimension(100, 100));
        textPanel.add(myLabel);
        textPanel.add(textField);
        textPanel.add(myLabel2);
        textPanel.add(textField2);
        textPanel.add(balanceLabel);
        textPanel.add(textBalance);
        textPanel.add(dateLabel);
        textPanel.add(textDate);
        main.add(buttonPanel, BorderLayout.EAST);

        //Estimates the daily compounded interest earned from the checking balance
        AutoInterest.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent b) {
                try {
                    if (checkingButton.isSelected()) {
                        f.openAutoChecking();
                        f.ReadAutoChecking();
                        f.openAutoCheckingRate();
                        f.ReadAutoCheckingRate();
                        f.openDateC();
                        f.ReadDateC();
                        f.openAutoEarnChecking();
                        f.ReadAutoEarnChecking();
                        textField.setText(Log.lastCheckingEI);
                        textField2.setText(Log.lastCheckingRate);
                        textBalance.setText(Log.lastChecking);
                        textDate.setText(Log.lastDateC);
                        double rate = Double.parseDouble(textField2.getText());
                        double cbal = Double.parseDouble(textBalance.getText());
                        double cEI = Double.parseDouble(textField.getText());
                        checking.updateBalance(cbal);
                        checking.updateRate(rate);
                        checking.updateInterest(cEI);
                        String formatEarnings1 = dc.format(cEI);
                        textField.setText(formatEarnings1);

                    } else if (savingsButton.isSelected()) {
                        f.openAutoSavings();
                        f.ReadAutoSavings();
                        f.openAutoSavingsRate();
                        f.ReadAutoSavingsRate();
                        f.openDateS();
                        f.ReadDateS();
                        f.openAutoEarnSavings();
                        f.ReadAutoEarnSavings();
                        textField.setText(Log.lastSavingsEI);
                        textField2.setText(Log.lastSavingsRate);
                        textBalance.setText(Log.lastSavings);
                        textDate.setText(Log.lastDateC);
                        double rate = Double.parseDouble(textField2.getText());
                        double sbal = Double.parseDouble(textBalance.getText());
                        double sEI = Double.parseDouble(textField.getText());
                        checking.updateBalance(sbal);
                        checking.updateRate(rate);
                        checking.updateInterest(sEI);
                        String formatEarnings2 = dc.format(sEI);
                        textField.setText(formatEarnings2);

                    } //if neither are selected the user is prompted to select an account
                    else {
                        setPane(popupWidth, popupHeight, "Exception", "Please select an account");
                        //  }
                    }
                } //tells the user to enter a value into the text field
                catch (NumberFormatException e) {
                    setPane(popupWidth, popupHeight, "Exception", "Please enter a number.");
                }
            }
        });

        //setting actions to buttons
        SaveBalance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                try {
                    //if checking is selected the deposit is made to checking and message for success
                    if (checkingButton.isSelected()) {
                        //gets the user input from the textfield
                        double cbal = Double.parseDouble(textBalance.getText());
                        checking.updateBalance(cbal);
                        String formatcBal = dc.format(cbal);
                        Log.saveData("AutoChecking.txt", formatcBal);
                        textBalance.setText(formatcBal);

                    } //if savings is selected the deposit is made to savings and inidcates successful
                    else if (savingsButton.isSelected()) {
                        //gets the user input from the textfield
                        double sbal = Double.parseDouble(textBalance.getText());
                        savings.updateBalance(sbal);
                        String formatsBal = dc.format(sbal);
                        Log.saveData("AutoSavings.txt", formatsBal);
                        textBalance.setText(formatsBal);

                    } //if neither are selected the user is prompted to select an account
                    else {
                        setPane(popupWidth, popupHeight, "Exception", "Please select an account");
                    }
                } //tells the user they must enter a number
                catch (NumberFormatException b) {
                    setPane(popupWidth, popupHeight, "Exception", "Please enter a number.");
                }
            }
        });

        SaveRate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                try {
                    //if checking is selected the deposit is made to checking and message for success
                    if (checkingButton.isSelected()) {
                        //gets the user input from the textfield
                        double interestRate = Double.parseDouble(textField2.getText());
                        checking.updateRate(interestRate);
                        double nRate = checking.getRate();
                        String formatRate = dc.format(nRate);
                        Log.saveData("AutoCheckingRate.txt", formatRate);                       
                        textField2.setText(formatRate);
                    } //if savings is selected the deposit is made to savings and inidcates successful
                    else if (savingsButton.isSelected()) {
                        //gets the user input from the textfield
                        double interestRate = Double.parseDouble(textField2.getText());
                        savings.updateRate(interestRate);
                        double nRate = savings.getRate();
                        String formatRate = dc.format(nRate);
                        Log.saveData("AutoSavingsRate.txt", formatRate);                        
                        textField2.setText(formatRate);

                    } //if neither are selected the user is prompted to select an account
                    else {
                        setPane(popupWidth, popupHeight, "Exception", "Please select an account");
                    }
                } //tells the user they must enter a number
                catch (NumberFormatException b) {
                    setPane(popupWidth, popupHeight, "Exception", "Please enter a number.");
                }
            }
        });

        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                try {
                    //check for numbers
                    if (textField.getText().matches("[0-9]+") == false) {
                        setPane(popupWidth, popupHeight, "Exception", "Please enter a number");
                    } else {
                        //gets the user input from the textfield
                        double amount = Double.parseDouble(textField.getText());
                        //if checking is selected the deposit is made to checking and message for success
                        if (checkingButton.isSelected()) {
                            checking.deposit(amount);
                            double nBal = checking.getBalance();
                            String formatBal = dc.format(nBal);
                            textBalance.setText(formatBal);
                            Log.saveData("AutoChecking.txt", formatBal);
                            
                            
                        } //if savings is selected the deposit is made to savings and inidcates successful
                        else if (savingsButton.isSelected()) {
                            savings.deposit(amount);
                            double nBal2 = savings.getBalance();
                            String formatBal2 = dc.format(nBal2);
                            textBalance.setText(formatBal2);
                            Log.saveData("AutoSavings.txt", formatBal2);
                        } //if neither are selected the user is prompted to select an account
                        else {
                            setPane(popupWidth, popupHeight, "Exception", "Please select an account");
                        }
                    }
                } //tells the user they must enter a number
                catch (NumberFormatException b) {
                    setPane(popupWidth, popupHeight, "Exception", "Please enter a number.");
                }
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent b) {
                try {
                    //check if number input
                    if (textField.getText().matches("[0-9]+") == false) {
                        setPane(popupWidth, popupHeight, "Exception", "Please enter a number.");
                    } else {
                        //gets the user input from the textfield
                        double amount = Double.parseDouble(textField.getText());
                        //check if the amount entered is a multiple of $20 
                        if (amount % 20 == 0) {
                            //withdraws money from checking if checking is selected or notifies the user of insufficient funds
                            if (checkingButton.isSelected()) {
                                try {
                                    checking.withdraw(amount);
                                    double nBal = checking.getBalance();
                                    String formatBal = dc.format(nBal);
                                    textBalance.setText(formatBal);
                                    Log.saveData("AutoChecking.txt", formatBal);
                                    //  checking.updateBalance(amountBal);

                                } catch (InsufficientFunds c) {
                                    setPane(popupWidth, popupHeight, "Exception", "Insufficient funds");
                                }
                            } //withdraws money from savings
                            else if (savingsButton.isSelected()) {
                                try {
                                    savings.withdraw(amount);
                                    double nBal = savings.getBalance();
                                    String formatBal2 = dc.format(nBal);
                                    textBalance.setText(formatBal2);
                                    Log.saveData("AutoSavings.txt", formatBal2);

                                } catch (InsufficientFunds d) {
                                    setPane(popupWidth, popupHeight, "Exception", "Insufficient funds");
                                }
                            } //if neither are selected the user is prompted to select an account
                            else {
                                setPane(popupWidth, popupHeight, "Exception", "Please select an account");
                            }
                        } //input a value in increments of $20
                        else {
                            setPane(popupWidth, popupHeight, "Exception", "Please enter in increments of $20");
                        }
                    }
                } //user input reqiured
                catch (NumberFormatException e) {
                    setPane(popupWidth, popupHeight, "Exception", "Please enter a number");
                }
            }
        });

        //Estimates the daily compounded interest earned from the checking balance
        CalcInterest.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent b) {
                try {
                    if (checkingButton.isSelected()) {                        
                        f.openInterestC();
                        f.ReadInterestC();
                        f.open();
                        f.Read();
                        double rate = Double.parseDouble(textField2.getText());
                        double cbal = Double.parseDouble(textBalance.getText());
                        double earnings = cbal * ((rate / 100) / 365);
                        double totBal = cbal + earnings;
                        String formatBal = dc.format(totBal);
                        String formatEarnings = dc.format(earnings);
                        double dnum = Double.valueOf(formatBal);
                        double dRate = Double.valueOf(rate);
                        double dEarn = Double.valueOf(formatEarnings);
                        checking.updateBalance(dnum);
                        checking.updateRate(dRate);
                        checking.updateInterest(dEarn);                        
                        textBalance.setText(formatBal);
                        textField.setText(formatEarnings);                       
                    } else if (savingsButton.isSelected()) {                        
                        f.openInterestS();
                        f.ReadInterestS();
                        textField2.setText(Log.savingsRate);
                        double rate = Double.parseDouble(textField2.getText());
                        double cbal = Double.parseDouble(textBalance.getText());
                        double earnings = cbal * ((rate / 100) / 365);
                        double totBal = cbal + earnings;
                        String formatBal = dc.format(totBal);
                        String formatEarnings = dc.format(earnings);
                        double dnum = Double.valueOf(formatBal);
                        double dRate = Double.valueOf(rate);
                        double dEarn = Double.valueOf(formatEarnings);
                        savings.updateBalance(dnum);
                        savings.updateRate(dRate);
                        savings.updateInterest(dEarn);                       
                        textBalance.setText(formatBal);
                        textField.setText(formatEarnings);
                        } //if neither are selected the user is prompted to select an account
                    else {
                        setPane(popupWidth, popupHeight, "Exception", "Please select an account");
                        //  }
                    }
                } //tells the user to enter a value into the text field
                catch (NumberFormatException e) {
                    setPane(popupWidth, popupHeight, "Exception", "Please enter a number.");
                }
            }
        });

        SaveCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                try {
                    if (checkingButton.isSelected()) {
                        String cAccount = Double.toString(checking.getBalance());
                        String cRate = Double.toString(checking.getRate());
                        Log.saveData("checkingAccount.txt", cAccount);
                        Log.saveData("checkingRate.txt", cRate);
                        Log.saveData("checkingDate.txt", nowDate);
                    } //shows savings balance
                    else if (savingsButton.isSelected()) {
                        String sAccount = Double.toString(savings.getBalance());
                        String sRate = Double.toString(savings.getRate());
                        Log.saveData("savingsAccount.txt", sAccount);
                        Log.saveData("savingsRate.txt", sRate);
                        Log.saveData("savingsDate.txt", nowDate);
                    } //prompts the user to select an account if neither is selected
                    else {
                        setPane(popupWidth, popupHeight, "Exception", "Please select an account");
                    }
                } //tells the user they must enter a number
                catch (NumberFormatException b) {
                    setPane(popupWidth, popupHeight, "Exception", "Error saving data.");
                }
            }
        });

        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                try {
                    if (checkingButton.isSelected()) {
                        textField.setText(" ");
                        textField2.setText(" ");
                        textBalance.setText(" ");
                        textDate.setText(" ");
                        f.open();
                        f.Read();
                        f.openInterestC();
                        f.ReadInterestC();
                        f.openDate1();
                        f.ReadDate1();
                        textField2.setText(Log.checkingRate);
                        textBalance.setText(Log.checkingAmount);
                        textDate.setText(Log.checkingDate);
                        double rate = Double.parseDouble(textField2.getText());
                        double cbal = Double.parseDouble(textBalance.getText());
                        checking.updateBalance(cbal);
                        checking.updateRate(rate);
                        String formatBal = dc.format(cbal);
                        String formatRate = dc.format(rate);
                        textBalance.setText(formatBal);
                        textField2.setText(formatRate);
                    } else if (savingsButton.isSelected()) {
                        textField.setText(" ");
                        textField2.setText(" ");
                        textBalance.setText(" ");
                        textDate.setText(" ");
                        f.openSavings();
                        f.ReadSavings();
                        f.openInterestS();
                        f.ReadInterestS();
                        f.openDate2();
                        f.ReadDate2();
                        textField2.setText(Log.savingsRate);
                        textBalance.setText(Log.savingsAmount);
                        textDate.setText(Log.savingsDate);
                        double rate2 = Double.parseDouble(textField2.getText());
                        double cbal2 = Double.parseDouble(textBalance.getText());
                        checking.updateBalance(cbal2);
                        checking.updateRate(rate2);
                        String formatBal2 = dc.format(cbal2);
                        String formatRate2 = dc.format(rate2);
                        textBalance.setText(formatBal2);
                        textField2.setText(formatRate2);                        
                    } //if neither are selected the user is prompted to select an account
                    else {
                        setPane(popupWidth, popupHeight, "Exception", "Please select an account");
                        //  }
                    }
                } //tells the user to enter a value into the text field
                catch (NumberFormatException e) {
                    setPane(popupWidth, popupHeight, "Exception", "Please enter a number.");
                }
            }
        });
    }

    //set visibility for app
    public void display() {
        main.setVisible(true);
    }

    //sets the size of the specified frame and defines default behavior of the frame
    private void setFrame(JFrame frame, int width, int height) {
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //sets pop-up windows
    private void setPane(int width, int height, String title, String dialog) {
        alerts.setSize(width, height);
        alerts.showMessageDialog(main, dialog, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {

        Log f = new Log();
        File file1 = new File("AutoChecking.txt");
        File file2 = new File("AutoCheckingRate.txt");
        File file3 = new File("AutoDateC.txt");
        File file4 = new File("AutoSavings.txt");
        File file5 = new File("AutoSavingsRate.txt");
        File file6 = new File("AutoDateS.txt");
        if (file1.exists() && file2.exists() && file3.exists() && file4.exists() && file5.exists() && file6.exists()) {
            System.out.println("Exists");
        } else {
            System.out.println("Does not Exists");
            f.saveData("AutoChecking.txt", "1000.00");
            f.saveData("AutoCheckingRate.txt", "12.00");
            f.saveData("AutoDateC.txt", "2021-05-01");
            f.saveData("AutoEarnChecking.txt", " 0.00");

            f.saveData("AutoSavings.txt", "200000.00");
            f.saveData("AutoSavingsRate.txt", "15.00");
            f.saveData("AutoEarnSavings.txt", " 0.00");
            f.saveData("AutoDateS.txt", "2021-05-01");

            f.saveData("checkingAccount.txt", "3000.00");
            f.saveData("checkingRate.txt", "12.00");
            f.saveData("checkingDate.txt", "2021-05-01");

            f.saveData("savingsAccount.txt", "5000.00");
            f.saveData("savingsRate.txt", "15.00");
            f.saveData("savingsDate.txt", "2021-05-01");

        }
        // open and reads data
        f.open();
        f.Read();
        f.openSavings();
        f.ReadSavings();
        f.openInterestC();
        f.ReadInterestC();
        f.openInterestS();
        f.ReadInterestS();

        f.openDateC();
        f.ReadDateC();
        f.openDateS();
        f.ReadDateS();

        f.openDate1();
        f.ReadDate1();
        f.openDate2();
        f.ReadDate2();
        f.openAutoChecking();
        f.ReadAutoChecking();
        f.openAutoCheckingRate();
        f.ReadAutoCheckingRate();
        f.openAutoSavings();
        f.ReadAutoSavings();
        f.openAutoSavingsRate();
        f.ReadAutoSavingsRate();

        System.out.println(nowDate);
        

        LocalDate testC = LocalDate.parse(Log.lastDateC);
        LocalDate testS = LocalDate.parse(Log.lastDateS);
        long Cdays = ChronoUnit.DAYS.between(testC, nowDate);
        long Sdays = ChronoUnit.DAYS.between(testS, nowDate);
        System.out.printf("%s  %s  days between : " + Cdays, Log.lastDateC, nowDate);
        System.out.println(" ");
        System.out.printf("%s  %s  days between : " + Sdays, Log.lastDateC, nowDate);
        DecimalFormat dc = new DecimalFormat("0.00");
        double rate = Double.parseDouble(Log.lastCheckingRate);
        double cbal = Double.parseDouble(Log.lastChecking);
        double rate2 = Double.parseDouble(Log.lastSavingsRate);
        double cbal2 = Double.parseDouble(Log.lastSavings);

        double totEarnings = 0;
        double totEarnings2 = 0;

        for (int i = 0; i < Cdays; i++) {
            double earnings = cbal * ((rate / 100) / 365);
            cbal = cbal + earnings;
            totEarnings += earnings;
            Log.saveData("AutoEarnChecking.txt", earnings);
        }

        for (int i = 0; i < Sdays; i++) {
            double earnings2 = cbal2 * ((rate2 / 100) / 365);
            cbal2 = cbal2 + earnings2;
            totEarnings2 += earnings2;
            Log.saveData("AutoEarnSavings.txt", earnings2);
        }

        //initializes and displays the ATM
        GUI ledger = new GUI();
        ledger.display();

        String formatBal = dc.format(cbal);
        Log.saveData("AutoChecking.txt", formatBal);
        Log.saveData("AutoCheckingRate.txt", rate);
        Log.saveData("AutoEarnChecking.txt", totEarnings);
        Log.saveData("AutoDateC.txt", nowDate);

        String formatBal2 = dc.format(cbal2);
        Log.saveData("AutoSavings.txt", formatBal2);
        Log.saveData("AutoEarnSavings.txt", totEarnings2);
        Log.saveData("AutoSavingsRate.txt", rate2);
        Log.saveData("AutoDateS.txt", nowDate);

    }
}
