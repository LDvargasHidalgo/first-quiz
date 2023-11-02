package org.velezreyes.quiz.question6;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class VendingMachineImpl implements VendingMachine {

  private double quarter;
  private Drink drink;
  private List<String> drinks = Arrays.asList("ScottCola", "KarenTea");

  public static VendingMachine getInstance() {
    // Fix me!
    return new VendingMachineImpl();
  }

  @Override
  public void insertQuarter() {
    this.quarter += 0.25;
  }

  @Override
  public Drink pressButton(String name) throws NotEnoughMoneyException, UnknownDrinkException {
    if (this.quarter == 0) {
      throw new NotEnoughMoneyException();
    }

    Optional<String> selectedDrink = drinks.stream()
            .filter(name::contains)
            .findFirst();

    if (selectedDrink.isPresent()) {
      if (selectedDrink.get().contains("Tea") && quarter < 1) {
        throw new NotEnoughMoneyException();
      }
      this.drink = new DrinkImpl(selectedDrink.get());
      this.quarter = 0.0;
      return this.drink;
    } else {
      throw new UnknownDrinkException();
    }
  }
}