package com.company.logic.workers;

import com.company.database.MySqlTeacherStatisticHandler;
import com.company.model.TeacherStatistics;
import com.company.model.buffers.TeacherStatisticsArrayList;
import com.company.transport.response.Response;

public class TeacherStatisticsWorker {
    public static Response getStatisticByTeacherUsername(String username) {

        TeacherStatisticsArrayList teacherStatistics = new TeacherStatisticsArrayList(MySqlTeacherStatisticHandler.selectStatisticByTeacherUsername(username));
        return new Response(true, teacherStatistics);

    }
}
