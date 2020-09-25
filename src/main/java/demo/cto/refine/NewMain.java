package demo.cto.refine;

public class NewMain {

    public static void main(String[] args) {
        String sign = "TOUTIAO";
        ChannelRuleEnum channelRule = ChannelRuleEnum.match(sign);
        GeneralChannelRule rule = channelRule.channel;
        rule.process();
    }
}
