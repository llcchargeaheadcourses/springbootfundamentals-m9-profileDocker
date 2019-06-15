package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.ProfileSpecificInterface;
import com.example.demo.model.Items;
import com.example.demo.service.ItemsService;

@Controller
@RequestMapping("/inventory")
public class ItemsController {
  
	@Autowired
	ItemsService its;
	
	@Value("${logging.file}")
	private String loggingFile;
	
	@Value("${main.greeting}")
	private String mainGreeting;
	
	@Autowired
	ProfileSpecificInterface profileClass;
	
	@RequestMapping(value= {"/all","/"})
	public String getAll(Model model) {
		List<Items> items = its.getAll();
		model.addAttribute("items", items);
		String env = profileClass.getEnv();
		String heading = mainGreeting+" Env:"+env+" Log file:"+loggingFile;
		model.addAttribute("heading", heading);
		return "itemsView";
	}
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		Optional<Items> i = its.get(id);
		model.addAttribute("item", i);
		model.addAttribute("heading", "Edit Item");
		return "editView";
	}
	@RequestMapping("/update")
	public String edit(Model model, Items item) {
		its.update(item);
		List<Items> items = its.getAll();
		model.addAttribute("items", items);
		model.addAttribute("heading", "Item Inventory");
		return "redirect:/inventory/all";
	}
	@RequestMapping("/add")
	public String add(Model model) {
		Items i = new Items();
		model.addAttribute("item", i);
		model.addAttribute("heading", "Add Item");
		return "editView";
	}
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id, Model model) {
		Optional<Items> i = its.get(id);
		its.delete(i.get());
		List<Items> items = its.getAll();
		model.addAttribute("items", items);
		model.addAttribute("heading", "Item Inventory");
		return "redirect:/inventory/all";
	}
}
