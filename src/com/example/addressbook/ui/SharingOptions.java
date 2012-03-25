package com.example.addressbook.ui;

import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

public class SharingOptions extends Window {

	
	private Button close = null;
	private CheckBox gmail = null;
	private CheckBox mac = null;
	
	private static final String HELP_HTML_SNIPPET = "With these setting you can modify contact sharing "
            + "options. (non-functional, example of modal dialog)";
	
	
	public SharingOptions() {
		
		initComponente();
        setModal(true);
        setWidth("50%");
        center();
        setCaption("Sharing options");
        addComponent(new Label(HELP_HTML_SNIPPET));
        addComponent(gmail);
        addComponent(mac);
        addComponent(close);
	}
	
	private void initComponente(){
		close = new Button("OK");
		gmail = new CheckBox("Gmail");
		mac = new CheckBox("Gmail");
	}

}
