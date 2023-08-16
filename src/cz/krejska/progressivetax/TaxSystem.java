package cz.krejska.progressivetax;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Tax system for a country. You add progressive tax rates. Then you can calculate tax on entered income.
 *
 * @author Daniel Krejska
 * @since 16.8.2023
 */
final class TaxSystem
{
    private final String country;
    private ArrayList<TaxRateLimit> taxRateLimits;

    TaxSystem(String country)
    {
        this.country = country;
        taxRateLimits = new ArrayList<TaxRateLimit>();
    }

    public void addTaxRate(int incomeLimit, int taxRate)
    {
        this.taxRateLimits.add(new TaxRateLimit(incomeLimit, taxRate));
        this.taxRateLimits.sort(Comparator.comparingInt(TaxRateLimit::incomeLimit));
    }

    public int calculateTax(int income)
    {
        int totalTax = 0;
        for (TaxRateLimit taxRateLimit : this.taxRateLimits)
        {
            int diff = income - taxRateLimit.incomeLimit();
            if (diff <= 0)
            {
                continue;
            }
            income -= diff;
            totalTax += (diff * (taxRateLimit.taxRate() / 100));
        }
        return totalTax;
    }

    public String getCountry()
    {
        return country;
    }
}
