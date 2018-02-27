package org.zerock.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;


public class WebConfig  extends AbstractAnnotationConfigDispatcherServletInitializer {

    private final String UPLOAD_DIR = "C:\\upload\\tmp";
    // 10MB
    private final long MAX_UPLOAD_SIZE = 1024 * 1024 * 10;

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{ServletConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {

        MultipartConfigElement multi =
                new MultipartConfigElement(
                        //directory name
                        UPLOAD_DIR,
                        // file size
                        MAX_UPLOAD_SIZE,
                        // the maximum size allowed for multiPART/form-data requests
                        MAX_UPLOAD_SIZE * 5,
                        //the size threshold after which files will be written to disk
                        (int)MAX_UPLOAD_SIZE/2
                );

        registration.setMultipartConfig(multi);

    }
}
