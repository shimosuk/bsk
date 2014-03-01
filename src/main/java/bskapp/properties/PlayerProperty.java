package bskapp.properties;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class PlayerProperty {
    private IntegerProperty totalProperty = new SimpleIntegerProperty();
    private IntegerProperty foulProperty = new SimpleIntegerProperty();

    public abstract Integer getTotal();
    public abstract Integer getFoul();

    public IntegerProperty totalProperty() {
        return this.totalProperty;
    }

    public IntegerProperty foulProperty() {
        return this.foulProperty;
    }

    public void bindTotalProperty() {
        totalProperty.setValue(getTotal());
    }

    public void bindFoulProperty() {
        foulProperty.setValue(getFoul());
    }
}
