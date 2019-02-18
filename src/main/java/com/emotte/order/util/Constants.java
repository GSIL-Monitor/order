package com.emotte.order.util;

import org.wildhorse.server.core.utils.ContextConstants.BaseConstants;

public class Constants extends BaseConstants{

//	  // �ɹ�
	  public static final java.lang.String SCUUESS = "00";
//	  // ʧ��
	  public static final java.lang.String FAIL = "01";
//	  // ������
//	  public static final java.lang.String NULL = "02";
//	  // ����δע��
//	  public static final java.lang.String NO_REG = "03";
//	  // ��Ȩ��
//	  public static final java.lang.String NO_AUTH = "04";
//	  // δ��½
//	  public static final java.lang.String NO_LOGIN = "05";
	  // ��Ȩ���ϴ���������
	  public static final java.lang.String NO_UPLOAD = "06";
	  // ����״̬�����ݴ�����δͨ��״̬���޷��ϴ���������
	  public static final java.lang.String NO_UPLOAD_STATUS = "07";
	  // ��Ȩ��ɾ����������
	  public static final java.lang.String NO_DELETE = "08";
	  // ����״̬�����ݴ�����δͨ��״̬���޷�ɾ����������
	  public static final java.lang.String NO_DELETE_STATUS = "09";
	  
	  public final static String EMAIL_HOST = "smtp.exmail.qq.com"; // ���������������
	  
	  public final static String EMAIL_FROM = "service@ttjiehuo.com"; // ���������� 
	  
	  public final static String EMAIL_USER_NAME = "service@ttjiehuo.com"; // �������û���
	  
	  public final static String EMAIL_PASSWORD = "ttjh503"; // ���������� 
	  
	  public final static String LOGIN_COOKIE_COOKIENAME = "manager";
	  
	  public final static String HAVE_AFTERSALE = "10";
	  
	  public final static String RET_FAILED = "-1";//错误信息 (例如没有排期)
	  public final static String RET_ERROR = "-2";//缺少参数(例如id不能为空)或程序错误(例如空指针)
	  
}
