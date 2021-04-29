package com.udemy.docweba.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.udemy.docweba.entities.Documenta;
import com.udemy.docweba.repo.DocumentaRepo;
import com.udemy.docweba.service.DocumentaService;
import com.udemy.docweba.util.EmailUtil;

@Controller
public class DocumentaController {
	
	@Autowired
	private DocumentaService documentaService;
	
	@Autowired
	private DocumentaRepo documentaRepo;
	@Autowired
	private EmailUtil emailUtil;

	@RequestMapping("/displayUpload")
	public String displayUpload(ModelMap modelMap) {

		List<Documenta> documents = documentaRepo.findAll();
		System.out.println(documents.size());
		modelMap.addAttribute("documents", documents);
		System.out.println("Test");
		return "docUpload";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String uploadDocument(@RequestParam("document") MultipartFile multipartFile, @RequestParam("id") long id,
			ModelMap modelMap) {

		Documenta document = new Documenta();
		document.setId(id);
		document.setName(multipartFile.getOriginalFilename());
		try {
			document.setData(multipartFile.getBytes());
		} catch (IOException e) {

			e.printStackTrace();
		}
		emailUtil.sendEmail("johnqatwork21@gmail.com", "Document Saved",
				"Document with id of " + document.getName() + " has been saved");
		 String msg = "Document with name of "+ document.getName()+ " has been uploaded";
		 modelMap.addAttribute("msg"	, msg);
		documentaRepo.save(document);
		List<Documenta> documents = documentaRepo.findAll();
		System.out.println(documents.size());
		modelMap.addAttribute("documents", documents);
		return "docUpload";
	}
	
	
	 @RequestMapping("/download")
	public StreamingResponseBody download(@RequestParam("id") long id, HttpServletResponse response,MultipartFile multipartFile) {
		Documenta document = documentaRepo.findById(id).get();
		byte[] data = document.getData();
		response.setHeader("Content-Disposition", "attachment;filename="+document.getName()+"");
		
		emailUtil.sendEmail("johnqatwork21@gmail.com", "Document downloaded", "Document with name of "+ document.getName()+" has been downloaded");
		return outputStream -> {
			outputStream.write(data);
		};
	}
	 

	 @RequestMapping("/deleteDocument")
	 public String deleteDocument(@RequestParam("id")long id, ModelMap modelMap) {
		 Documenta documentaById = documentaService.getDocumentaById(id);
		 documentaService.deleteDocumentById(documentaById);
		 String msg = "Document with id of "+ documentaById.getId()+ " has been deleted";
		 modelMap.addAttribute("msg"	, msg);
		 emailUtil.sendEmail("johnqatwork21@gmail.com", "Document deleted", "Document with id of "+documentaById.getId()+" has been deleted");
		 List<Documenta> documents = documentaRepo.findAll();
			
			modelMap.addAttribute("documents", documents);
		 
		 return "docUpload";
	 }
	 
	 /*
	  @RequestMapping("/deleteVendor")
	public String deleteVendor(@RequestParam("id") int id, ModelMap modelMap) {
		Vendors vendorid = service.getVendorById(id);
		service.deleteVendors(vendorid);
		String msg = "Vendor with id of "+ vendorid.getId()+ " has been deleted";
		modelMap.addAttribute("msg"	, msg);
		List<Vendors> allVendors = service.getAllVendors();
		modelMap.addAttribute("vendors",allVendors);
		emailUtil.sendEmail("johnqatwork21@gmail.com", "Vendor Deleted", "Vendor with id of "+ vendorid.getId()+" has been deleted");
		return "displayVendors";
		
	}
	  */
	 
	 
	 
	 
	 
}
