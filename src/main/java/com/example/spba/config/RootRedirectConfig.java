package com.example.spba.config;

import org.apache.catalina.Container;
import org.apache.catalina.Valve;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ValveBase;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class RootRedirectConfig implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        factory.addContextCustomizers(context -> {
            Container host = context.getParent();
            if (host == null) {
                return;
            }
            boolean alreadyAdded = false;
            for (Valve valve : host.getPipeline().getValves()) {
                if (valve instanceof RootRedirectValve) {
                    alreadyAdded = true;
                    break;
                }
            }
            if (!alreadyAdded) {
                host.getPipeline().addValve(new RootRedirectValve());
            }
        });
    }

    static class RootRedirectValve extends ValveBase {

        @Override
        public void invoke(Request request, Response response) throws IOException, ServletException {
            String uri = request.getRequestURI();
            if ("/".equals(uri)) {
                System.out.println("=======>>invokeLocation");
                response.setStatus(HttpServletResponse.SC_FOUND);
                response.setHeader("Location", "/spba-api/home/index.html");
                response.finishResponse();
                return;
            }
            getNext().invoke(request, response);
        }
    }
}
