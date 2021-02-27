import java.util.ArrayList;
import java.util.List;

public class BlockChain{
    private List<Block> chain;
    public BlockChain(){
        chain = new ArrayList<Block>();
        chain.add(createGenesisBlock());
    }

    private Block createGenesisBlock()
    {
        return new Block(new VacinationData("FIRST_NAME", "LAST_NAME", 0), "0");
    }

    public void addBlock(Block block)
    {
        block.setPreviousHash(getPreviousBlock().getHash());
        chain.add(block);
    }

    public Block getPreviousBlock()
    {
        return chain.get(chain.size() - 1);
    }
}
