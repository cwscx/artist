package com.arts.web.server;

import com.arts.web.client.SaveQuestionService;
import com.arts.web.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class SaveQuestionServiceImpl extends RemoteServiceServlet implements
    SaveQuestionService {

    @Override
    public boolean sendQuestion(String question) throws IllegalArgumentException {

    }
}