package com.lmz.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * Html工具类，过滤标签 
 *
 */
public class HtmlUtils {
	//日志
	private static final Logger LOGGER=Logger.getLogger(HtmlUtils.class);
	
	/**
	 * 用于去掉富文本编辑框中加入的html标签，检索的时候，不需要包含标签信息
	 * 
	 * @param inputString 含html标签的检索字符串
	 * @return
	 */
	public static String htmlText(String inputString){
		String htmlStr=inputString;
		Pattern p_script;
		Pattern p_style;
		Pattern p_html;
		Pattern p_other;
		Matcher m_script;
		Matcher m_style;
		Matcher m_html;
		Matcher m_other;
		try {
			//定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> }
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
            //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> }
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
            String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式
            String regEx_other = "\\s*\n";

            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE); 
            m_script = p_script.matcher(htmlStr);
            //过滤script标签
            htmlStr = m_script.replaceAll("");

            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            //过滤style标签
            htmlStr = m_style.replaceAll(""); 

            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            //过滤html标签
            htmlStr = m_html.replaceAll(""); 

            p_other = Pattern.compile(regEx_other);
            m_other = p_other.matcher(htmlStr);
            //过滤掉其他字符
            htmlStr = m_other.replaceAll("");
        } catch (Exception e) {
            LOGGER.error("htmlText: " + e.getMessage());
        }
		return htmlStr;
	}
	
}
