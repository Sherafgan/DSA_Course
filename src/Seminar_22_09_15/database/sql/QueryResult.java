package Seminar_22_09_15.database.sql;
import java.util.List;
import Seminar_22_09_15.database.Row;

/**
 * 
 * Class representing query  result: result set + number of rows affected
 *
 */
public class QueryResult {
	public List<Row> RESULT_SET;
	public int ROWS_AFFECTED;
}