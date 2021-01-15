package demo.preStatePara;

/**
 * Created by MAYUNXIA on 2016/8/24.
 */
public class PreStatePara {
    private int index;
    private Object value;
    private String fieldType;

    public PreStatePara(int index, Object value){
        this.index = index;
        this.value = value;
        this.fieldType = value.getClass().getSimpleName();
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String toString()
    {
        return "{index:"+index+", value:"+String.valueOf(value)+", fieldType: "+fieldType+"}";
    }
}
