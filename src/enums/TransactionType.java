package enums;

public enum TransactionType {

    // En transaktion kan endast ha typen av en inkomst eller utgift


    INKOMST("Inkomst"),
    UTGIFT("Utgift");

    // Variabel displaynamee
    private final String displayName;

    // Konstruktor för displayName
    TransactionType(String displayName){
        this.displayName = displayName;
    }

    // Returnerar namnet
    public String getDisplayName() {
        return displayName;
    }
}
