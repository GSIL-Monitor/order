package test;

import java.util.Calendar;
import java.util.Timer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * To change this template use File | Settings | File Templates.
 */
public class ServerSocketListener implements ServletContextListener {
    private Timer timer;

    @Override
	public void contextInitialized(ServletContextEvent e) {
        ServletContext servletContext = e.getServletContext();
        timer = new Timer();
        //配置定时器
        Calendar calendar = Calendar.getInstance();
        int runHour = 2;
//        if (calendar.get(Calendar.HOUR_OF_DAY) <= runHour) {
        System.out.println("设置在每天的" + runHour + "点执行!");
        
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH) + 1, runHour, 0, 0);
        //每天执行
        //timer.schedule(new DictionaryService(), calendar.getTime(), 1 * 24 * 60 * 60 * 1000);
    }

    @Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
        if (timer != null) {
            timer.cancel();
        }
    }
}
