package bskapp.properties;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class TeamProperty {
    private SimpleIntegerProperty totalProperty = new SimpleIntegerProperty();
    private SimpleDoubleProperty foulProperty = new SimpleDoubleProperty();

    public abstract Integer getTotal();
    public abstract Integer getFoul();

    public IntegerProperty totalProperty() {
        return this.totalProperty;
    }

    public DoubleProperty foulProperty() {
        return this.foulProperty;
    }

    public void setTotalProperty() {
        this.totalProperty.setValue(getTotal());
    }

    public void setFoulProperty() {
        this.foulProperty.setValue((double)getTotal()/5);
    }
}
