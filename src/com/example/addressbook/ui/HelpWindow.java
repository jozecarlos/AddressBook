package com.example.addressbook.ui;


import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

public class HelpWindow extends Window {

	 private static final String HELP_HTML_SNIPPET = "This is "
             + "an application built during <strong><a href=\""
             + "http://dev.vaadin.com/\">Vaadin</a></strong> "
             + "tutorial. Hopefully it doesn't need any real help.";
	
	public HelpWindow() {
		setCaption("Address Book Help");
		addComponent(new Label(HELP_HTML_SNIPPET, Label.CONTENT_XHTML));
	}

}
