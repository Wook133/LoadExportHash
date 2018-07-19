package nmu.devilliers;

import javafx.util.Pair;

import java.util.ArrayList;

public class Source extends Leaf<deVillCargo> implements Comparable<deVillCargo>{

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
        return this.cargo.getPublicAddressOfCreator();
    }

    @Override
    public String hashCargo() {
        return this.cargo.hashCargo();
       // return this.cargo.getHashofFile();
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
    public long getApproximateSize()
    {
        long a = this.cargo.getApproximateSize() + Type().length();
        return a;
    }

    /**
     * determine whether this hash is the same as that hash
     * @param s1 the Source to compare to
     * @return true if hashes are the same
     */
    public boolean ThisHashEqualThatHash(Source s1)
    {
        if (this.hashCargo().compareTo(s1.hashCargo()) == 0)
            return true;
        else
            return false;
    }

    @Override
    public int compareTo(deVillCargo o) {
        int i = this.hashCargo().compareTo(o.hashCargo());
        if (i == 0)
        {
            int j = this.toString().compareTo(o.toString());
            if (j == 0)
            {
                return 0;
            }
            else
                return j;
        }
        else
            return i;


    }
}
