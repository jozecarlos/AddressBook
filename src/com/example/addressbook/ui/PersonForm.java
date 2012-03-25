package com.example.addressbook.ui;


import java.util.Arrays;
import java.util.List;

import com.example.addressbook.AddressbookApplication;
import com.example.addressbook.data.Person;
import com.example.addressbook.data.PersonContainer;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;

public class PersonForm extends Form implements ClickListener {

	private Button save = new Button("Save", (ClickListener) this);
	private Button cancel = new Button("Cancel", (ClickListener) this);
	private Button edit = new Button("Edit", (ClickListener) this);
	private AddressbookApplication app;

	private boolean newContactMode = false;
	private Person newPerson = null;




	public PersonForm(AddressbookApplication app) {

		this.app = app;

		addField("First Name", new TextField("First Name"));
		addField("Last Name", new TextField("Last Name"));
		HorizontalLayout footer = new HorizontalLayout();
		footer.setSpacing(true);
		footer.addComponent(save);
		footer.addComponent(cancel);
		footer.addComponent(edit);
		setFooter(footer);
	}


	@Override
	public void buttonClick(ClickEvent event) {

		Button source = event.getButton();
		if (source == save) {
			/* If the given input is not valid there is no point in continuing */
			if (!isValid()) {
				return;
			}
			commit();
			if (newContactMode) {
				/* We need to add the new person to the container */
				Item addedItem = app.getDataSource().addItem(newPerson);
				/*
				 * We must update the form to use the Item from our datasource
				 * as we are now in edit mode
				 */
				setItemDataSource(addedItem);
				newContactMode = false;
			}
			setReadOnly(true);
		} else if (source == cancel) {
			if (newContactMode) {
				newContactMode = false;
				setItemDataSource(null);
			} else {
				discard();
			}
			setReadOnly(true);
		} else if (source == edit) {
			setReadOnly(false);
		}

	}


	@Override
	public void setItemDataSource(Item newDataSource) {

		newContactMode = false;

		if (newDataSource != null) {

			List<Object> orderedProperties = Arrays.asList(PersonContainer.NATURAL_COL_ORDER);
			super.setItemDataSource(newDataSource, orderedProperties);
			setReadOnly(true);
			getFooter().setVisible(true);
		} 
		else {

			super.setItemDataSource(null);
			getFooter().setVisible(false);
		}

	}


	@Override
	public void setReadOnly(boolean readOnly) {
		super.setReadOnly(readOnly);
		save.setVisible(!readOnly);
		cancel.setVisible(!readOnly);
		edit.setVisible(readOnly);

	}


	public void addContact() {

		newPerson = new Person();
		setItemDataSource(new BeanItem<Person>(newPerson));
		newContactMode = true;
		setReadOnly(false);

	}


}
