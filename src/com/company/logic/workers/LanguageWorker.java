package com.company.logic.workers;

import com.company.database.MySqlLanguagesHandler;
import com.company.model.buffers.LanguagesArrayList;
import com.company.transport.response.Response;

public class LanguageWorker {

    public static Response getAllLanguages() {
        LanguagesArrayList languagesArrayList = new LanguagesArrayList(MySqlLanguagesHandler.selectAllLanguages());
        return new Response(true,languagesArrayList);
    }

}
