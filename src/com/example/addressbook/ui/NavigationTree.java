package com.example.addressbook.ui;

import com.example.addressbook.AddressbookApplication;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Tree;

public class NavigationTree extends Tree {
	
	public static final Object SHOW_ALL = "Show All";
	public static final Object SEARCH = "Search";
	
	public NavigationTree(AddressbookApplication app) {
		super();
		
		this.addItem(SHOW_ALL);
		this.addItem(SEARCH);
		
		setSelectable(true);
		setNullSelectionAllowed(false);
		addListener((ItemClickListener) app);
	}

}
