package demo.mina.message;

import java.io.Serializable;

public class AbstractMessage implements Serializable {
    private int sequence;

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
}
