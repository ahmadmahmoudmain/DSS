import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        double income;
        int creditScore, repaymentPeriod, loanAmount;
        boolean existingDebt, employment;
        String loanType;

        Scanner input = new Scanner(System.in);

        System.out.print("Enter the desired loan amount: ");
        loanAmount = input.nextInt();

        System.out.print("Enter your credit score: ");
        creditScore = scoreVali(input.nextInt());

        System.out.print("Do you have any existing loan? (1 - Yes | 2 - No) ");
        existingDebt = existingVali(input.nextInt());

        System.out.print("Enter the loan type (1 - Mortgage | 2 - Personal): ");
        loanType = typeVali(input.nextInt());

        System.out.print("Are you employed? (1 - Yes | 2 - No) ");
        employment = empVali(input.nextInt());

        System.out.print("Enter your monthly income: ");
        income = input.nextInt();

        System.out.print("Enter the loan repayment period in years: ");
        repaymentPeriod = input.nextInt();

        input.close();
    }

    public static int scoreVali(int score){
        if(score <= 1000) {
            return score;
        }
        else{
            return 0;
        }
    }

    public static boolean existingVali(int input){
        return input == 1;
    }

    public static String typeVali(int input){
        if(input == 1){
            return "Mortgage";
        }
        else{
            return "Personal";
        }
    }

    public static boolean empVali(int input){
        return input == 1;
    }
}