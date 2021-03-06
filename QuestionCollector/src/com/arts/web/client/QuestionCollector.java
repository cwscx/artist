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
  private Button submitQuestionButton = new Button("Submit Question");
  private ListBox introLanguageListBox = new ListBox();
  private HTMLPanel introPanel = new HTMLPanel("<div id=\"introduction\"></div>");
  private HTMLPanel introParagraphPanel = new HTMLPanel("<h1 id=\"intro-para\"></h1>");
  private VerticalPanel questionPanel = new VerticalPanel();
  private TextBox questionEntryBox = new TextBox();

  private final SaveQuestionServiceAsync saveQuestionSerivce = GWT.create(SaveQuestionService.class);

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    introPanel.add(introLanguageListBox);
    introPanel.add(askQuestionButton);
    introPanel.add(introParagraphPanel);
    RootPanel.get().add(introPanel);

    questionPanel.add(questionEntryBox);
    questionPanel.add(submitQuestionButton);
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
        introPanel.setVisible(false);
        questionPanel.setVisible(true);
      }
    });

    // Intro language change logic
    String defaultLanguage = introLanguageListBox.getSelectedItemText();
    Element introDiv = introParagraphPanel.getElementById("intro-para");
    introDiv.setInnerText("This is " + defaultLanguage + " intro.");

    // Handler to change the intro paragraph.
    introLanguageListBox.addChangeHandler(new ChangeHandler() {
      public void onChange(ChangeEvent event) {
        String selectedLanguage = introLanguageListBox.getSelectedItemText();
        Element introDiv = introParagraphPanel.getElementById("intro-para");
        introDiv.setInnerText("This is " + selectedLanguage + " intro.");
      }
    });

    // The text box to enter questions. Sets style and default text.
    questionEntryBox.setStyleName("questionPanel", true);
    questionEntryBox.setMaxLength(500);
    questionEntryBox.setText("Please Enter Your Question today!");

    submitQuestionButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        // Set intro paragraph and intro change button invisible.
        String question = questionEntryBox.getText();
        saveQuestionSerivce.sendQuestion(question, new AsyncCallback<Boolean>() {
          @Override
          public void onFailure(Throwable caught) {
            Window.alert(caught.getMessage());
          }

          @Override
          public void onSuccess(Boolean result) {
            if (result) {
              Window.alert("return true");
            } else {
              Window.alert("return false");
            }         
          }
        });
      }
    });


  }
}
