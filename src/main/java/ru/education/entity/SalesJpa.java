package ru.education.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "sales")
public class SalesJpa {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sales_id_seq")
    @SequenceGenerator(name = "sales_id_seq", sequenceName = "sales_id_seq", allocationSize = 1)
    private int id;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;

    @Column(name = "count")
    private int count;

    @Column(name = "price")
    private int price;

    public SalesJpa() { }

    public SalesJpa(int id, Product product, int count, int price) {
        this.id = id;
        this.product = product;
        this.count = count;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
