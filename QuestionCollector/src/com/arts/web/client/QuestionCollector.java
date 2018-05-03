package com.arts.web.client;

import com.arts.web.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import java.util.HashMap;
import java.util.Map;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class QuestionCollector implements EntryPoint {
  
  private static final String[] AVAILABLE_INTRO_LANGS = 
      {"English", "Chinese", "Spanish", "French"};

  private ListBox introLanguageListBox = new ListBox();
  
  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    for (String lang : AVAILABLE_INTRO_LANGS) {
      introLanguageListBox.addItem(lang);
    }
    RootPanel.get("introduction-lang-select").add(introLanguageListBox);

    String defaultLanguage = introLanguageListBox.getSelectedItemText();
    Element introDiv = DOM.getElementById("introduction");
    introDiv.setInnerText("This is " + defaultLanguage + " intro.");

    introLanguageListBox.addChangeHandler(new ChangeHandler() {
      public void onChange(ChangeEvent event) {
        String selectedLanguage = introLanguageListBox.getSelectedItemText();
        Element introDiv = DOM.getElementById("introduction");
        introDiv.setInnerText("This is " + selectedLanguage + " intro.");
      }
    });
  }
}
