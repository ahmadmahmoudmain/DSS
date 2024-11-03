import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        double income, totalLoanPayment;
        int creditScore, repaymentPeriod, loanAmount, interestRate;
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

        if (existingDebt) { interestRate += 2; }

        totalLoanPayment = calculateTotalLoanPayments(loanAmount, interestRate, repaymentPeriod);

        if (income < 2000 || creditScore < 600 || !employment) {
            System.out.print("\nRejected");
        } else {
            System.out.println("Approved \n Your Total payment will be: " + totalLoanPayment);
        }
    }

    public static int scoreValidation(int score) {
        if (score <= 1000) {
            return score;
        } else {
            return 0;
        }
    }

    public static boolean existingValidation(int input) {
        return input == 1;
    }

    public static String typeValidation(int input) {
        if (input == 1) {
            return "Mortgage";
        } else {
            return "Personal";
        }
    }

    public static boolean empValidation(int input) { return input == 1; }

    public static double calculateTotalLoanPayments(double loanAmount, double interestRate, int repaymentPeriod) {
        interestRate = (interestRate / 100);
        return (1 + loanAmount * interestRate) * repaymentPeriod;
    }
}