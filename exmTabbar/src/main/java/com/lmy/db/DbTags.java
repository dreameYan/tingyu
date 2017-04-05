package com.lmy.db;

public class DbTags {
	
	public final static String DB_NAME="tingyuaudios.db";
	//a类---audio
	public final static String TABLE_A_INFO="table_a_info";
	public final static String TABLE_A_CATEGORY="a_category";
	
	//field
	public final static String FIELD_ID = "_id";
	public final static String FIELD_A_DOWNLOAD = "a_downloadurl";
	public final static String FIELD_A_MD5 = "a_md5";
	public final static String FIELD_A_ID = "a_id";
	public final static String FIELD_A_NAME = "a_name";
	public final static String FIELD_A_AUTHOR = "a_author";
	public final static String FIELD_A_PATH = "a_path";
	public final static String FIELD_A_ADD_TIME = "a_add_time";
	public final static String FIELD_A_OPEN_TIME = "a_open_time";
	public final static String FIELD_A_CATEGORY_ID = "a_category_id";
	public final static String FIELD_A_CATEGORY_NAME = "a_category_name";
	public final static String FIELD_A_SIZE = "a_size";
	public final static String FIELD_A_FAV="a_is_fav";
	public final static String FIELD_A_PROGRESS = "a_progress";
	public final static String FIELD_A_MARK_ID = "a_mark_id";//上次学习到哪里
	public final static String FIELD_A_MARK_ADD_TIME= "a_mark_add_time";
	public final static String FIELD_A_MARK_PROGRESS = "a_mark_progress";
	public final static String FIELD_A_MARK_BEGIN_POSITION = "a_mark_begin_position";
	public final static String FIELD_A_MARK_DETAIL = "a_mark_detail";
	public final static String FIELD_A_BEGIN_POSITION = "a_begin_position";

	//provider  ？
	public final static String DB_PROVIDER = "content://com.yamin.reader.database.BookContentProvider";
	public final static String URI_TABLE_BOOK_INFO = DB_PROVIDER+"/"+TABLE_A_INFO;
	public final static String URI_TABLE_BOOK_CATEGORY = DB_PROVIDER+"/"+TABLE_A_CATEGORY;
	
}
