package com.swift;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The iban format registry text file can be downloaded here: https://www.swift.com/standards/data-standards/iban
 */
public class Registry {

    private Map<String, RegistryFormat> countryCodeToRegistryFormat;

    public Registry(File registryFile) throws IOException {
        countryCodeToRegistryFormat = parse(read(registryFile));
    }

    private static String read(File registryFile) throws IOException {
        FileInputStream fis = new FileInputStream(registryFile);
        byte[] data = new byte[(int) registryFile.length()];
        fis.read(data);
        fis.close();

        return new String(data, "ISO-8859-1");
    }

    private static Map<String, RegistryFormat> parse(String content) throws IOException {
        Iterable<CSVRecord> records = CSVFormat.TDF.parse(new StringReader(content));
        List<Map<String, String>> csvColumns = transpose(records);

        Map<String, RegistryFormat> countryCodeToRegistryFormat = new HashMap<String, RegistryFormat>();
        for (Map<String, String> column : csvColumns) {
            RegistryFormat format = map(column);
            countryCodeToRegistryFormat.put(format.getCountryCode(), format);
        }

        return countryCodeToRegistryFormat;
    }

    private static List<Map<String, String>> transpose(Iterable<CSVRecord> records) {
        ArrayList<CSVRecord> rows = new ArrayList<CSVRecord>();
        for (CSVRecord record : records) {
            rows.add(record);
        }

        List<Map<String, String>> columns = new ArrayList<Map<String, String>>();
        for (int i = 1; i < rows.get(1).size(); i++) {  // column 0 is the header column
            Map<String, String> column = new HashMap<String, String>();
            for (int j = 1; j < rows.size(); j++) {  // row 0 is the 'Data element' row
                column.put(rows.get(j).get(0), rows.get(j).get(i));
            }
            columns.add(column);
        }
        return columns;
    }

    private static RegistryFormat map(Map<String, String> column) {
        return new RegistryFormat(
            column.get("BBAN structure"),
            column.get("IBAN prefix country code (ISO 3166)"),
            column.get("Name of country"),
            column.get("IBAN electronic format example")
        );
    }

    public RegistryFormat getRegistryFormat(String countryCode) {
        return countryCodeToRegistryFormat.get(countryCode.toUpperCase());
    }

    public Set<RegistryFormat> getRegistryFormats() {
        return new HashSet<RegistryFormat>(countryCodeToRegistryFormat.values());
    }
}
