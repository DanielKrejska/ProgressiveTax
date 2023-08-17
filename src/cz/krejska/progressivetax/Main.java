package cz.krejska.progressivetax;

import java.util.Scanner;

/**
 * @author Daniel Krejska
 * @since 15.8.2023
 */
public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        String country = getCountry(scanner);
        int income = getIncome(scanner);
        scanner.close();

        TaxSystem cz = new TaxSystem();
        cz.addTaxRate(0, 20);
        int taxToPay = cz.calculateTax(income);
        System.out.println("before tax: " + income);
        System.out.println("tax: " + taxToPay);
        System.out.println("after tax: " + (income - taxToPay));
    }

    static String getCountry(Scanner scanner)
    {
        System.out.print("country: ");
        return scanner.nextLine();
    }

    static int getIncome(Scanner scanner)
    {
        System.out.print("€: ");
        while (!scanner.hasNextInt())
        {
            scanner.nextLine();
            System.err.println("input for income is not INT, try again");
        }
        return scanner.nextInt();
    }
}
