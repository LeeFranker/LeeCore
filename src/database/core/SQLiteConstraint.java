package database.core;

/**
 * 字段的约束
 * 
 * @author LeeFranker
 * 
 */
public enum SQLiteConstraint {
	UNIQUE {
		@Override
		public String toString() {
			return "UNIQUE";
		}
	},
	PRIMARY_KEY_AUTOINCREMENT {
		@Override
		public String toString() {
			return "PRIMARY KEY AUTOINCREMENT";
		}
	},
	NOT_NULL {
		@Override
		public String toString() {
			return "NOT NULL";
		}
	}

}
