package com.parthi.logistic.common.model;

import java.util.Arrays;

public enum TransactionCategory {
    ACCESSORY("ACCESSORY"),
    ADVERTISEMENT("ADVERTISEMENT"),
    BANK_COST("BANK COST"),
    CAMPAIGN_COST("CAMPAIGN COST"),
    DEPOSITE("DEPOSITE"),
    FOOD("FOOD"),
    INSTALLMENT("INSTALLMENT"),
    INVESTMENT("INVESTMENT"),
    INVESTMENT_RETURN("INVESTMENT RETURN"),
    MISC_COST("MISC COST"),
    OPERATIONAL_COST("OPERATIONAL COST"),
    PRODUCT_COST("PRODUCT COST"),
    PRODUCT_RETURN("PRODUCT RETURN"),
    SALARY("SALARY"),
    SALES("SALES"),
    TRAVEL("TRAVEL");

    private final String label;

    TransactionCategory(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    // ✅ Return array of labels (excluding PRODUCT_RETURN)
    public static String[] getLabels() {
        return Arrays.stream(values())
                     .filter(tc -> tc != PRODUCT_RETURN && tc != PRODUCT_COST)
                     .map(TransactionCategory::getLabel)
                     .toArray(String[]::new);
    }
}
