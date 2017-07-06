//package com.prcmind.listener;
//
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//import javax.servlet.http.HttpSession;
//import javax.servlet.http.HttpSessionAttributeListener;
//import javax.servlet.http.HttpSessionBindingEvent;
//
//import com.prcmind.utils.WebConstants;
//import com.prcmind.view.BaseUserView;
//
//
//public class SessionAttributeListener implements HttpSessionAttributeListener {
//
//     public static Map<String, HttpSession> sessionMap=new ConcurrentHashMap<String, HttpSession>();  
//     @Override 
//     public void attributeAdded(HttpSessionBindingEvent arg0) { 
//         if(arg0.getName().equals(WebConstants.CURRENT_USER)){
//            // Enumeration session = arg0.getSession().getServletContext().getAttributeNames();
//             HttpSession session = arg0.getSession();
//        //     ServletContext application = session.getServletContext();
//             BaseUserView user = (BaseUserView) session.getAttribute(WebConstants.CURRENT_USER);
//             if(sessionMap.containsKey(user.getAccountId())){//踢掉前一次登录    
//                 try {
//                    HttpSession session2=sessionMap.remove(user.getAccountId());    
//                     session2.invalidate();
//                } catch (Exception e) {
//                }   
//                // application.removeAttribute(user.getAccount());
//            }   
//            sessionMap.put(user.getAccountId(), session);  
//        //    application.setAttribute(user.getAccount(), user);
//         }
//        
//     }
//    @Override
//    public void attributeRemoved(HttpSessionBindingEvent arg0) {
//    }
//    @Override
//    public void attributeReplaced(HttpSessionBindingEvent se) {
//        // TODO Auto-generated method stub
//        
//    }
//
//}