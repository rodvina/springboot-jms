package com.ro.springboot.jms;

public class Order implements Serializable {

    String from
    String to
    BigDecimal amount

    @Override
    public String toString() {
        return "Order{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", amount=" + amount +
                '}';
    }
}
