package edu.dosw.lab.estructurales.Reto4;

import java.util.Map;

public class RealExchangeRateService {

    private final Map<String, Double> ratesToUSD = Map.of(
            "USD", 1.0,
            "EUR", 1.1,
            "JPY", 0.0066666667,
            "COP", 0.00025
    );

    public double toUSD(double amount, String currency) {
        return amount * ratesToUSD.get(currency);
    }

    public double fromUSD(double amount, String currency) {
        return amount / ratesToUSD.get(currency);
    }
}
