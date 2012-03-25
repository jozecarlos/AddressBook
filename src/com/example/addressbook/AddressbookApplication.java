package com.example.addressbook;

import com.example.addressbook.data.PersonContainer;
import com.example.addressbook.ui.HelpWindow;
import com.example.addressbook.ui.ListView;
import com.example.addressbook.ui.NavigationTree;
import com.example.addressbook.ui.PersonForm;
import com.example.addressbook.ui.PersonList;
import com.example.addressbook.ui.SearchView;
import com.example.addressbook.ui.SharingOptions;
import com.vaadin.Application;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;

public class AddressbookApplication extends Application implements ItemClickListener, Button.ClickListener, Property.ValueChangeListener {

	private Button newContact;
	private Button search;
	private Button share;
	private Button help;
	private SplitPanel horizontalSplit;
	private NavigationTree tree;
	private ListView listView;
	private HelpWindow helpWindow =null;
	private SharingOptions sharingOptions = null;
	private PersonList personList = null;
	private PersonForm personForm = null;
	private SearchView searchView = null;
	private PersonContainer datasource;


	@Override
	public void init() {

		buildMainLayout();
		setMainContent(getListView());
		//getMainWindow().addWindow(getSharingOptions());
	}

	private void initButtonComponente(){

		newContact = new Button("Add Contact");
		help = new Button("Help");
		search = new Button("Help");
		share = new Button("Share");
		horizontalSplit = new SplitPanel(SplitPanel.ORIENTATION_HORIZONTAL);
		tree = new NavigationTree(this);

		horizontalSplit.setFirstComponent(tree);

		datasource = PersonContainer.createWithTestData();

		search.addListener((Button.ClickListener)this);
		help.addListener((Button.ClickListener)this);
		share.addListener((Button.ClickListener)this);
	}

	private void buildMainLayout(){

		setTheme("reindeer");
		initButtonComponente();
		setMainWindow(new Window("Address Book Demo Application"));

		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		layout.addComponent(createToolBar());
		layout.addComponent(horizontalSplit);

		layout.setExpandRatio(horizontalSplit, 1);
		horizontalSplit.setSplitPosition(200, SplitPanel.UNITS_PIXELS);

		getMainWindow().setContent(layout);
	}

	private HorizontalLayout createToolBar(){

		HorizontalLayout lo = new HorizontalLayout();
		lo.addComponent(newContact);
		lo.addComponent(search);
		lo.addComponent(share);
		lo.addComponent(help);

		return lo;
	}


	private void setMainContent(Component component){
		horizontalSplit.setSecondComponent(component);
	}

	private ListView getListView(){
		if (listView == null) {

			listView = new ListView();
			personList = new PersonList(this);
			personForm = new PersonForm(this);
			listView = new ListView(personList, personForm);
		}
		return listView;
	}

	public void valueChange(ValueChangeEvent event) {
		Property property = event.getProperty();

		if (property == personList) {

			Item item = personList.getItem(personList.getValue());

			if (item != personForm.getItemDataSource()) {
				personForm.setItemDataSource(item);
			}
		}
	}


	private SearchView getSearchView(){

		if(searchView == null){
			searchView = new SearchView(this);
		}
		return searchView;
	}

	private HelpWindow getHelpWindow(){

		if(helpWindow == null){
			helpWindow = new HelpWindow();
		}

		return helpWindow;
	}

	private SharingOptions getSharingOptions(){

		if(sharingOptions == null){
			sharingOptions = new SharingOptions();
		}
		return sharingOptions;
	}

	public PersonContainer getDataSource(){
		return datasource;
	}

	@Override
	public void itemClick(ItemClickEvent event) {

		if(event.getSource() == tree) {

			Object itemId = event.getItemId();

			if (itemId != null) {

				if (NavigationTree.SHOW_ALL.equals(itemId)) {

					setMainContent(getListView());
				} 
				else if (NavigationTree.SEARCH.equals(itemId)) {

					setMainContent(getSearchView());
				}
			}
		}
	}

	@Override
	public void buttonClick(ClickEvent event) {

		final Button source = event.getButton();

		if (source == search) {
			setMainContent(getSearchView());
		}

		if (source == help) {
			getMainWindow().addWindow(getHelpWindow());
		}

		if (source == share) {
			getMainWindow().addWindow(getSharingOptions());
		}

	}

	private void addNewContanct() {
		setMainContent(getListView());
		personForm.addContact();
	}


}
