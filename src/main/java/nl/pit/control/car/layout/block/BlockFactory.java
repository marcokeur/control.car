package nl.pit.control.car.layout.block;

public class BlockFactory {

    public static Integer blockCounter = 0;

    public static Block getBlock(String blockType){
        if(blockType == null){
            return null;
        }

        blockCounter++;

        if(blockType.equalsIgnoreCase("Basic")){
            Block b = new BasicBlock();
            b.setId(blockCounter);
            return b;
        }else if(blockType.equalsIgnoreCase("Switch")){
            return new TurnoutBlock();
        }

        return null;
    }
}
