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
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import java.util.HashMap;
import java.util.Map;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class QuestionCollector implements EntryPoint {
  
  private static final String[] AVAILABLE_INTRO_LANGS = 
      {"English", "Chinese", "Spanish", "French"};

  private Button askQuestionButton = new Button("I feel lucky!");
  private ListBox introLanguageListBox = new ListBox();
  private HTMLPanel introPanel = new HTMLPanel("<div id=\"introduction\"></div>");
  private HTMLPanel introParagraphPanel = new HTMLPanel("<h1 id=\"intro-para\"></h1>");
  private FocusPanel questionPanel = new FocusPanel();
  private TextBox questionEntryBox = new TextBox();

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    introPanel.add(introLanguageListBox);
    introPanel.add(askQuestionButton);
    introPanel.add(introParagraphPanel);
    RootPanel.get().add(introPanel);

    questionPanel.add(questionEntryBox);
    RootPanel.get().add(questionPanel);
    questionPanel.setVisible(false);

    // Adds language selection button
    for (String lang : AVAILABLE_INTRO_LANGS) {
      introLanguageListBox.addItem(lang);
    }

    // Set the style of askQuestionButton
    askQuestionButton.setStyleName("askQuestionButton", true);
    askQuestionButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        // Set intro paragraph and intro change button invisible.
        introLanguageListBox.setVisible(false);
        introParagraphPanel.setVisible(false);
        questionPanel.setVisible(true);
      }
    });

    // Intro language change logic
    String defaultLanguage = introLanguageListBox.getSelectedItemText();
    Element introDiv = introParagraphPanel.getElementById("intro-para");
    introDiv.setInnerText("This is " + defaultLanguage + " intro.");

    introLanguageListBox.addChangeHandler(new ChangeHandler() {
      public void onChange(ChangeEvent event) {
        String selectedLanguage = introLanguageListBox.getSelectedItemText();
        Element introDiv = introParagraphPanel.getElementById("intro-para");
        introDiv.setInnerText("This is " + selectedLanguage + " intro.");
      }
    });

    questionEntryBox.setStyleName("questionPanel", true);
    questionEntryBox.setMaxLength(500);
    questionEntryBox.setText("Please Enter Your Question today!");
  }
}
