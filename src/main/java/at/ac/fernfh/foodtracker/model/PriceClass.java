package at.ac.fernfh.foodtracker.model;

public enum PriceClass {

    ONE ("€"),
    TWO ("€€"),
    THREE ("€€€"),
    FOUR ("€€€€");

    private final String priceClass;

    /**
     * Constructor
     * @param priceClass the price class
     */
    PriceClass(final String priceClass) {
        this.priceClass = priceClass;
    }

    public String getPriceClass() {
        return priceClass;
    }

    @Override
    public String toString() {
        return "PriceClass{" +
                "priceClass='" + priceClass + '\'' +
                '}';
    }
}
