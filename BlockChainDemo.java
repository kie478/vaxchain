package blockchain;

public class BlockChainDemo {
	public static void main(String[] args) {
		
		VacinationData testVax;
		Block testBlock = new Block(testVax = new VacinationData("Kyle", "G"));
		
		System.out.println(testBlock.getHashCode());
	}
}
