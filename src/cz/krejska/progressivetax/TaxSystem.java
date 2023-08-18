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
    // intelliJ wants it final, no idea why or why it works
    private final ArrayList<TaxRateLimit> taxRateLimits;

    TaxSystem()
    {
        taxRateLimits = new ArrayList<>();
    }

    public void addTaxRate(int incomeLimit, int taxRate)
    {
        this.taxRateLimits.add(new TaxRateLimit(incomeLimit, taxRate));
        this.taxRateLimits.sort(Comparator.comparingInt(TaxRateLimit::incomeLimit).reversed());
    }

    public double calculateTax(double income)
    {
        int totalTax = 0;
        for (TaxRateLimit taxRateLimit : this.taxRateLimits)
        {
            double diff = income - taxRateLimit.incomeLimit();
            if (diff <= 0)
            {
                continue;
            }
            income -= diff;
            totalTax += (int) (diff * ((double) taxRateLimit.taxRate() / 100));
        }
        return totalTax;
    }
}
