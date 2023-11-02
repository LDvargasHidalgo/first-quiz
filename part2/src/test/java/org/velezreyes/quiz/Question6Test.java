package org.velezreyes.quiz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.velezreyes.quiz.question6.Drink;
import org.velezreyes.quiz.question6.NotEnoughMoneyException;
import org.velezreyes.quiz.question6.UnknownDrinkException;
import org.velezreyes.quiz.question6.VendingMachine;
import org.velezreyes.quiz.question6.VendingMachineImpl;

public class Question6Test {
  @Test
  public void canCreateVendingMachineInstance() {
    // Verifica que se pueda crear una instancia de VendingMachineImpl.
    VendingMachine vm = VendingMachineImpl.getInstance();
    assertNotNull(vm);
  }


  // Verifica que se lance una excepción NotEnoughMoneyException
  // cuando se intenta comprar una bebida sin insertar dinero.
  @Test
  public void drinkNotFree() {
    VendingMachine vm = VendingMachineImpl.getInstance();
    Exception exception = assertThrows(NotEnoughMoneyException.class, () -> {
      vm.pressButton("ScottCola");
    });
  }

  @Test
  public void canGetScottColaFor75Cents() throws Exception {
    // Verifica que se pueda comprar la bebida ScottCola por 75 centavos.
    VendingMachine vm = VendingMachineImpl.getInstance();

    // Inserta 75 centavos (3 cuartos) para comprar ScottCola.
    vm.insertQuarter();
    vm.insertQuarter();
    vm.insertQuarter();

    // Intenta comprar ScottCola.
    Drink drink = vm.pressButton("ScottCola");

    // Verifica las propiedades de la bebida comprada.
    assertTrue(drink.isFizzy());
    assertEquals(drink.getName(), "ScottCola");
  }

  @Test
  public void machineResets() throws Exception {
    // Verifica que la máquina se resetee después de una compra.
    VendingMachine vm = VendingMachineImpl.getInstance();

    // Inserta dinero para comprar ScottCola.
    vm.insertQuarter();
    vm.insertQuarter();
    vm.insertQuarter();

    // Compra ScottCola.
    Drink drink = vm.pressButton("ScottCola");
    assertNotNull(drink);

    // Intenta comprar ScottCola nuevamente sin insertar más dinero.
    Exception exception = assertThrows(NotEnoughMoneyException.class, () -> {
      vm.pressButton("ScottCola");
    });
  }

  @Test
  public void canGetKarenTeaForOneDollar() throws Exception {
    // Verifica que se pueda comprar KarenTea por un dólar.
    VendingMachine vm = VendingMachineImpl.getInstance();

    // Inserta 75 centavos (3 cuartos), no es suficiente para KarenTea.
    vm.insertQuarter();
    vm.insertQuarter();
    vm.insertQuarter();

    // Intenta comprar KarenTea sin suficiente dinero.
    assertThrows(NotEnoughMoneyException.class, () -> {
      vm.pressButton("KarenTea");
    });

    // Inserta un cuarto más para tener un dólar en total.
    vm.insertQuarter();

    // Compra KarenTea.
    Drink drink = vm.pressButton("KarenTea");

    // Verifica las propiedades de la bebida comprada.
    assertFalse(drink.isFizzy());
    assertEquals(drink.getName(), "KarenTea");
  }

  @Test
  public void otherDrinksUnknown() throws Exception {
    // Verifica que se lance UnknownDrinkException cuando se intenta comprar una bebida desconocida.
    VendingMachine vm = VendingMachineImpl.getInstance();

    // Inserta 1 dólar (4 cuartos).
    vm.insertQuarter();
    vm.insertQuarter();
    vm.insertQuarter();
    vm.insertQuarter();

    // Intenta comprar una bebida desconocida.
    assertThrows(UnknownDrinkException.class, () -> {
      vm.pressButton("BessieBooze");
    });
  }
}