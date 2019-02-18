package test;

import java.util.Calendar;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 需要在web.xml 中配置启动
 * @author Administrator
 *
 */
public class SocketListener implements ServletContextListener{
    private Timer timer;

    @Override
    public void contextInitialized(ServletContextEvent e) {
        timer = new Timer();
        //配置定时器
        Calendar calendar = Calendar.getInstance();
        System.out.println("设置在每天的" + 1 + "点执行!");
        
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH) + 1, 1, 0, 0);
        //每天定时执行
        timer.schedule(new TestTimerTask(), calendar.getTime(), 1 * 24 * 60 * 60 * 1000);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        if (timer != null) {
            timer.cancel();
        }
    }

}
