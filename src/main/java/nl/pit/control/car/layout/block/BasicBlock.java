package nl.pit.control.car.layout.block;

public class BasicBlock implements Block {
    Integer id = null;
    String name = null;
    Integer length = null;

    Integer nextId = null;
    Integer prevId = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public void setNextId(Integer id) {
        nextId = id;
    }

    @Override
    public void setPrevId(Integer id) {
        prevId = id;
    }

    @Override
    public Integer getPrevId() {
        return prevId;
    }

    @Override
    public Integer getNextId() {
        return nextId;
    }
/*
    Block next = null;
    Block prev = null;

    public Block getNext() {
        return next;
    }

    public void setNext(Block b) {
        this.next = b;

        // if "b" is next, means we are b->prev
        if(b.getPrev() != this) {
            b.setPrev(this);
        }
    }

    public Block getPrev() {
        return prev;
    }

    public void setPrev(Block b) {
        this.prev = b;

        // if "b" is prev, means we are b->next
        if(b.getNext() != this){
            b.setNext(this);
        }
    }
*/
}
