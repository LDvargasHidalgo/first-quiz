package org.velezreyes.quiz.question6;

public interface VendingMachine {

  public void insertQuarter();

  // Presiona un botón para seleccionar una bebida por su nombre. Puede lanzar dos excepciones:
  // NotEnoughMoneyException si no hay suficiente dinero insertado y UnknownDrinkException si
  // la bebida seleccionada no es reconocida.
  public Drink pressButton(String name) throws NotEnoughMoneyException, UnknownDrinkException;
}