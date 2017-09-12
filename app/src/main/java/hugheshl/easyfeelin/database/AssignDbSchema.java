package hugheshl.easyfeelin.database;

public class AssignDbSchema {
    public static final class AssignTable {
        public static final String NAME = "assignments";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "Date";
            public static final String COMPLETED = "completed";
            public static final String TOTALTIME = "totalTime";
            public static final String LOGS = "logs";
        }
    }
}