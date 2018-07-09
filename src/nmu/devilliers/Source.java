package nmu.devilliers;

import javafx.util.Pair;

import java.util.ArrayList;

public class Source extends Leaf<deVillCargo> {

    public Source(deVillCargo cargo) {
        super(cargo);
    }

    @Override
    public Boolean equalsTo(Leaf obj1) {
        if (this.Type().equals(obj1.Type())) {
            return this.cargo.equalsTo((deVillCargo) obj1.cargo);
        }
        else {
            return this.cargo.toString().equals(obj1.cargo.toString());
        }
    }

    @Override
    public Integer compareTo(Leaf obj1) {
        if (this.Type().equals(obj1.Type())) {
            return this.cargo.compareTo((deVillCargo) obj1.cargo);
        }
        else {
            return this.cargo.toString().compareTo(obj1.cargo.toString());
        }
    }

    @Override
    public ArrayList<Pair<String, String>> toArraylistPairForSending() {
        return this.cargo.toCargoArraylistPairForSending();
    }

    @Override
    public String toString() {
        return  this.Type() + "_" + this.cargoToString();
    }

    @Override
    public String Type() {
        return "Source";
    }

    @Override
    public String PrimaryKeyOfCreator() {
        return this.cargo.getPkAdder();
    }

    @Override
    public String hashCargo() {
        return this.cargo.getHashofFile();
    }

    @Override
    public String cargoToString() {
        return this.cargo.toString();
    }

    @Override
    public Boolean cargoEquals(Leaf obj) {
        if (this.Type().equals(obj.Type())) {
            return this.cargo.equalsTo((deVillCargo) obj.cargo);
        }
        else {
            return this.cargo.toString().equals(obj.cargo.toString());
        }
    }

    @Override
    public Integer cargoCompareTo(Leaf obj) {
        if (this.Type().equals(obj.Type())) {
            return this.cargo.compareTo((deVillCargo) obj.cargo);
        }
        else {
            return this.cargo.toString().compareTo(obj.cargo.toString());
        }
    }
}
