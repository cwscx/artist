package com.arts.web.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>SaveQuestion</code>.
 */
public interface SaveQuestionServiceAsync {
  void sendQuestion(String question, AsyncCallback<Boolean> callback)
      throws IllegalArgumentException;
}
