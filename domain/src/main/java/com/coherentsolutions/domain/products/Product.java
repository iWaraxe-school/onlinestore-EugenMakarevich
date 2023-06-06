package com.coherentsolutions.domain.products;

public class Product {
    private String name;
    private double rate;
    private double price;

    //Introducing Builder pattern
    public static Builder newBuilder() {
        return new Product().new Builder();
    }

    public class Builder {
        private String name;
        private double rate;
        private double price;

        private Builder() {
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setRate(double rate) {
            this.rate = rate;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Product build() {
            Product.this.name = this.name;
            Product.this.price = this.price;
            Product.this.rate = this.rate;
            return Product.this;
        }
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", rate=" + rate +
                ", price=" + price;
    }
}
