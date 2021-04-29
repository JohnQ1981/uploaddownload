package com.udemy.docweba.service;

import com.udemy.docweba.entities.Documenta;

public interface DocumentaService {
	
	
	void deleteDocumentById(Documenta document);
	Documenta getDocumentaById(long id);

}
