package edu.dosw.lab.estructurales.Reto4;

import java.util.List;

public class Transaction {
    double amount;
    String origin;
    List<String> destinations;

    public Transaction(double amount, String origin, List<String> destinations) {
        this.amount = amount;
        this.origin = origin;
        this.destinations = destinations;
    }
}
