package blockchain;

import java.util.Date;
import java.text.SimpleDateFormat;


public class Block {
	private String hashCode, timeStamp;
	 
	public Block(VacinationData vaxData) {
		this.timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.ms").format(new Date());
		this.hashCode = MD5.getMd5(timeStamp + vaxData.getFirstName() + vaxData.getLastName());
	}

	public String getHashCode() {
		return hashCode;
	}

}
