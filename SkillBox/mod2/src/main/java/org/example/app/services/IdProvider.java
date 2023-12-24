package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class IdProvider implements InitializingBean, DisposableBean, BeanPostProcessor {

    Logger logger = Logger.getLogger(IdProvider.class);

    public String provideId(Book book) {
        return this.hashCode() + "_" + book.hashCode();
    }

    private void initIdProvider() {
        logger.info("provider INIT");
    }

    private void destroyIdProvider() {
        logger.info("provider DESTROY");
    }

    public void defaultInit() {
        logger.info("default INIT in provider");
    }

    public void defaultDestroy() {
        logger.info("default DESTROY in provider");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("provider afterPropertiesSet is called");
    }

    @Override
    public void destroy() throws Exception {
        logger.info("the provider afterPropertiesSet parameter is called");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        logger.info("the provider afterPropertiesSet parameter from the provider is called " + beanName);
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        logger.info("Postprocessafterinitialization caused by the component " + beanName);
        return null;
    }

    @PostConstruct
    public void postConstructIdProvider() {
        logger.info("The annotated PostConstruct method being called");
    }

    @PreDestroy
    public void preDestroyIdProvider() {
        logger.info("A pre-created annotated method called");
    }
}
