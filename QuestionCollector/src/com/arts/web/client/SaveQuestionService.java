package com.arts.web.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("saveQuestion")
public interface SaveQuestionService extends RemoteService {
  boolean sendQuestion(String question) throws IllegalArgumentException;
}
