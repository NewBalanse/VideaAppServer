package launch;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

public class MainClass {
    public static void main(String[] args) {
        try {
            String webAppString = "src/main/webapp/";
            Tomcat tomcat = new Tomcat();

            String webport = System.getenv("PORT");
            if (webport == null || webport.trim().length() == 0)
                webport = "8080";

            
            tomcat.setPort(Integer.valueOf(webport));

            StandardContext StandartContx = (StandardContext) tomcat.addWebapp("",new File(webAppString).getAbsolutePath());
            System.out.println("configuring app with basedir: " + new File(webAppString).getAbsolutePath());

            File additionWebInfClasses = new File("target/classes");
            WebResourceRoot resourceRoot = new StandardRoot(StandartContx);
            resourceRoot.addPreResources(new DirResourceSet(resourceRoot,"/WEB-INF/classes",
                    additionWebInfClasses.getAbsolutePath(),"/"));
            StandartContx.setResources(resourceRoot);

            tomcat.start();
            tomcat.getServer().await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
