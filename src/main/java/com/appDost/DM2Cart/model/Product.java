package com.appDost.DM2Cart.model;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import java.time.Instant;
import java.util.*;
import org.hibernate.type.SqlTypes;


@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ================= TENANT =================
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;




    // ================= BASIC DETAILS =================
    @Column(nullable = false)
    private String title;

    @Column(unique = true)
    private String sku;

    @Column(name = "short_desc")
    private String shortDesc;

    @Column(name = "long_desc", columnDefinition = "TEXT")
    private String longDesc;

    @Column
    private Double price;

    @Column(length = 3)
    private String currency = "INR";

    @Column
    private Double taxPercent ;

    private boolean active = true;

    // ================= ATTRIBUTES (JSON) =================

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> attributes;

    // ================= CATEGORIES (ManyToMany) =================
    @ManyToMany
    @JoinTable(
            name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();



    // ================= AUDIT =================
    @Column(name = "created_at", updatable = false)
    private Instant createdAt = Instant.now();

    @Column(name = "updated_at")
    private Instant updatedAt = Instant.now();


    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ProductImage> images = new ArrayList<>();


    // ================= GETTERS & SETTERS =================

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Seller getSeller() { return seller; }
    public void setSeller(Seller seller) { this.seller = seller; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    public String getShortDesc() { return shortDesc; }
    public void setShortDesc(String shortDesc) { this.shortDesc = shortDesc; }

    public String getLongDesc() { return longDesc; }
    public void setLongDesc(String longDesc) { this.longDesc = longDesc; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public Double getTaxPercent() { return taxPercent; }
    public void setTaxPercent(Double taxPercent) { this.taxPercent = taxPercent; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public Map<String, Object> getAttributes() { return attributes; }
    public void setAttributes(Map<String, Object> attributes) { this.attributes = attributes; }

    public Set<Category> getCategories() { return categories; }
    public void setCategories(Set<Category> categories) { this.categories = categories; }



    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }


    public List<ProductImage> getImages() {
        return images;
    }

    public void setImages(List<ProductImage> images) {
        this.images = images;
    }
}
