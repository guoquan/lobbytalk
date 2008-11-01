package net.guoquan.network.chat.chatRoom.client.UI.component;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyleConstants;

public class JStyledDocument extends DefaultStyledDocument {

	/**
	 * 
	 */
	private static final long serialVersionUID = -905332537780081797L;
		 private int type = -1;//������������

		 AttributeSet myAttributeSet = null;
		 public JStyledDocument(int type)
		 {
		  this.type = type;
		 }
		 /**
		 *�����ַ���
		 */
		 public void insertString(int offset,String str,AttributeSet a)
		 throws BadLocationException
		 {
		  this.myAttributeSet = a;
		  super.insertString(offset,str,a);
		  setSyntaxColor(offset,str.length());
		 }
		/**
		 *ɾ���ַ���
		 */
		 public void remove(int offs,int len)
		 throws BadLocationException
		 {
		  super.remove(offs,len);
		  setSyntaxColor(offs);
		 }
		 /**
		 *��ȡ�ƶ�λ�õ��ַ�
		 */
		 private String getPositionChar(int offset)
		 {
		  String str = "";
		  try
		  {
		   str = getText(offset,1);
		  }
		  catch(BadLocationException ex)
		  {
		   //ex.printStackTrace(System.out);
		  }
		  return str;
		 }
		/**
		 *��ָ����λ�ÿ�ʼ�����Ƶ���һ�������ո�λ��
		 */
		 private String getBeforeBlankString(int offset)
		 {
		  String str = "";
		  if(offset<0) return "";
		 
		  str = getPositionChar(offset);
		  if(SyntaxMgr.isSpaceChar(str))
		   return "";

		  String r = getBeforeBlankString(offset-1);
		  return r + str;
		 }
		 /**
		  *��ָ����λ�ÿ�ʼ��˳�Ƶ���һ�������ո�λ��
		 */
		 private String getAfterBlankString(int offset)
		 {
		  String str = "";
		  if(offset>getLength()) return "";
		   str = getPositionChar(offset);
		   if(SyntaxMgr.isSpaceChar(str))
		    return "";
		   String r = getAfterBlankString(offset+1);
		   return str + r;
		 }  
		 /**
		  * ����Postion����ǰ�жϣ�����жϣ�������ɫ,����������ɫĩβ��λ��
		 */
		 private int setSyntaxColor(int offset)
		 {
		  if(offset<0) return offset;//������õ�λ�ò����ڣ����Բ��ÿ���

		  if(myAttributeSet==null) return offset;//���myAttributeSetΪnull,���Բ��ÿ���

		  String ifSyntax = "";
		  
		  String before = getBeforeBlankString(offset-1);
		  String after = getAfterBlankString(offset);

		  Syntax = (before + after).trim();

		  int start = offset-before.length();

		  int tmp_len = ifSyntax.length();

		  if(start<0 || tmp_len<=0) return offset;//���������ɫ���ַ���Ϊ�գ�����

		  //������ɫ
		 StyleConstants.setForeground((MutableAttributeSet)myAttributeSet,
		  SyntaxMgr.isSyntax(type,ifSyntax));

		 setCharacterAttributes(start,tmp_len,myAttributeSet,true);

		  return start + tmp_len;
		 }
		 /**
		  *����һ����Χ�����ø÷�Χ�ڵĵ�SyntaxColor
		 */
		 private int setSyntaxColor(int offset,int len)
		  throws BadLocationException
		  {
		   //�����Χ�����ڣ�������
		   if(offset<0 || len<0) return offset;
		   int tmp_offset = offset;
		   while(tmp_offset<offset + len)
		   {
		    tmp_offset = setSyntaxColor(tmp_offset);
		    tmp_offset = getNextWordOffset(tmp_offset);
		   }
		   tmp_offset = setSyntaxColor(tmp_offset);//����ѭ���������һ������
		   return tmp_offset;
		  }
		  /**
		   *����Postion�������һ�����ʵĿ�ʼ��
		  */
		  private int getNextWordOffset(int offset)
		  {
		   int rOffset = offset;
		   int textlength = getLength();

		   while(rOffset<=textlength && offset>=0)
		   {
		    String str = getPositionChar(rOffset);
		    if(!SyntaxMgr.isSpaceChar(str))
		    {
		     break;
		    }
		    rOffset+=1;
		   }

		  if(rOffset!=offset)//���ü������ɫ
		  {
		   //������ɫ
		  StyleConstants.setForeground((MutableAttributeSet)myAttributeSet,
		   SyntaxColorMgr.getSpaceColor());
		  setCharacterAttributes(offset,rOffset-offset,myAttributeSet,true);
		  }

		  return rOffset;
		 }

}
