package org.lyh.model;

import java.io.Serializable;

/**
 * @author lyh
 * @version 2019-10-18 10:44
 */
public class Trade implements Serializable {

    private static final long serialVersionUID = -3841423880124665364L;

    private String symbol;

    private double price;

    private int size;

    public Trade() {
    }

    public Trade(String symbol, double price, int size) {
        this.symbol = symbol;
        this.price = price;
        this.size = size;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "symbol='" + symbol + '\'' +
                ", price=" + price +
                ", size=" + size +
                '}';
    }
}
