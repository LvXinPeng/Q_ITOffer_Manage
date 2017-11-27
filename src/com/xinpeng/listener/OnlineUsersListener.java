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
    	// �жϼ��������Ƿ�Ϊ��¼�û���Ϣ�ĻỰ������
    	if("SESSION_USER".equals(event.getName())){
    		// ��ȡ�Ự������ֵ����ǰ��¼���û�����
    		User user = (User)event.getValue();
    		// ��ȡӦ�������Ķ���
    		ServletContext application = event.getSession().getServletContext();
    		// ��ȡ�����Ӧ���������е������û���Ϣ�б�
    		Map<String,User> onlineUserMap = (Map<String,User>)application.getAttribute("ONLINE_USER");
    		if(onlineUserMap == null)
    			onlineUserMap = new Hashtable<String,User>();
    		// ����ǰ��¼���û��������Ӧ�������ԣ������û���Ϣ�б�
    		onlineUserMap.put(user.getUsername(), user);
    		application.setAttribute("ONLINE_USER", onlineUserMap);
    	}
    }

    public void attributeRemoved(HttpSessionBindingEvent arg0)  { 
    }

    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
    }
	
}
