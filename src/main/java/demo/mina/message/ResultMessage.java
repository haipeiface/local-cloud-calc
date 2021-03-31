package demo.mina.message;

public class ResultMessage extends AbstractMessage{
    private boolean ok;

    private int value;

    public ResultMessage() {
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        if (ok) {
            return getSequence() + ":RESULT(" + value + ')';
        } else {
            return getSequence() + ":RESULT(ERROR)";
        }
    }
}