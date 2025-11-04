package Transaction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Transaction {

    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    protected UUID ID;
    protected String description;
    protected int amount;
    protected LocalDateTime date;
    protected boolean isIncome;

    public Transaction(String description, int amount, LocalDateTime date, boolean isIncome){
        this.ID = UUID.randomUUID();
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.isIncome = isIncome;
    }

    public UUID getID() {
        return ID;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setAmount(int amount){
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

    @Override
    public String toString() {

        return ID + description + amount + date + isIncome;


    }
}
