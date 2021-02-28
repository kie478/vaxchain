package BlockChainData;

import java.util.ArrayList;
import java.util.List;

public class BlockChain{
    public List<Block> chain;
    public BlockChain(){
        chain = new ArrayList<Block>();
        chain.add(createGenesisBlock());
    }

    private Block createGenesisBlock()
    {
        return new Block(new VaccinationData("GENESIS", "BLOCK", 0), "NO_HASH");
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

    public boolean isHashValid(int i)
    {
        if(!(chain.get(i).getPreviousHash().equals(chain.get(i - 1).getHash())))
            return false;
        return true;
    }

    public Block getBlock(int i)
    {
        return chain.get(i);
    }

    public boolean isChainValid(){
        for(int i = 1; i < chain.size(); i++){
            Block currentBlock = chain.get(i);
            Block previousBlock = chain.get(i - 1);

            if(!currentBlock.getPreviousHash().equals(previousBlock.getHash()))
                return false;
            if(!currentBlock.getHash().equals(currentBlock.calculateHash()))
                return false;
        }
        return true;
    }
}
