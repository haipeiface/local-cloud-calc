package demo.social.weixin.connect;

import demo.social.weixin.api.Weixin;
import demo.social.weixin.api.impl.WeiXinImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

public class WeixinServiceProvider extends AbstractOAuth2ServiceProvider<Weixin> {

    /**
     * 微信获取授权码的url
     */
    private static final String WEIXIN_URL_AUTHORIZE = "https://open.weixin.qq.com/connect/qrconnect";
    /**
     * 微信获取accessToken的url(微信在获取accessToken时也已经返回openId)
     */
    private static final String WEIXIN_URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";

    public WeixinServiceProvider(String appId, String appSecret) {
        super(new WeixinOAuth2Template(appId, appSecret, WEIXIN_URL_AUTHORIZE, WEIXIN_URL_ACCESS_TOKEN));
    }

    @Override
    public Weixin getApi(String accessToken) {
        return new WeiXinImpl(accessToken);
    }
}
