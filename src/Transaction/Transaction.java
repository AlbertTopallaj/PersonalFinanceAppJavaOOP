package Transaction;

import enums.TransactionType;
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
    private TransactionType type;

    // Konstruktor för transaktionen med genererat UUID
    public Transaction(String description, int amount, LocalDateTime date, TransactionType type) {
        this.ID = UUID.randomUUID();
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.type = type;
    }

    // Konstruktor med färdig genererat UUID
    public Transaction(UUID ID, String description, int amount, LocalDateTime date, TransactionType type) {
        this.ID = ID;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.type = type;
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

    public TransactionType getType() {
        return type;
    }

    public void setIncome(TransactionType type) {
        this.type = type;
    }
}

