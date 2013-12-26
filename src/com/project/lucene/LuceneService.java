package com.project.lucene;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class LuceneService {
	
	
	public void createIndex() {
		Directory directory = null;
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_44, new IKAnalyzer());
		indexWriterConfig.setOpenMode(OpenMode.CREATE_OR_APPEND);
		try {
			IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
			Document doc = new Document();
			doc.add(null);
			indexWriter.addDocument(doc);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void updateIndex() {
		
	}
	
	public void deleteIndex() {
		
	}
	
	public void findIndex() {
		
	}

}
