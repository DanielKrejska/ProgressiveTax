package cz.krejska.progressivetax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author Daniel Krejska
 * @since 17.8.2023
 */
class DataLoader
{
    static void loadTaxSystems(HashMap<String, TaxSystem> map, String path, String separator) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        while ((line = reader.readLine()) != null)
        {
            String[] separatedLine = line.split(separator);
            map.put(separatedLine[0], new TaxSystem());
            for (int index = 1; index < separatedLine.length - 1; index += 2)
            {
                map.get(separatedLine[0]).addTaxRate(Integer.parseInt(separatedLine[index]),
                        Integer.parseInt(separatedLine[index + 1]));
            }
        }
    }
}
