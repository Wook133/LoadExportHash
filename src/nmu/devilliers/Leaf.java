package nmu.devilliers;

import javafx.util.Pair;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Leaf<T> implements Serializable, Cloneable
{
    public final Long maxNumberBytes = 536870912L; //512MB is the absolute maximum size of a given leaf
    public abstract Boolean equalsTo(Leaf obj1);
    public abstract Integer compareTo(Leaf obj1);
    public abstract ArrayList<Pair<String, String>> toArraylistPairForSending();
    public abstract String toString();
    public abstract String Type();
    public abstract String PrimaryKeyOfCreator();
    public abstract String hashCargo();
    public abstract String cargoToString();
    public abstract Boolean cargoEquals(Leaf obj);
    public abstract Integer cargoCompareTo(Leaf obj);


    protected T cargo;
    public Leaf(T cargo){this.cargo = cargo;}
    public Leaf(){}


    /**
     * Creates and returns a copy of this object
     *
     * @return a clone of this instance
     */
    @SuppressWarnings("unchecked")
    @Override
    public Leaf<T> clone()
    {
        try {
            return (Leaf<T>) super.clone();
        } catch (CloneNotSupportedException e) {
            String message = "Unable to clone the current tree node";
            throw new LeafException(message, e);
        }
    }

    /**
     * Indicates whether some object equals to this one
     *
     * @param obj the reference object with which to compare
     * @return {@code true} if this object is the same as the obj
     *         argument; {@code false} otherwise
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null
                || getClass() != obj.getClass()) {
            return false;
        }
        Leaf<T> that = (Leaf<T>) obj;
        return this.hashCargo().equals(that.hashCargo());
    }


}
