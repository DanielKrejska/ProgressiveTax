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
        int income = getIncome(scanner);
        scanner.close();

        int taxToPay = economies.get(country).calculateTax(income);

        System.out.println("before tax: " + income);
        System.out.println("tax: " + taxToPay);
        System.out.println("after tax: " + (income - taxToPay));
    }

    static String getCountry(Scanner scanner, ArrayList<String> validCountries)
    {
        System.out.println("valid countries are>> " + validCountries.toString());
        System.out.print("country: ");
        String userInput = scanner.nextLine();
        while (!validCountries.contains(userInput))
        {
            System.err.println("invalid input for country, try again");
            userInput = scanner.nextLine();
        }
        return userInput;
    }

    static int getIncome(Scanner scanner)
    {
        System.out.print("€: ");
        while (!scanner.hasNextInt())
        {
            scanner.nextLine();
            System.err.println("invalid input for income, try again");
        }
        return scanner.nextInt();
    }
}
