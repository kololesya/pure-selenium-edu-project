package com.solvd.laba.projectConstants;

public enum Products {

    WHITE_TOP("White Top", 400),
    UNICORN_PRINT("unicorn print", 500),
    REGULAR_FIT("regular fit", 1200);

    private final String name;
    private final double price;

    Products (String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getText() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
