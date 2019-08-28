package com.chenyan.springshiro.web.bind.annotation;

import com.chenyan.springshiro.Constants;

/**  
* <p>绑定当前登陆的用户</p>  
* @author chenyan  
* @date 2019年8月28日  
*/
public @interface CurrentUser {

	/**
	 * 当前用户在request中的名字
	 * @return
	 */
	String value() default Constants.CURRENT_USER ;
}
