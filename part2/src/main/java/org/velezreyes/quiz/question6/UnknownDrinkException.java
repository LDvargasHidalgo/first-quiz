package org.velezreyes.quiz.question6;

public class UnknownDrinkException extends Exception {


  //Esta excepcion se lanza cuando intente lanzar una bebida desconocida
  public UnknownDrinkException() {
    super("Unknown drink!");
  }
}
