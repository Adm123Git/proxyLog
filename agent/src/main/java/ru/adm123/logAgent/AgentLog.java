package ru.adm123.logAgent;

import ru.adm123.logAgent.annotation.TransformerMethodLog;

import java.lang.instrument.Instrumentation;

/**
 * Created by Dmitry Ushakov at 22.01.2022
 */
public class AgentLog {

    public static void premain(String agentArgument,
                               Instrumentation instrumentation) {
        instrumentation.addTransformer(new TransformerMethodLog());
    }

}
