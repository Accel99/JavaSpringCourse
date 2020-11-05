package ru.education.entity;

public class SalesJdbc {

    private int id;

    private int productId;

    private int count;

    private int price;

    public SalesJdbc(int id, int productId, int count, int price) {
        this.id = id;
        this.productId = productId;
        this.count = count;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
