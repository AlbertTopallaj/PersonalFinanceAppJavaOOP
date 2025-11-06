package Transaction;

import services.TransactionService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Transaction {

    // Formattera datumformat
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // ID med UUID
    private final UUID ID;

    // Beskrivning
    private String description;

    // Belopp
    private int amount;

    // Datum
    private LocalDateTime date;

    // Typ
    private boolean isIncome;

    // Konstruktor för transaktionen med genererat UUID
    public Transaction(String description, int amount, LocalDateTime date, boolean isIncome) {
        this.ID = UUID.randomUUID();
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.isIncome = isIncome;
    }

    // Konstruktor med färdig genererat UUID
    public Transaction(UUID ID, String description, int amount, LocalDateTime date, boolean isIncome) {
        this.ID = ID;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.isIncome = isIncome;
    }

    // Getters och setters för samtliga variabler

    public UUID getID() {
        return ID;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.date = dateTime;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public boolean getType() {
        return isIncome;
    }

    public void setIncome(boolean income) {
        isIncome = income;
    }
}

