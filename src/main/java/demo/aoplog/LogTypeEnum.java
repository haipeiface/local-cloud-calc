package demo.aoplog;

public enum LogTypeEnum {
    //��־ö������
    WEB("-1"), DUBBO("1"), MQ("2");

    private final String value;

    LogTypeEnum(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
