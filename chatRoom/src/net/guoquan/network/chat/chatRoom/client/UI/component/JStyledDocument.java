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
		 private int type = -1;//数据连接类型

		 AttributeSet myAttributeSet = null;
		 public JStyledDocument(int type)
		 {
		  this.type = type;
		 }
		 /**
		 *插入字符串
		 */
		 public void insertString(int offset,String str,AttributeSet a)
		 throws BadLocationException
		 {
		  this.myAttributeSet = a;
		  super.insertString(offset,str,a);
		  setSyntaxColor(offset,str.length());
		 }
		/**
		 *删除字符串
		 */
		 public void remove(int offs,int len)
		 throws BadLocationException
		 {
		  super.remove(offs,len);
		  setSyntaxColor(offs);
		 }
		 /**
		 *获取制定位置的字符
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
		 *从指定的位置开始，倒推到第一个遇到空格位置
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
		  *从指定的位置开始，顺推到第一个遇到空格位置
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
		  * 根据Postion，向前判断，向后判断，设置颜色,返回设置颜色末尾的位置
		 */
		 private int setSyntaxColor(int offset)
		 {
		  if(offset<0) return offset;//如果设置的位置不存在，可以不用考虑

		  if(myAttributeSet==null) return offset;//如果myAttributeSet为null,可以不用考虑

		  String ifSyntax = "";
		  
		  String before = getBeforeBlankString(offset-1);
		  String after = getAfterBlankString(offset);

		  Syntax = (before + after).trim();

		  int start = offset-before.length();

		  int tmp_len = ifSyntax.length();

		  if(start<0 || tmp_len<=0) return offset;//如果设置颜色的字符串为空，返回

		  //设置颜色
		 StyleConstants.setForeground((MutableAttributeSet)myAttributeSet,
		  SyntaxMgr.isSyntax(type,ifSyntax));

		 setCharacterAttributes(start,tmp_len,myAttributeSet,true);

		  return start + tmp_len;
		 }
		 /**
		  *根据一个范围，设置该范围内的的SyntaxColor
		 */
		 private int setSyntaxColor(int offset,int len)
		  throws BadLocationException
		  {
		   //如果范围不存在，不考虑
		   if(offset<0 || len<0) return offset;
		   int tmp_offset = offset;
		   while(tmp_offset<offset + len)
		   {
		    tmp_offset = setSyntaxColor(tmp_offset);
		    tmp_offset = getNextWordOffset(tmp_offset);
		   }
		   tmp_offset = setSyntaxColor(tmp_offset);//设置循环完后的最后一个单词
		   return tmp_offset;
		  }
		  /**
		   *根据Postion，获得下一个单词的开始点
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

		  if(rOffset!=offset)//设置间隔的颜色
		  {
		   //设置颜色
		  StyleConstants.setForeground((MutableAttributeSet)myAttributeSet,
		   SyntaxColorMgr.getSpaceColor());
		  setCharacterAttributes(offset,rOffset-offset,myAttributeSet,true);
		  }

		  return rOffset;
		 }

}
