package testJersey2;

import java.net.URI;
import java.text.MessageFormat;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class WebServer {
    public static void main(String[] args) {
        if (args == null || args.length < 2 || !args[1].matches("^\\d+$") ) {
            System.out.println("Invalid parameters. Use with parameters: <server host> <server port> [root class package>]");
            System.exit(1);
        }
        String serverhost = args[0].trim();
        int serverport = Integer.parseInt(args[1].trim());
        String rootclass = args.length > 2 ? args[2].trim() : null;
        
        new WebServer().initWebServer(serverhost, serverport, rootclass);
    }
    
    private void initWebServer(String rest_serverhost, int rest_serverport, String rest_rootclass) {
        System.out.println("java.home = " + System.getProperty("java.home"));
        System.out.println("java.library.path = " + System.getProperty("java.library.path"));
        System.out.println("java.class.path = " + System.getProperty("java.class.path"));

        String baseUri = MessageFormat.format("http://{0}:{1}/", rest_serverhost, Integer.toString(rest_serverport)); //$NON-NLS-1$
        
        ResourceConfig rc = new ResourceConfig().packages(rest_rootclass); //$NON-NLS-1$
        JdkHttpServerFactory.createHttpServer(URI.create(baseUri), rc, true);

        System.out.println(MessageFormat.format("Webservice startet at {0} with root classes at {1}", baseUri, rest_rootclass)); //$NON-NLS-1$
        System.out.println(MessageFormat.format("Now you can access the webservice. Try to connect to {0}foo/bar", baseUri)); //$NON-NLS-1$
    }
}
