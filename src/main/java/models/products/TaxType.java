package models.products;

public enum TaxType {
    A(0.23), B(0.08), C(0.05);
    private final double interest;

    TaxType(double interest) {
        this.interest = interest;
    }

    public double getInterest() {
        return interest;
    }
}
