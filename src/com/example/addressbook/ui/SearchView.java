package com.example.addressbook.ui;

import com.example.addressbook.AddressbookApplication;
import com.example.addressbook.data.PersonContainer;
import com.example.addressbook.data.SearchFilter;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;

public class SearchView extends Panel {


	private TextField tf;
	private NativeSelect fieldToSearch;
	private CheckBox saveSearch;
	private TextField searchName;
	private AddressbookApplication app;

	public SearchView(final AddressbookApplication app) {

		this.app = app;

		FormLayout formLayout = new FormLayout();
		setContent(formLayout);

		initComponente();
		initFieldToSearch();

		setCaption("Search contacts");
		setSizeFull();
	}

	private void initComponente(){

		tf = new TextField("Search term");
		fieldToSearch = new NativeSelect("Field to Search");
		searchName = new TextField("Search name");
		saveSearch = new CheckBox("Save search");
		saveSearch.setValue(true);
		saveSearch.addListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				searchName.setVisible(event.getButton().booleanValue());
			}
		});


		Button search = new Button("Search");
		search.addListener(new Button.ClickListener() {

			public void buttonClick(ClickEvent event) {
				performSearch();
			}

			private void performSearch() {

				String searchTerm = (String) tf.getValue();
				
				SearchFilter searchFilter = new SearchFilter(fieldToSearch.getValue(),
						searchTerm, (String) searchName.getValue());
				
				if (saveSearch.booleanValue()) {
					app.saveSearch(searchFilter);
				}
				
				app.search(searchFilter);

			}
		});


		this.addComponent(fieldToSearch);
		this.addComponent(tf);
		this.addComponent(saveSearch);
		this.addComponent(searchName);
		this.addComponent(tf);
		this.addComponent(search);
	}

	private void initFieldToSearch(){

		for (int i = 0; i < PersonContainer.NATURAL_COL_ORDER.length; i++) {

			fieldToSearch.addItem(PersonContainer.NATURAL_COL_ORDER[i]);
			fieldToSearch.setItemCaption(PersonContainer.NATURAL_COL_ORDER[i],PersonContainer.COL_HEADERS_ENGLISH[i]);
		}

		fieldToSearch.setValue("lastName");
		fieldToSearch.setNullSelectionAllowed(false);


	}

}
