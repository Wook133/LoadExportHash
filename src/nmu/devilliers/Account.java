package nmu.devilliers;

import java.util.Objects;

public class Account {
    private String privateKey;
    public String publicAddress;

    public Account(String privateKey, String publicAddress) {
        this.privateKey = privateKey;
        this.publicAddress = publicAddress;
    }

    public Account() {
        this.privateKey = null;
        this.publicAddress = null;
    }

    public Account(String privateKey) {
        this.privateKey = privateKey;
        try {
            GeneralHASH gh = new GeneralHASH();
            publicAddress = gh.HashnoPrint(privateKey, "SHA3-512");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        this.publicAddress = publicAddress;
    }

    /**
     * Test whether this private key generates this public address
     * @param pk
     * @return
     */
    public boolean verifyPrivateKey(String pk)
    {
        try {
            GeneralHASH gh = new GeneralHASH();
            String sHash = gh.HashnoPrint(pk, "SHA3-512");
            if (sHash.compareTo(this.publicAddress) == 0)
                return true;
            else
                return false;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public String getPublicAddress() {
        return publicAddress;
    }

    public void setPublicAddress(String publicAddress) {
        this.publicAddress = publicAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(privateKey, account.privateKey) &&
                Objects.equals(getPublicAddress(), account.getPublicAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(privateKey, getPublicAddress());
    }

    public String toString() {
        return this.getPublicAddress();
    }
}
