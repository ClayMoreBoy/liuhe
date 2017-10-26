package com.lin.liuhe.ascept;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.beans.factory.annotation.Autowired;

import com.lin.liuhe.service.ManagerLogService;
import com.lin.liuhe.thread.MyLogAspectBindRequest;
import com.lin.liuhe.thread.MyRouterBind;
import com.lin.liuhe.utils.GlobalString;
/**
 * 这是用环绕通知来进行切面
 * @author Rs
 *
 */
public class MyLogAspect {
	@Autowired
	ManagerLogService managerLogService;

	public Object recordLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		//这是要返回的值的，不管是否返回什么东西，都要进行返回
		Object returnValue = null;
		//进行声明一些变量
		String logOperator=null;//这是保存是否可以用户登录了的字符串
		String logTime=null;//保存日志的时间
		String logMethodName=null;//保存的是这个调用的方法名
		String logTypeName=null;//保存的是这个调用方法的参数类型
		String logInputData=null;//保存输入的是什么数据
		String logOutputData=null;//保存的输出的是什么数据
		String logExceptionType="rs";//这是异常的类型
		String logExceptionMessage="rs";//这个是产生异常的来源
		//环绕通知
		try{
			//得到参数列表
			Object[] args = proceedingJoinPoint.getArgs();
			//1.对logInputData输入数据进行设置
			if(args != null && args.length > 0){
				StringBuilder sb=new StringBuilder();
				for (Object object : args) {
					sb.append(",").append(object);
				}
				logInputData = sb.substring(1);
			}else{
				logInputData = "没有数据输入";
			}
			//获取方法签名
			Signature signature = proceedingJoinPoint.getSignature();
			//5.对logMethodName进行设置
			logMethodName = signature.getName();
			//6.对logTypeName进行设置
			logTypeName = signature.getDeclaringTypeName();
			//执行目标方法,得到返回值,不过不一定有返回值
			returnValue = proceedingJoinPoint.proceed(args);
		}catch(Exception t){
			//获取异常对象的全类名
			logExceptionType = t.getClass().getName();

			//获取异常的消息
			logExceptionMessage = t.getMessage();

			//尝试获取当前异常对象的原因
			Throwable cause = t.getCause();

			while(cause != null) {

				//使用原因的异常对象的全类名
				logExceptionType = cause.getClass().getName();

				//使用原因的异常对象的消息
				logExceptionMessage = cause.getMessage();

				//尝试获取原因的原因
				cause = cause.getCause();

				//※注意：千万别这么写——这会导致无限死循环
				//cause = e.getCause();
			}

			/*for(Throwable cause = e.getCause();cause != null;cause = cause.getCause()) {
				//使用原因的异常对象的全类名
				exceptionType = cause.getClass().getName();

				//使用原因的异常对象的消息
				exceptionMessage = cause.getMessage();
			}*/

			//将捕获到的异常继续向上抛出
			throw t;
		}finally{
			//2.对logOutputData输出的是什么数据 进行设置
			if(logOutputData == null){
				logOutputData ="没有输出数据";
			}else{
				logOutputData = logOutputData.toString();
			}
			//怎么拿到request对象,这个隔着一个框架，用线程进行特殊调用Thread
			HttpServletRequest request = MyLogAspectBindRequest.getLogAspectBindRequest();
			//3.对logOperator进行设置
			//ManagerUser managerUser =  (ManagerUser) request.getSession().getAttribute(GlobalString.MANGERUSER);
			//User user = (User) request.getSession().getAttribute(GlobalString.USER);
			//String stringManager = managerUser == null ? "管理员没有登录":"管理员:"+managerUser.getManagerName()+"登录操作";
			//String stringUser = user == null ? "用户没有登录":"用户:"+user.getName()+"登录操作";
			//logOperator = stringManager+"/"+stringUser;
			//4.对logTime时间设置
			logTime = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date());
			//对日志的数据进行封装
			//Manager_Log log =new Manager_Log(logOperator, logTime, logMethodName, logTypeName, logInputData, logOutputData, logExceptionType, logExceptionMessage);
			//获得分布数据源
			MyRouterBind.setRouterBind(GlobalString.LOG_DATASOURCE_KEY);
			//进行保存
		//	managerLogService.saveCurrentLog(log);
		}
		//无论是否有返回值，都返回给下一个方法调用,
		return returnValue;
	}
}
