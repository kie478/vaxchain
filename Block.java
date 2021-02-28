package BlockChainData;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Block{
    private String hash, previousHash, timeStamp;
    private VaccinationData data;
    private static int counter;
    private int iD;

    public Block(VaccinationData data, String previousHash){
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        this.hash = calculateHash();
        this.iD = counter++;
    }

    public VaccinationData getVaccData()
    {
        return this.data;
    }


    public String calculateHash()
    {
        String preHash = data.getFirstName() + data.getLastName() + data.getAge() + timeStamp;
        hash = MD5.getMd5(preHash);
        return hash;
    }

    public String getPreviousHash()
    {
        return previousHash;
    }

    public void setPreviousHash(String previousHash)
    {
        this.previousHash = previousHash;
    }

    public String getHash()
    {
        return hash;
    }

    public void setHash(String hash)
    {
        this.hash = hash;
    }

    public int getiD() {
        return iD;
    }
}
