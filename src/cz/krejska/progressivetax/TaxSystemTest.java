package cz.krejska.progressivetax;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Daniel Krejska
 * @since 17.8.2023
 */
class TaxSystemTest
{

    @Test
    @DisplayName("No tax rate")
    void calculateTax1()
    {
        TaxSystem cz = new TaxSystem();

        assertAll(() -> assertEquals(0, cz.calculateTax(0)),
                () -> assertEquals(0, cz.calculateTax(100)));
    }

    @Test
    @DisplayName("One tax rate")
    void calculateTax2()
    {
        TaxSystem cz = new TaxSystem();

        cz.addTaxRate(0, 20);

        assertAll(() -> assertEquals(0, cz.calculateTax(0)),
                () -> assertEquals(20, cz.calculateTax(100)),
                () -> assertEquals(40, cz.calculateTax(200)));
    }

    @Test
    @DisplayName("Two tax rates")
    void calculateTax3()
    {
        TaxSystem cz = new TaxSystem();

        cz.addTaxRate(0, 20);
        cz.addTaxRate(500, 40);

        assertAll(() -> assertEquals(0, cz.calculateTax(0)),
                () -> assertEquals(20, cz.calculateTax(100)),
                () -> assertEquals(40, cz.calculateTax(200)),
                () -> assertEquals(100, cz.calculateTax(500)),
                () -> assertEquals(100 + 200, cz.calculateTax(1000)));

    }
}
