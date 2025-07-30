package com.example.cc5lab;

import java.io.Serializable;
import java.util.*;

public class Order implements Serializable {
  private static final long serialVersionUID = 1L;
  private List<Drink> drinks = new ArrayList<>();

  public Order(List<Drink> drinks) { this.drinks = drinks; }

  public List<Drink> getDrinks() { return drinks; }

  @Override public String toString() { return "Order" + drinks; }
}

