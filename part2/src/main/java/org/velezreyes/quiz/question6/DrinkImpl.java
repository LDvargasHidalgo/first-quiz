package org.velezreyes.quiz.question6;

public class DrinkImpl implements Drink {

    private String name;

    public DrinkImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean isFizzy() {
        return this.name.contains("ScottCola");
    }
}
