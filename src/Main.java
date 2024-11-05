import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Loan Approval System");
        // Declare gui labels
        JLabel creditScoreL, loanAmountL, existingDebtL, loanTypeL, employmentStatusL, monthlyIncomeL,
                repaymentPeriodL;
        // Declare text fields
        JTextField creditScoreTF, loanAmountTF, existingDebtTF, loanTypeTF, employmentStatusTF, monthlyIncomeTF,
                repaymentPeriodTF;
        // Declare check eligibility button
        JButton checkEligability;

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
        checkEligability = new JButton("Check Eligibility");

        // Create a form panel with GridLayout for labels and text fields
        JPanel formPanel = new JPanel(new GridLayout(8, 10, 10, 10)); // 8 rows, 10 columns, with v and h gaps

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

        // Create a panel for the button
        JPanel buttonPanel = new JPanel(); // Default FlowLayout centers the button
        // Add the button to it's panel
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
                boolean validInput = true;

                // Assign initial values
                creditScore = loanAmount = existingDebt = income = repaymentPeriod = 0;
                loanType = employment = "";

                // Store values entered to text fields into variables after parsing it
                try {
                    creditScore = Integer.parseInt(creditScoreTF.getText());
                    loanAmount = Integer.parseInt(loanAmountTF.getText());
                    existingDebt = Integer.parseInt(existingDebtTF.getText());
                    income = Integer.parseInt(monthlyIncomeTF.getText());
                    repaymentPeriod = Integer.parseInt(repaymentPeriodTF.getText());
                    loanType = loanTypeTF.getText();
                    employment = employmentStatusTF.getText();

                    if (loanType == null || loanType.trim().isEmpty()) {
                        throw new IllegalArgumentException("Loan type cannot be empty or only whitespace.");
                    }
                    if (employment == null || employment.trim().isEmpty()) {
                        throw new IllegalArgumentException("Employment status cannot be empty or only whitespace.");
                    }

                    if (loanType.matches(".*\\d.*")) { // Matches if the input contains any digit
                        throw new IllegalArgumentException(
                                "Loan type cannot contain any numbers. Please enter valid text.");
                    }
                    if (employment.matches(".*\\d.*")) { // Matches if the input contains any digit
                        throw new IllegalArgumentException(
                                "Employment status cannot contain any numbers. Please enter valid text.");
                    }
                } catch (NumberFormatException E) {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please enter valid integers.");
                    validInput = false;
                } catch (IllegalArgumentException o) {
                    JOptionPane.showMessageDialog(frame, o.getMessage());
                    validInput = false;
                }

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
                if (validInput) {
                    JOptionPane.showMessageDialog(frame, decision);
                }
            }
        });

        frame.setSize(700, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}