package edu.dosw.lab.estructurales.Reto4;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Reto4 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        CurrencyConverter converter =
                new ExchangeAdapter(new RealExchangeRateService());

        System.out.print("Ingrese número de transacciones: ");
        int n = sc.nextInt();
        sc.nextLine();

        List<Transaction> transactions = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            System.out.println("\n--- Transacción " + i + " ---");

            System.out.print("Ingrese monto: ");
            double amount = sc.nextDouble();
            sc.nextLine();

            System.out.print("Ingrese moneda de origen: ");
            String origin = sc.nextLine().toUpperCase();

            System.out.print("Ingrese monedas destino (separadas por coma): ");
            List<String> dests = Arrays.stream(sc.nextLine().split(","))
                    .map(String::trim)
                    .map(String::toUpperCase)
                    .collect(Collectors.toList());

            transactions.add(new Transaction(amount, origin, dests));
        }

        Map<String, Double> totals = new HashMap<>();

        int index = 1;
        for (Transaction t : transactions) {
            System.out.println("\nTransacción " + index++ + ": " +
                    t.amount + " " + t.origin);

            t.destinations.forEach(dest -> {
                double converted = converter.convert(t.amount, t.origin, dest);
                System.out.printf("Convertido a %s: %.2f %s%n",
                        dest, converted, dest);

                totals.merge(dest, converted, Double::sum);
            });
        }

        System.out.println("\n--- Totales por moneda ---");
        totals.forEach((k, v) ->
                System.out.printf("%s: %.2f %s%n", k, v, k));
    }

    public static void ejecutar() {
        main(new String[0]);
    }
}
