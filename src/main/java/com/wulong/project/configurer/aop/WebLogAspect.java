package com.wulong.project.configurer.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wulong.project.service.SysLogService;
import com.wulong.project.slog.SLog;
import com.wulong.project.tool.IpUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * AOP统一日志处理
 *
 * @Author:wulong
 * @Date:2018/7/9 14:34
 * @mail:491925129@qq.com
 */
@Aspect
@Component
public class WebLogAspect {
	private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

	private static Long startTime;

	@Autowired
	private SysLogService sysLogService;

	/**
	* @Description: 后面2个*表示controller包下所有类的所有方法，如果一个*会报错
	* @Param:
	* @return:
	* @Author: wulong
	* @Date: 2018/7/9
	*/
	@Pointcut("execution(public * com.wulong.project.web.*.*(..))")
	private void webLog(){}

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) {
		startTime = System.currentTimeMillis();
		// 接收到请求，记录请求内容
		logger.info("------------------------- 请求成功 Start -------------------------");
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		// 记录下请求内容
		logger.info("请求路径: " + request.getRequestURL().toString());
		logger.info("请求方式: " + request.getMethod());
		logger.info("请求来源: " + IpUtils.getIpAddr(request));
		logger.info("响应方法: " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature
				().getName());
		//获取所有参数方法一：
		Enumeration<String> enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String paraName = enu.nextElement();
			if (paraName.equals("password")) {
				logger.info("请求参数: " + paraName + ":******");
			} else {
				logger.info("请求参数: "+ paraName+":"+request.getParameter(paraName));
			}
		}
	}

	/**
	 * 请求结束
	 * @param joinPoint
	 * @param retVal
	 */
	@AfterReturning(pointcut="webLog()",argNames = "joinPoint,retVal",returning = "retVal")
	public void doAfterReturning(JoinPoint joinPoint,Object retVal) {
		logger.info("请求耗时: "+(System.currentTimeMillis()-startTime)+"毫秒");
		Map<String,Object> result = JSONObject.parseObject(JSON.toJSONString(retVal));
		if (result.containsKey("data")) {
			Map<String,Object> dataMap = (Map<String, Object>) result.get("data");
			if (dataMap.containsKey("token")) {
				((Map<String, Object>) result.get("data")).put("token","******");
			}
		}
		doSLog(joinPoint,retVal);
		logger.info("返回结果: "+JSON.toJSONString(result));
		// 处理完请求，返回内容
		logger.info("------------------------- 请求成功  End  -------------------------");
	}


	public void doSLog(JoinPoint joinPoint, Object retVal) {
		// 拦截方法上的SLog注解
		Signature signature = joinPoint.getSignature();
		MethodSignature methodSignature = (MethodSignature)signature;
		Method targetMethod = methodSignature.getMethod();
		if (targetMethod.isAnnotationPresent(SLog.class)) {
			SLog sLog = (SLog)targetMethod.getAnnotation(SLog.class);
			String[] params = sLog.param().split(",");
			String param = "";
			for (int i = 0; i < params.length; i++) {
				String[] paramList = params[i].split(":");
				param = param + paramList[0]+":"+targetMethod.getParameterCount();
			}
			System.out.print(param);
		}
	}
}

