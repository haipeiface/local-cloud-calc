package demo.aoplog;

import com.alibaba.fastjson.JSON;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtMethod;
import javassist.bytecode.LocalVariableAttribute;
import org.apache.commons.lang.StringUtils;

import org.apache.http.client.utils.DateUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class WebLogAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebLogAspect.class);

    private long startTime = 0L;

    private long endTime = 0L;

    /**
     * Controller���е�
     */
//    @Pointcut("execution(* demo.aoplog.TestAspectController.addPost(..))")
    @Pointcut("execution(* *..controller..*.*(..))")
    public void controllerAspect() {
    }

    /**
     * ǰ��֪ͨ ��������Controller���¼�û��Ĳ���
     *
     * @param joinPoint �е�
     */
    @Before("controllerAspect()")
    public void doBeforeInServiceLayer(JoinPoint joinPoint) {
    }

    /**
     * ����controller����֪ͨ,ʹ���ڷ���aspect()��ע��������
     *
     * @param point �е�
     * @return
     * @throws Throwable
     */
    @Around("controllerAspect()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        // ��ȡrequest
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();

        //Ŀ�귽��ʵ��
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        boolean hasMethodLogAnno = method
                .isAnnotationPresent(Loggable.class);
        //û��ע�� ֱ��ִ�з��ؽ��
        if (!hasMethodLogAnno) {
            return point.proceed();
        }

        //��־��ӡ�ⲿ����Ĭ�Ϲر�
//        String logSwitch = StringUtils.equals(RedisUtil.get(BaseConstants.CACHE_WEB_LOG_SWITCH), BaseConstants.YES) ? BaseConstants.YES : BaseConstants.NO;
        String logSwitch = BaseConstants.YES;

        //��¼��־��Ϣ
        LogMessage logMessage = new LogMessage();

        //����ע��ʵ��
        Loggable methodLogAnnon = method.getAnnotation(Loggable.class);

        //���������־
        handleRequstLog(point, methodLogAnnon, request, logMessage, logSwitch);

        //ִ��Ŀ�귽�����ݣ���ȡִ�н��
        Object result = point.proceed();

        //����ӿ���Ӧ��־
        handleResponseLog(logSwitch, logMessage, methodLogAnnon, result);
        return result;
    }

    /**
     * ���������־
     *
     * @param point          ���е�
     * @param methodLogAnnon ����־ע��
     * @param logMessage     ����־��Ϣ��¼ʵ��
     */
    private void handleRequstLog(ProceedingJoinPoint point, Loggable methodLogAnnon, HttpServletRequest request,
                                 LogMessage logMessage, String logSwitch) throws Exception {

        String paramsText = "";
        //�����б�
        String includeParam = methodLogAnnon.include();
        Map<String, Object> methodParamNames = getMethodParamNames(
                point.getTarget().getClass(), point.getSignature().getName(), includeParam);
        Map<String, Object> params = getArgsMap(
                point, methodParamNames);
        if (params != null) {
            //���л������б�
            paramsText = JSON.toJSONString(params);
        }
        logMessage.setParameter(paramsText);
        //�ж��Ƿ������־
        if (methodLogAnnon.loggable()
                && methodLogAnnon.scope().contains(LogScopeEnum.BEFORE)
                && methodLogAnnon.console()
                && StringUtils.equals(logSwitch, BaseConstants.YES)) {
            //��ӡ�����־
            LOGGER.info("��{}�� �ӿ���γɹ�!, ��������:��{}��, �������:��{}��", methodLogAnnon.descp().toString(), point.getSignature().getName(), paramsText);
        }
        startTime = System.currentTimeMillis();
        //�ӿ�����
        logMessage.setDescription(methodLogAnnon.descp().toString());

        //...ʡ�Բ��ֹ���logMessage��Ϣ����
    }

    /**
     * ������Ӧ��־
     *
     * @param logSwitch         �ⲿ��־���أ������ⲿ��̬������־��ӡ
     * @param logMessage        ��־��¼��Ϣʵ��
     * @param methodLogAnnon    ��־ע��ʵ��
     * @param result         �� �ӿ�ִ�н��
     */
    private void handleResponseLog(String logSwitch, LogMessage logMessage, Loggable methodLogAnnon, Object result) {
        endTime = System.currentTimeMillis();
        //����ʱ��
        logMessage.setEndTime(endTime);
        //����ʱ��
        logMessage.setSpendTime(endTime - startTime);
        //�Ƿ������־
        if (methodLogAnnon.loggable()
                && methodLogAnnon.scope().contains(LogScopeEnum.AFTER)) {
            //�ж��Ƿ����
            if (methodLogAnnon.db()) {
                //...ʡ��������
            }
            //�ж��Ƿ����������̨
            if (methodLogAnnon.console()
                    && StringUtils.equals(logSwitch, BaseConstants.YES)) {
                //...ʡ�Դ�ӡ��־����
                LOGGER.info("spend time : {}", endTime - startTime);
            }
        }
    }
    /**
     * ��ȡ������α�����
     *
     * @param cls        ��������
     * @param methodName �����ķ�����
     * @param include    ��Ҫ��ӡ�ı�����
     * @return
     * @throws Exception
     */
    private Map<String, Object> getMethodParamNames(Class cls,
                                                    String methodName, String include) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath(new ClassClassPath(cls));
        CtMethod cm = pool.get(cls.getName()).getDeclaredMethod(methodName);
        LocalVariableAttribute attr = (LocalVariableAttribute) cm
                .getMethodInfo().getCodeAttribute()
                .getAttribute(LocalVariableAttribute.tag);

        if (attr == null) {
            throw new Exception("attr is null");
        } else {
            Map<String, Object> paramNames = new HashMap<>();
            int paramNamesLen = cm.getParameterTypes().length;
            int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
            if (StringUtils.isEmpty(include)) {
                for (int i = 0; i < paramNamesLen; i++) {
                    paramNames.put(attr.variableName(i + pos), i);
                }
            } else { // ��include��Ϊ��
                for (int i = 0; i < paramNamesLen; i++) {
                    String paramName = attr.variableName(i + pos);
                    if (include.indexOf(paramName) > -1) {
                        paramNames.put(paramName, i);
                    }
                }
            }
            return paramNames;
        }
    }

    /**
     * ��װ���Map
     *
     * @param point���������������е�
     * @param methodParamNames���������Ƽ���
     * @return
     */
    private Map getArgsMap(ProceedingJoinPoint point,
                           Map<String, Object> methodParamNames) {
        Object[] args = point.getArgs();
        if (null == methodParamNames) {
            return Collections.EMPTY_MAP;
        }
        for (Map.Entry<String, Object> entry : methodParamNames.entrySet()) {
            int index = Integer.valueOf(String.valueOf(entry.getValue()));
            if (args != null && args.length > 0) {
                Object arg = (null == args[index] ? "" : args[index]);
                methodParamNames.put(entry.getKey(), arg);
            }
        }
        return methodParamNames;
    }
}
