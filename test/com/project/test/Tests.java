package com.project.test;



import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttributeImpl;
import org.apache.lucene.util.AttributeImpl;
import org.apache.lucene.util.Version;

/**
 * @author pst
 */
public class Tests {

	public static void main(String[] args) throws Exception {
		String text = "IK Analyzer是一个开源的，基于java语言开发的轻量级的中文分词工具包。从2006年12月推出1.0版开始， "
			+ "IKAnalyzer已经推出了3个大版本。最初，它是以开源项目Luence为应用主体的，结合词典分词和文法分析算法的中文分词组件。"
			+ "新版本的IK Analyzer 3.0则发展为面向Java的公用分词组件，独立于Lucene项目，同时提供了对Lucene的默认优化实现。 ";
		asd(text);

//		IKTokenizer s = new IKTokenizer(new StringReader(text), true);
//		s.
	}

	private static void asd(String text) throws IOException {
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_44);

		StringReader reader = new StringReader(text);
	
		long startTime = System.currentTimeMillis(); // 开始时间
		TokenStream ts = analyzer.tokenStream("name", reader);
		
		while (ts.incrementToken()) {
			Iterator<AttributeImpl> attributeImplsIterator = ts.getAttributeImplsIterator();
			while (attributeImplsIterator.hasNext()) {
				AttributeImpl next = attributeImplsIterator.next();
				if (next instanceof CharTermAttributeImpl) {
					CharTermAttributeImpl c = (CharTermAttributeImpl)next;
					System.out.println(c);
					break;
				}
			}
		}
	
		long endTime = System.currentTimeMillis(); // 结束时间
		System.out.println("分词耗时" + new Float((endTime - startTime)) / 1000
				+ "秒!");
	}
}
