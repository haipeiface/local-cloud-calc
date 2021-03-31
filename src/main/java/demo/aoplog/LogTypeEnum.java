package demo.aoplog;

public enum LogTypeEnum {
    //日志枚举类型
    WEB("-1"), DUBBO("1"), MQ("2");

    private final String value;

    LogTypeEnum(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
