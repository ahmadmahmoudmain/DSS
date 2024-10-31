import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        double income, loanAmount;
        int creditScore, loanType, repaymentPeriod;
        boolean existingDebt, employment;

        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to The Loan Decision app!");
        System.out.println("Enter the desired loan amount: ");
        loanAmount = input.nextDouble();

        System.out.println("Enter your credit score: ");
        creditScore = input.nextInt();

        System.out.println("Do you have any existing loan? ");
        existingDebt = input.nextBoolean();

        System.out.println("Enter the loan type (1 - Mortgage | 2 - Personal): ");
        loanType = input.nextInt();

        System.out.println("Are you employed? ");
        employment = input.nextBoolean();

        System.out.println("Enter your monthly income: ");
        income = input.nextInt();

        System.out.println("Enter the loan repayment period in years: ");
        repaymentPeriod = input.nextInt();
    }
}