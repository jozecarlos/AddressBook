package com.example.addressbook.ui;

import com.example.addressbook.AddressbookApplication;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.ui.Table;

public class PersonList extends Table {

	public PersonList(AddressbookApplication app) {
		setSizeFull();
		setSelectable(true);
		setImmediate(true);
		setNullSelectionAllowed(false);
		addListener((Property.ValueChangeListener)app);
		setContainerDataSource(app.getDataSource());
	}

}
