package cz.krejska.progressivetax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Daniel Krejska
 * @since 15.8.2023
 */
public class Main
{
    public static void main(String[] args) throws IOException
    {
        HashMap<String, TaxSystem> economies = new HashMap<>();
        DataLoader.loadTaxSystems(economies, "data/economies.csv", ";");

        Scanner scanner = new Scanner(System.in);
        String country = getCountry(scanner, new ArrayList<>(economies.keySet()));
        double income = getIncome(scanner);
        scanner.close();

        double taxToPay = economies.get(country).calculateTax(income);

        String format = "%-15s %10.2f €\n";
        System.out.format(format, "before tax:", income);
        System.out.format(format, "tax:", taxToPay);
        System.out.format(format, "after rax:", income - taxToPay);
    }

    static String getCountry(Scanner scanner, ArrayList<String> validCountries)
    {
        System.out.println("valid countries are>> " + validCountries.toString());
        System.out.print("country: ");
        String userInput = scanner.nextLine();
        while (!validCountries.contains(userInput))
        {
            System.err.println("that's not a valid option, try again");
            userInput = scanner.nextLine();
        }
        return userInput;
    }

    static double getIncome(Scanner scanner)
    {
        System.out.print("€: ");
        double result;
        while ( (result = inputDouble(scanner)) < 0 )
        {
            System.err.println("income can not be negative");
        }
        return result;
    }

    static double inputDouble(Scanner scanner)
    {
        while (!scanner.hasNextDouble())
        {
            scanner.nextLine();
            System.err.println("this is not DOUBLE (use ',' instead of '.'), try again");
        }
        return scanner.nextDouble();
    }
}
