package cz.muni.fi.cepv;

import cz.muni.fi.cepv.web.SimpleCORSFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
* @author xgarcar
*/
public class ServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {ApplicationConfig.class, SecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/api/*"};
    }

//    @Override
//    protected Filter[] getServletFilters() {
//        return new Filter[] {new SimpleCORSFilter()};
//    }
}
