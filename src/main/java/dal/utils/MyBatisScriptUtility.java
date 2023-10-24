package dal.utils;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.sql.Connection;

public class MyBatisScriptUtility {
    private static final String projectRoot = System.getProperty("user.dir");

    public static void runScript(
            Connection connection
    ) throws Exception {
        ScriptRunner scriptRunner = new ScriptRunner(connection);
        scriptRunner.setSendFullScript(false);
        scriptRunner.setStopOnError(true);
        String path = "\\src\\main\\resources\\init.sql";
        scriptRunner.runScript(new java.io.FileReader(projectRoot + path));
    }
}