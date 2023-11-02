package org.velezreyes.quiz.question6;

public class NotEnoughMoneyException extends Exception {

  //indicar que no se ha insertado suficiente dinero en la máquina expendedora para comprar un producto.
  public NotEnoughMoneyException() {
    super("Not enough money inserted.");
  }
}
