package edu.dosw.lab.estructurales.Reto4;

public class ExchangeAdapter implements CurrencyConverter {

    private final RealExchangeRateService service;

    public ExchangeAdapter(RealExchangeRateService service) {
        this.service = service;
    }

    @Override
    public double convert(double amount, String from, String to) {
        double usd = service.toUSD(amount, from);
        return service.fromUSD(usd, to);
    }
}
