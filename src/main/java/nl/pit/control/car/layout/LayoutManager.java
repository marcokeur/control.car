package nl.pit.control.car.layout;

import nl.pit.control.car.exception.BlockException;
import nl.pit.control.car.layout.block.Block;

import java.util.ArrayList;
import java.util.List;

public class LayoutManager {
    List<Block> blockList = new ArrayList<Block>();

    public volatile static LayoutManager layoutManager;

    private LayoutManager(){

    }

    public static LayoutManager getInstance(){
        if(layoutManager == null){
            synchronized (LayoutManager.class){
                if(layoutManager == null){
                    layoutManager = new LayoutManager();
                }
            }
        }

        return layoutManager;
    }

    public void addBlock(Block b){
        blockList.add(b);
    }

    public int getBlockListCount() {
        return blockList.size();
    }

    public Block getBlock(int i) {
        i--;

        if(i < 0){
            return null;
        }

        if(blockList.size() >= i){
            return blockList.get(i);
        }
        return null;
    }

    public Block removeBlock(int i){
        i--;

        if(blockList.size() >= i){
            return blockList.remove(i);
        }
        return null;
    }

    public Block getNext(Block b) throws BlockException {
        Integer nextId = b.getNextId();
        if(nextId == null){
            throw new BlockException(b.getName() + " has no next block");
        }else{
            Block nextBlock = getBlockById(nextId);

            if(nextBlock == null){
                throw new BlockException("Block with id " + nextId + " does not exist.");
            }else{
                return nextBlock;
            }
        }
    }

    public Block getPrev(Block b) throws BlockException {
        Integer prevId = b.getPrevId();
        if(prevId == null){
            throw new BlockException(b.getName() + " has no previous block");
        }else{
            Block prevBlock = getBlockById(prevId);

            if(prevBlock == null){
                throw new BlockException("Block with id " + prevId + " does not exist");
            }else{
                return prevBlock;
            }
        }
    }

    public Block getBlockById(Integer id){
        for(Block b : blockList){
            if(b.getId().equals(id)){
                return b;
            }
        }

        return null;
    }

    public Block getBlockByName(String name){
        for(Block b : blockList){
            if(b.getName().equals(name)){
                return b;
            }
        }

        return null;
    }

    public void setNext(Block current, Block next) {
        current.setNextId(next.getId());
        next.setPrevId(current.getId());
    }

    public void setPrev(Block current, Block prev){
        current.setPrevId(prev.getId());
        prev.setNextId(current.getId());
    }
}
