package aston;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class Main {
    public static void main(String[] args) {
        Tomcat server = new Tomcat();
        server.getConnector().setPort(8080);

        Context tomcatContext = server.addContext("", null);

        AnnotationConfigWebApplicationContext applicationContext =
                new AnnotationConfigWebApplicationContext();
        applicationContext.scan("aston");
        applicationContext.setServletContext(tomcatContext.getServletContext());
        applicationContext.refresh();

        DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);
        Wrapper dispatcherWrapper =
                Tomcat.addServlet(tomcatContext, "dispatcher", dispatcherServlet);
        dispatcherWrapper.addMapping("/");
        dispatcherWrapper.setLoadOnStartup(1);

        try {
            server.start();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }
}