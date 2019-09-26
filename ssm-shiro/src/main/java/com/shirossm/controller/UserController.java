package com.shirossm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shirossm.pojo.Result;
import com.shirossm.pojo.Role;
import com.shirossm.pojo.User;
import com.shirossm.service.UserService;

/**  
* <p>Title: UserController</p>  
* @author chenyan  
* @date 2019年9月23日  
*/
@Controller
@SuppressWarnings("all")
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService ;
	
	//@Autowired
	//private RoleService roleService ;
	
	/**
	 * 查询用户列表
	 * @param model
	 * @return
	 */
	@RequestMapping("/findAll")
	public String findAllUser(Model model) {
		model.addAttribute("userList" ,userService.findAll()) ;
		return "page/user" ;
	}
	
	/**
	 * 创建用户
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/create")
	@RequiresRoles(value= {"admin" ,"personnel-resource"} ,logical = Logical.OR)
	public Result create(@RequestBody User user) {
		try {
			userService.create(user) ;
			return new Result(true, "创建用户成功");
		} catch(Exception e) {
			e.printStackTrace();
            return new Result(false, "发生未知错误");
		}
	}
	
	@ResponseBody
	@RequestMapping("/update")
	@RequiresRoles(value= {"admin" ,"personnel-resource"} ,logical = Logical.OR)
	public Result update(@RequestBody User user) {
		try {
			userService.update(user) ;
			return new Result(true ,"更新成功") ;
		} catch (Exception e) {
			e.printStackTrace() ;
			return new Result(false ,"发生未知错误") ;
		}
		
	}
	
	/**
     * 删除用户信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    @RequiresRoles(value = {"admin", "personnel-resource"}, logical = Logical.OR)
    public Result delete(@RequestParam("id") Long id){
        try{
            userService.delete(id);
            return new Result(true,"删除用户数据成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"发生未知错误");
        }
    }
	
    /**
     * 根据用户名查找其角色
     *
     * @param username
     * @return
     */
    @ResponseBody
    @RequestMapping("/findRoles")
    @RequiresRoles(value = {"admin"}, logical = Logical.OR)
    @RequiresPermissions(value = {"role:view", "role:*"}, logical = Logical.OR)
    public List<Role> findRoles(String username) {
        return userService.findRoles(username);
    }
    
    /**
     * 更新用户角色信息
     * 		id				当前用户id
     * 		ids			当前用户的角色的id集合
     * 		parents		当前结点是否是父节点
     * @param dataMap	参数的 map集合
     * @return
     * 为什么用 Map接收？
     * 		如果想要传递对象(如数组)参数，ajax就必须发送post请求，而post请求传递对象参数就要用JSON.stringify()格式化数据为JSON字符串才能传递 ；
     * 	一旦使用了JSON.stringify()格式化数据，传递的就是JSON字符串后端必须使用String类型接收，
     * 而我们传递的数据中包含了普通变量、数组等多种数据，所以使用使用Map接收，并指定泛型是String类型
     * 
     *前端传递进来的参数在Map中封装的数据结构类似：
     * {0:{key: id, value: {...}}, 1:{key: ids, value: {...}}, 2:{key:pids, value: {...}}, 3:{key:parents, value: {...}}}
     */
    @ResponseBody
    @RequestMapping(value="/updateUserRoles" ,method = RequestMethod.POST)
    public Result updateUserRoles(@RequestBody Map<String ,Object> dataMap) {
    	try {
    		Long id = Long.valueOf((String)dataMap.get("id")) ;//当前用户id
    		ArrayList ids = (ArrayList)dataMap.get("ids") ;//当前用户的角色节点的id集合
    		ArrayList parents = (ArrayList)dataMap.get("parents") ;//当前用户角色是否是父节点判断的集合
    		ArrayList names = (ArrayList)dataMap.get("names") ;//当前用户角色的名称集合
    		
    		//更新用户角色(维护 用户-角色表) 前端数据：用户id ；被选中的角色id
    		//ZTree实体类JSON数据，先删除用户所有关联的角色id，再依次关联此用户当前调整的角色信息
    		userService.deleteAllUserRoles(id) ;
    		
    		String role_id = "" ; //初始化User的 role_id列数据
    		// ids 和parents长度相等
    		if(ids.size() == 1) {
    			//说明只有一个节点，且被选中，证明这个角色没有子节点，与用户关联
    			userService.correlationRoles(id ,Long.valueOf(String.valueOf(ids.get(0)))) ;
    			role_id = "["+String.valueOf(names.get(0))+"]" ;
    		} else {
    			for(int i = 0 ;i < ids.size() ;i++) {
    				if(!(boolean)parents.get(i)) {
    					userService.correlationRoles(id, Long.valueOf(String.valueOf(ids.get(i)))) ;
    					
    					//更新user表中role_id的数据
    					role_id += "[" + String.valueOf(names.get(i)) + "]";
    				}
    			}
    		}
    		//单独更新user表中role_id列数据
            User user = new User();
            user.setRoleId(role_id);
            user.setId(id);
            user.setLocked(null);
            userService.update(user);

            System.out.println(role_id);
            System.out.println(dataMap);
    		return new Result(true ,"更新用户角色信息成功") ;
    	} catch (Exception e) {
    		e.printStackTrace() ;
    		return new Result(false ,"发生未知错误") ;
    	}
    	
    }
    
    
    
    
    
    
    
	
}
