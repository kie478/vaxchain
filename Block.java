package blockchain;

import java.util.Date;
import java.text.SimpleDateFormat;


public class Block {
	public String hash, previousHash, timeStamp;
	
	public Block(VacinationData vaxData, String previousHash) {
		this.timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.ms").format(new Date());
		this.hash = MD5.getMd5(timeStamp + vaxData.getFirstName() + vaxData.getLastName() + String.valueOf(vaxData.getZipCode()));		
	
	}

	public String getHash() {
		return hash;
	}
	
	public void setAsPreviousHash(String hash) {
		this.previousHash = hash;
	}	
	
	public String getPreviousHash() {
		return previousHash;
	}

	
}
