package com.udemy.docweba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.docweba.entities.Documenta;
import com.udemy.docweba.repo.DocumentaRepo;

@Service
public class DocumentaServiceImpl implements DocumentaService {
	
	@Autowired
	private DocumentaRepo documentaRepo;

	@Override
	public void deleteDocumentById(Documenta document) {
		documentaRepo.delete(document);

	}

	@Override
	public Documenta getDocumentaById(long id) {
		return documentaRepo.findById(id).get();
		
	}

}
