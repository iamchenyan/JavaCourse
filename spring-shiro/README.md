**看《跟我学Shiro》的Demo**

**spring-shiro**

* **spring+springmvc+JdbcTemplate + shiro**
* **用户：admin/123456**

**其他要点：**

* **自定义注解 自定义注解解析器 `@CurrentUser` **

  ```xml
  <mvc:annotation-driven>
       	<mvc:argument-resolvers>
       		<bean 		class="com.chenyan.springshiro.web.bind.method.CurrentUserMethodArgumentResolver" />
       	</mvc:argument-resolvers>
       </mvc:annotation-driven> 
  ```

* **Spring 配置 —— spring-config.xml**

  ​	定义 `context:component-scan`来扫描 web 层组件、dataSource(数据源)、事务管理器及事务切面等；

* **Spring 配置 —— spring-config-cache.xml**

  ​	定义了spring通用 cache，使用 ehcache 实现 ；

* **Spring 配置 —— spring-config-shiro.xml**

  ​	定义 shiro相关组件：

  ```xml
  <!-- Realm实现 -->
  <bean id="userRealm" class="com.chenyan.springshiro.realm.UserRealm">
      <property name="credentialsMatcher" ref="credentialsMatcher"/>
      <property name="cachingEnabled" value="false"/>
  </bean>
  ```

  ​	userRealm 组件禁用了 cache ，否则需要在修改如资源、角色等信息时清理掉缓存。也可实现自己的 cache切面；

  ```xml
  <bean id="sysUserFilter" class="com.chenyan.springshiro.web.shiro.filter.SysUserFilter"/>
  ```

  ​	sysUserFilter 用于根据当前登录用户身份获取 User 信息放入 request ，通过 request 获取 User ;

  ```xml
  <!-- Shiro的Web过滤器 -->
  <bean id="shiroFilter" class="org.apache.shiro.spring.web.ShiroFilterFactoryBean">
      <property name="securityManager" ref="securityManager" />
      <property name="loginUrl" value="/login"/>
      <property name="filters">
          <util:map>
              <!-- 基于表单的身份验证过滤器 -->
              <entry key="authc" value-ref="formAuthenticationFilter" />
              <!-- 根据当前登录用户身份获取 User 信息放入 request -->
              <entry key="sysUser" value-ref="sysUserFilter" />
          </util:map>
      </property>
      <property name="filterChainDefinitions">
          <value>
              <!--为 login 路径定义拦截，由FormAuthenticationFilter  -->
              /login = authc
              <!--定义注销路径-->
              /logout = logout
              /authenticated = authc
              /** = user,sysUser
          </value>
      </property>
  </bean>
  ```

  ​	shiroFilter 的定义

* **SpringMVC 配置 —— spring-mvc.xml**

  ​	定义 springmvc 相关组件：

  ```xml
  <!-- 注册@CurrentUser 参数解析 -->
  <mvc:annotation-driven>
      <mvc:argument-resolvers>
          <bean class="com.chenyan.springshiro.web.bind.method.CurrentUserMethodArgumentResolver" />
      </mvc:argument-resolvers>
  </mvc:annotation-driven>  
  ```

  ​		此处注册了一个 `@CurrentUser`  参数解析器。

* **SpringMVC 配置 —— spring-mvc-shiro.xml**

  ​	定义了 springMVC 相关组件：

  ```xml
  <!-- 定义aop切面 用于代理如@RequiresPermissions注解控制器，进行权限控制 -->
  <aop:config proxy-target-class="true"></aop:config>
  <bean class="org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor">
      <property name="securityManager" ref="securityManager"/>
  </bean>
  ```

  ​		定义 aop 切面，用于代理如  `@RequiresPermissions` 注解的控制器，进行权限控制

* **web.xml 配置文件**

  ​	定义 spring ROOT 上下文加载器、ShiroFilter、及 springMVC 拦截器。**顺序 先 spring-config.xml 再 spring-mvc-config.xml 。**

* **JSP 页面**

```jsp
<shiro:hasPermission name="user:create"> 
 <a href="${pageContext.request.contextPath}/user/create">用户新增</a><br/> 
</shiro:hasPermission>
```

​	使用 shiro 标签进行权限控制。