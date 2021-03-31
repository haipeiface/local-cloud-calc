package demo.aoplog;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author peifs
 */
@Target({ElementType.METHOD}) //ע�������ڷ�������
@Retention(RetentionPolicy.RUNTIME) //����ʱ������
public @interface Loggable {
    /**
     * �Ƿ������־
     */
    boolean loggable() default true;

    /**
     * ��־��Ϣ����,���Լ�¼�÷��������õ���Ϣ��
     */
    String descp() default "";

    /**
     * ��־���ͣ����ܴ��ڶ��ֽӿ����Ͷ���Ҫ��¼��־������dubbo�ӿڣ�web�ӿ�
     */
    LogTypeEnum type() default LogTypeEnum.WEB;

    /**
     * ��־�ȼ�
     */
    String level() default "INFO";

    /**
     * ��־�����Χ,���ڱ����Ҫ��¼����־��Ϣ��Χ��������Ρ�����ֵ�ȡ�
     * ALL-��κͳ���, BEFORE-���, AFTER-����
     */
    LogScopeEnum scope() default LogScopeEnum.ALL;

    /**
     * ��������Χ��ֵΪ��α�����������򶺺ŷָ��Ϊ��ʱ�������־����ӡinclude�еı���
     */
    String include() default "";

    /**
     * �Ƿ�������ݿ�
     */
    boolean db() default true;

    /**
     * �Ƿ����������̨
     *
     * @return
     */
    boolean console() default true;
}
