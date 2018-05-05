package com.arts.web.server;

import com.arts.web.client.SaveQuestionService;
import com.arts.web.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class SaveQuestionServiceImpl extends RemoteServiceServlet implements
    SaveQuestionService {

    // Initializes a file called "question.txt" to document all the questions entered.
    private final File pwd = new File(".");
    private final File questionFile = new File(pwd, "questions.txt");

    // Synchronized lock to avoid mutliple writing to the file.
    private final Object writeLock = new Object();

    @Override
    public boolean sendQuestion(String question) throws IllegalArgumentException {
      if (!questionFile.exists()) {
        return false;
      }

      // Appends the question to the file.
      boolean append = true;
      try {
      	synchronized (writeLock) {
          FileOutputStream output = new FileOutputStream(questionFile, append);
	      output.write(question.getBytes());
	      output.write('\n');
	      output.close();
      	}
      } catch (IOException e) {
      	return false;
      }
      
      return true;
    }
}