package si.ai.othello.web.util;

import lombok.Getter;

/**
 * @author Jakub Licznerski
 *         Created on 06.05.2017.
 */
public class Path {

    public static class Web {
        @Getter
        public static final String INDEX = "/index/";
    }

    public static class Template {
        @Getter public static final String INDEX = "/velocity/index.vm";
    }
}
