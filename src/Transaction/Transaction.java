package Transaction;

import java.time.LocalDateTime;

public class Transaction {

    protected int ID;
    protected String description;
    protected int amount;
    protected LocalDateTime dateTime;

    public Transaction(int ID, String description, int amount, LocalDateTime dateTime){

        this.ID = ID++;
        this.description = description;
        this.amount = amount;
        this.dateTime = dateTime;

    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setDescription() {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setAmount(){
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
