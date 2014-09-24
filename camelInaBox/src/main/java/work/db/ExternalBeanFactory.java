/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package work.db;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 *
 * @author toregard
 */
public class ExternalBeanFactory implements BeanFactoryAware {

    /**
     * Bean factory instance.
     */
    private static BeanFactory beanFactory;

    /**
     * Sets beanfactory.
     * 
     * @param beanFactory
     *            BeanFactory object
     * 
     * @see org.springframework.beans.factory.BeanFactoryAware#
     *      setBeanFactory(org.springframework.beans.factory.BeanFactory)
     * @exception BeansException
     *                if failed to set factory
     */
    public final void setBeanFactory(final BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    /**
     * DO NOT use this method in the application classes. This is meant to be
     * used by the framework level classes ONLY!!!!
     * 
     * Finds from bean from the factory.
     * 
     * @param beanName
     *            Name of the bean
     * @return bean object
     */
    @Deprecated
    public static final Object getBean(final String beanName) {
    //  if(beanFactory==null) System.out.println("Bean factory is null");
        return beanFactory.getBean(beanName);
    }

    public final Object getBeanInstance(final String beanName) {
        return beanFactory.getBean(beanName);
    }
}
    

