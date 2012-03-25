package com.example.addressbook.ui;

import com.vaadin.ui.SplitPanel;

public class ListView extends SplitPanel {

	public ListView() {
		// TODO Auto-generated constructor stub
	}

	public ListView(PersonList personList, PersonForm personForm) {
		 setFirstComponent(personList);
         setSecondComponent(personForm);
         setSplitPosition(40);
	}

	

}
