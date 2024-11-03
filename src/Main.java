import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        double totalLoanPayment, interestRate, deptToIncome;
        double maxDTI = 0.4;
        int income, creditScore, repaymentPeriod, loanAmount;
        boolean existingDebt, employment;
        String loanType;

        Scanner input = new Scanner(System.in);

        System.out.print("Enter the desired loan amount: ");
        loanAmount = input.nextInt();

        System.out.print("Enter your credit score: ");
        creditScore = scoreValidation(input.nextInt());

        System.out.print("Do you have any existing loan? (1 - Yes | 2 - No) ");
        existingDebt = existingValidation(input.nextInt());

        System.out.print("Enter the loan type (1 - Mortgage | 2 - Personal): ");
        loanType = typeValidation(input.nextInt());

        System.out.print("Are you employed? (1 - Yes | 2 - No) ");
        employment = empValidation(input.nextInt());

        System.out.print("Enter your monthly income: ");
        income = input.nextInt();

        System.out.print("Enter the loan repayment period in years: ");
        repaymentPeriod = input.nextInt();

        input.close();

        if (creditScore <= 600) {
            interestRate = 10;
        } else if (creditScore <= 700) {
            interestRate = 7;
        } else {
            interestRate = 4;
        }

        if (loanType.equals("Mortgage")) {
            interestRate -= 1;
        } else {
            interestRate += 1;
        }

        if (existingDebt) {
            interestRate += 2;
        }

        interestRate = (interestRate / 100);

        totalLoanPayment = ((double) loanAmount / (repaymentPeriod * 12)) * (1 + interestRate);
        deptToIncome = totalLoanPayment / income;

        if (!employment) {
            System.out.println("Rejected due to unemployment");
        } else if (creditScore < 600) {
            System.out.println("Rejected due to low credit score");
        } else if (maxDTI < deptToIncome)
            System.out.println("Rejected: Your monthly payment would be too high (40+% of your income)");
        else {
            System.out.println("\nApproved");
            System.out.print("Monthly loan payment: " + (int) totalLoanPayment);
        }
    }

    public static int scoreValidation(int score) {
        return score <= 1000 ? score : 0;
    }

    public static boolean existingValidation(int input) {
        return input == 1;
    }

    public static String typeValidation(int input) {
        return input == 1 ? "Mortgage" : "Personal";
    }

    public static boolean empValidation(int input) {
        return input == 1;
    }
}