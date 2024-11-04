import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Loan System");
        // Declare gui labels variables
        JLabel creditScoreL, loanAmountL, existingDebtL, loanTypeL, employmentStatusL, monthlyIncomeL,
                repaymentPeriodL;
        // Declare text fields
        JTextField creditScoreTF, loanAmountTF, existingDebtTF, loanTypeTF, employmentStatusTF, monthlyIncomeTF,
                repaymentPeriodTF;
        JButton checkEligability = new JButton("Check Eligibility");

        // Initialize components variables
        creditScoreL = new JLabel("Credit Score: ");
        loanAmountL = new JLabel("Loan Amount: ");
        existingDebtL = new JLabel("Existing Debt: ");
        loanTypeL = new JLabel("Loan Type [mortgage - personal]: ");
        employmentStatusL = new JLabel("Employment Status [employed - unemployed]: ");
        monthlyIncomeL = new JLabel("Monthly Income: ");
        repaymentPeriodL = new JLabel("Repayment Period in years: ");
        creditScoreTF = new JTextField();
        loanAmountTF = new JTextField();
        existingDebtTF = new JTextField();
        loanTypeTF = new JTextField();
        employmentStatusTF = new JTextField();
        monthlyIncomeTF = new JTextField();
        repaymentPeriodTF = new JTextField();

        // Set the main frame layout to BorderLayout
        frame.setLayout(new BorderLayout());

        // Create a form panel with GridLayout for labels and text fields
        JPanel formPanel = new JPanel(new GridLayout(10, 10, 10, 5)); // 6 rows, 2 columns, with spacing

        // Add labels and text fields to the form panel
        formPanel.add(creditScoreL);
        formPanel.add(creditScoreTF);
        formPanel.add(loanAmountL);
        formPanel.add(loanAmountTF);
        formPanel.add(existingDebtL);
        formPanel.add(existingDebtTF);
        formPanel.add(loanTypeL);
        formPanel.add(loanTypeTF);
        formPanel.add(employmentStatusL);
        formPanel.add(employmentStatusTF);
        formPanel.add(monthlyIncomeL);
        formPanel.add(monthlyIncomeTF);
        formPanel.add(repaymentPeriodL);
        formPanel.add(repaymentPeriodTF);

        // Add the form panel to the center of the frame
        frame.add(formPanel, BorderLayout.CENTER);

        // Create a panel for the button and add the button to it
        JPanel buttonPanel = new JPanel(); // Default FlowLayout centers the button
        buttonPanel.add(checkEligability);

        // Add the button panel to the bottom (SOUTH) of the frame
        frame.add(buttonPanel, BorderLayout.SOUTH);

        checkEligability.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double totalLoanPayment, interestRate, deptToIncome;
                double maxDTI = 0.4;
                int income, creditScore, repaymentPeriod, loanAmount, existingDebt;
                String loanType, employment, decision;

                // Store values entered to text fields into variables
                creditScore = Integer.parseInt(creditScoreTF.getText());
                loanAmount = Integer.parseInt(loanAmountTF.getText());
                existingDebt = Integer.parseInt(existingDebtTF.getText());
                loanType = loanTypeTF.getText();
                employment = employmentStatusTF.getText();
                income = Integer.parseInt(monthlyIncomeTF.getText());
                repaymentPeriod = Integer.parseInt(repaymentPeriodTF.getText());

                if (creditScore <= 600) {
                    interestRate = 10;
                } else if (creditScore <= 700) {
                    interestRate = 7;
                } else {
                    interestRate = 4;
                }

                if (loanType.equalsIgnoreCase("mortgage")) {
                    interestRate -= 1;
                } else {
                    interestRate += 1;
                }

                if (existingDebt > 0) {
                    interestRate += 2;
                }

                interestRate = (interestRate / 100);

                totalLoanPayment = ((double) loanAmount / (repaymentPeriod * 12)) * (1 +
                        interestRate);
                deptToIncome = (totalLoanPayment + existingDebt) / income;

                if (employment.equalsIgnoreCase("unemployed")) {
                    decision = "Rejected due to unemployment";
                } else if (creditScore < 600) {
                    decision = "Rejected due to low credit score";
                } else if (maxDTI < deptToIncome) {
                    decision = "Rejected: Your monthly payment would be too high (40+% of your income)";
                } else {
                    decision = "Approved\nMonthly loan payment: " + (int) totalLoanPayment;
                }
                JOptionPane.showMessageDialog(frame, decision);
            }
        });

        frame.setSize(700, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}