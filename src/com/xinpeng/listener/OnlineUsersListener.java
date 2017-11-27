package com.xinpeng.listener;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.xinpeng.model.User;

/**
 * Application Lifecycle Listener implementation class OnlineUsersListener
 *
 */
@WebListener
public class OnlineUsersListener implements HttpSessionAttributeListener {

    public OnlineUsersListener() {
    }

    public void attributeAdded(HttpSessionBindingEvent event)  { 
    	// 判断监听到的是否为记录用户信息的会话域属性
    	if("SESSION_USER".equals(event.getName())){
    		// 获取会话域属性值：当前登录的用户对象
    		User user = (User)event.getValue();
    		// 获取应用上下文对象
    		ServletContext application = event.getSession().getServletContext();
    		// 获取存放在应用域属性中的在线用户信息列表
    		Map<String,User> onlineUserMap = (Map<String,User>)application.getAttribute("ONLINE_USER");
    		if(onlineUserMap == null)
    			onlineUserMap = new Hashtable<String,User>();
    		// 将当前登录的用户对象存入应用域属性：在线用户信息列表
    		onlineUserMap.put(user.getUsername(), user);
    		application.setAttribute("ONLINE_USER", onlineUserMap);
    	}
    }

    public void attributeRemoved(HttpSessionBindingEvent arg0)  { 
    }

    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
    }
	
}
