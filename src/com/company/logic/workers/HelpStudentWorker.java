package com.company.logic.workers;

import com.company.database.MySqlHelpStudentHandler;
import com.company.model.HelpStudent;
import com.company.transport.response.Response;

public class HelpStudentWorker {
    public static Response insertHelpStudent(HelpStudent helpStudent) {
        MySqlHelpStudentHandler.insertHelpStudent(helpStudent.getStudent().getUsername(), helpStudent.getText());
            return new Response(true,null);
    }
}
