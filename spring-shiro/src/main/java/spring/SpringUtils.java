/**
 * <p>Title: SpringUtils.java</p>
 * @author chenyan
 * @date 2019年8月28日
 */
package spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**  
* <p>Title: SpringUtils</p>  
* @author chenyan  
* @date 2019年8月28日  
*/
public final class SpringUtils implements BeanFactoryPostProcessor {

	// Spring 应用上下文环境
	private static ConfigurableListableBeanFactory beanFactory ;
	
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		SpringUtils.beanFactory = beanFactory ;
	}
	
	/**
	 * 获取bean对象
	 * @param name
	 * @return Object 一个一所给名字注册的bean的实例
	 * @throws org.springframework.beans.BeansException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) throws BeansException{
		return (T) beanFactory.getBean(name) ;
	}
	
	/**
	 * 获取类型为requiredType的对象
	 * @param clazz
	 * @return
	 * @throws BeansException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> clazz) throws BeansException{
		T result = (T)beanFactory.getBean(clazz) ;
		return result ;
	}
	
	/**
	 * 如果BeanFactory包含一个与所给名称匹配的bean，则返回true
	 * @param name
	 * @return boolean
	 */
	public static boolean containsBean(String name) {
		return beanFactory.containsBean(name) ;
	}
	
	/**
	 * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
	 * @param name
	 * @return boolean
	 * @throws NoSuchBeanDefinitionException
	 */
	public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException{
		return beanFactory.isSingleton(name) ;
	}
	
	/**
	 * @param name
	 * @return Class 注册对象的类型
	 * @throws NoSuchBeanDefinitionException
	 */
	public static Class<?> getType(String name) throws NoSuchBeanDefinitionException{
		return beanFactory.getType(name) ;
	}
	
	/**
	 * 如果给定的bean名字在bean定义中有别名，则返回这些别名
	 * @param name
	 * @return
	 * @throws NoSuchBeanDefinitionException
	 */
	public static String[] getAliases(String name) throws NoSuchBeanDefinitionException{
		return beanFactory.getAliases(name) ;
	}

}
