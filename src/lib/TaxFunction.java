package lib;

public class TaxFunction {

    private static final double TAX_RATE = 0.05;
    private static final int TAX_EXEMPTION_SINGLE = 54000000;
    private static final int TAX_EXEMPTION_MARRIED = 58500000;
    private static final int TAX_EXEMPTION_PER_CHILD = 1500000;
    private static final int MAX_CHILDREN_FOR_TAX_EXEMPTION = 3;

    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {

        validateNumberOfMonths(numberOfMonthWorking);
        numberOfChildren = capNumberOfChildren(numberOfChildren);

        int totalIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;
        int taxExemption = calculateTaxExemption(isMarried, numberOfChildren);

        int taxableIncome = totalIncome - deductible - taxExemption;
        int tax = (int) Math.round(TAX_RATE * taxableIncome);

        return Math.max(0, tax); // Ensure tax is non-negative
    }

    private static void validateNumberOfMonths(int numberOfMonthWorking) {
        if (numberOfMonthWorking > 12) {
            throw new IllegalArgumentException("More than 12 months working per year");
        }
    }

    private static int capNumberOfChildren(int numberOfChildren) {
        return Math.min(numberOfChildren, MAX_CHILDREN_FOR_TAX_EXEMPTION);
    }

    private static int calculateTaxExemption(boolean isMarried, int numberOfChildren) {
        int taxExemption = isMarried ? TAX_EXEMPTION_MARRIED : TAX_EXEMPTION_SINGLE;
        taxExemption += numberOfChildren * TAX_EXEMPTION_PER_CHILD;
        return taxExemption;
    }
}
