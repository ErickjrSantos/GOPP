package com.example.admin.estoquescan.Classes;

public class Product {
    private String barCode, internalCode, description, price;
    private boolean sale;
    private String address, addressType;
    private int stock;

    public Product(String barCode, String internalCode, String description, int stock, String price, boolean sale){
        setBarCode(barCode);
        setInternalCode(internalCode);
        setDescription(description);
        setStock(stock);
        setPrice(price);
        setInSale(sale);
    }

    public String getAddressType() {return addressType;}

    public void setAddressType(String addressType) {this.addressType = addressType;}

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    public String getBarCode() {
        return barCode;
    }

    private void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getInternalCode() {
        return internalCode;
    }

    private void setInternalCode(String internalCode) {
        this.internalCode = internalCode;
    }

    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    public boolean isInSale() {
        return sale;
    }

    private void setInSale(boolean sale) {
        this.sale = sale;
    }

    public int getStock() {
        return stock;
    }

    private void setStock(int stock) {
        this.stock = stock;
    }

    public String getPrice() {
        return price;
    }

    private void setPrice(String price) {
        this.price = price;
    }
}
