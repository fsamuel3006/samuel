<%@ page language="java" import="java.util.*,java.io.*" contentType="text/html;charset=Big5" %>
<%@ page import="javax.mail.*" %>
<%@ page import="javax.mail.internet.*" %>
<%@ page import="javax.activation.*" %>


<%!
	InternetAddress[] address = null ;
%>

<%

	String ch_name = "peter1";   //String ch_name = request.getParameter("ch_name");
	String passRandom = "111";   //String passRandom = request.getParameter("passRandom");
	
	
	String mailserver   = "140.115.236.9";
	String From         = "test@pchome.com.tw";
	String to           = "ixlogic@pchome.com.tw";  //String to = request.getParameter("email");
	String Subject      = "�z���K�X";
  String messageText  = "Hello! " + ch_name + " ���԰O���K�X: " + passRandom +"\n" +" (�w�g�ҥ�)";        

        boolean sessionDebug = false;

try {

  // �]�w�ҭn�Ϊ�Mail ���A���M�ҨϥΪ��ǰe��w
  java.util.Properties props = System.getProperties();
  props.put("mail.host",mailserver);
  props.put("mail.transport.protocol","smtp");
  
  // ���ͷs��Session �A��
  javax.mail.Session mailSession = javax.mail.Session.getDefaultInstance(props,null);
  mailSession.setDebug(sessionDebug);
	
  Message msg = new MimeMessage(mailSession);
  
  // �]�w�ǰe�l�󪺵o�H�H
  msg.setFrom(new InternetAddress(From));
  
  // �]�w�ǰe�l��ܦ��H�H���H�c
  address = InternetAddress.parse(to,false);
  msg.setRecipients(Message.RecipientType.TO, address);
  
  // �]�w�H�����D�D 
  msg.setSubject(Subject);
  // �]�w�e�H���ɶ�
  msg.setSentDate(new Date());
  
  // �]�w�ǰe�H��MIME Type
  msg.setText(messageText);
  
  // �e�H
  Transport.send(msg);

      //response.sendRedirect("emp_select.jsp?msg=Y");
    System.out.println("�ǰe���\!");
    out.println("<script >document.open('mail_ok.jsp', '' ,'height=110,width=390,left=200,top=120,resizable=no')</script>");	
}
    catch (MessagingException mex) {
      //response.sendRedirect("emp_select.jsp?msg=N");
    System.out.println("�ǰe����!");
    out.println("<script >document.open('mail_error.jsp', '' ,'height=110,width=390,left=200,top=120,resizable=no')</script>");  
      //mex.printStackTrace();
    }
 
%>