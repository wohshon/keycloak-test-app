package com.redhat.apps.app1.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * MainWebAppInitializer
 */
public class MainWebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext sc) throws ServletException {
        AnnotationConfigWebApplicationContext root = 
        new AnnotationConfigWebApplicationContext();
      
      root.scan("com.redhat.apps.app1");
      sc.addListener(new ContextLoaderListener(root));

      ServletRegistration.Dynamic appServlet = 
        sc.addServlet("mvc", new DispatcherServlet(new GenericWebApplicationContext()));
      appServlet.setLoadOnStartup(1);
      appServlet.addMapping("*.page");
        
    }
    
}