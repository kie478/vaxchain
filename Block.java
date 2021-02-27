import java.text.SimpleDateFormat;
import java.util.Date;

public class Block{
    private String hash, previousHash, timeStamp;
    private VaccinationData data;

    public Block(VaccinationData data, String previousHash){
        this.data = data;
        this.previousHash = "";
        this.timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        this.hash = calculateHash();
    }

    private String calculateHash()
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
}
