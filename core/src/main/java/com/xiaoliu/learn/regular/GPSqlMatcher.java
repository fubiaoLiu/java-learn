package com.xiaoliu.learn.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description: 正则表达式匹配SQL
 * @author: FuBiaoLiu
 * @date: 2019/9/2
 */
public class GPSqlMatcher {

    public static void main(String[] args) {
        copyTableFrom();
        alterViewRenameTo();
        alterTableRenameTo();
        alterDatabaseRenameTo();
        createDatabaseWith();
        createTableLike();
        commentOn();
    }

    private static void copyTableFrom() {
        String sql = "COPY Table1 from table2";
        // 不区分大小写
        String regex1 = "(?i)copy(?-i) (.*?) (?i)from(?-i) (.*)";
        if (sql.matches(regex1)) {
            String table = getMatcher("(?i)copy(?-i) (.*?) (?i)from(?-i)", sql);
            System.out.println("[CopyTableFrom Regex1] Table is :" + table);
        }
        sql = sql.toLowerCase();
        String regex2 = "copy (.*?) from (.*)";
        if (sql.matches(regex2)) {
            String table = getMatcher("copy (.*?) from", sql);
            System.out.println("[CopyTableFrom Regex2] Table is :" + table);
        }
    }

    /**
     * 视图重命名
     * 管理元数据需要做的事情：
     * 1、血缘节点重命名
     * 2、数据库元数据更新时间更新、表元数据修改
     * 3、记录处理日志
     */
    private static void alterViewRenameTo() {
        String sql = "Alter View Table1 Rename to table2";
        // 不区分大小写
        String regex1 = "(?i)alter(?-i) (?i)view(?-i) (.*?) (?i)rename(?-i) (?i)to(?-i) (.*)";
        if (sql.matches(regex1)) {
            String oldName = getMatcher("(?i)alter(?-i) (?i)view(?-i) (.*?) (?i)rename(?-i)", sql);
            String newName = getMatcher("(?i)rename(?-i) (?i)to(?-i) (.*)", sql);
            System.out.println("[AlterViewRenameTo Regex] OldView is :" + oldName + "; NewView is :" + newName);
        }
    }

    /**
     * 表重命名
     * 管理元数据需要做的事情：
     * 1、血缘节点重命名
     * 2、数据库元数据更新时间更新、表元数据修改
     * 3、记录处理日志
     */
    private static void alterTableRenameTo() {
        String sql = "Alter table Table1 Rename to table2";
        // 不区分大小写
        String regex1 = "(?i)alter(?-i) (?i)table(?-i) (.*?) (?i)rename(?-i) (?i)to(?-i) (.*)";
        if (sql.matches(regex1)) {
            String oldName = getMatcher("(?i)alter(?-i) (?i)table(?-i) (.*?) (?i)rename(?-i)", sql);
            String newName = getMatcher("(?i)rename(?-i) (?i)to(?-i) (.*)", sql);
            System.out.println("[AlterTableRenameTo Regex] OldTable is :" + oldName + "; NewTable is :" + newName);
        }
    }

    /**
     * 数据库重命名
     * 管理元数据需要做的事情：
     * 1、数据库元数据修改
     * 2、记录处理日志
     */
    private static void alterDatabaseRenameTo() {
        String sql = "Alter database database1 Rename to database2";
        // 不区分大小写
        String regex1 = "(?i)alter(?-i) (?i)database(?-i) (.*?) (?i)rename(?-i) (?i)to(?-i) (.*)";
        if (sql.matches(regex1)) {
            String oldName = getMatcher("(?i)alter(?-i) (?i)database(?-i) (.*?) (?i)rename(?-i)", sql);
            String newName = getMatcher("(?i)rename(?-i) (?i)to(?-i) (.*)", sql);
            System.out.println("[AlterDatabaseRenameTo Regex] OldDatabase is :" + oldName + "; NewDatabase is :" + newName);
        }
    }

    /**
     * 创建数据库
     * 管理元数据需要做的事情：
     * 1、新增数据库元数据
     * 2、记录处理日志
     */
    private static void createDatabaseWith() {
        String sql = "CREATE database database1 WITH database2";
        // 不区分大小写
        String regex1 = "(?i)create(?-i) (?i)database(?-i) (.*?) (?i)with(?-i)(.*)";
        if (sql.matches(regex1)) {
            String dbName = getMatcher("(?i)create(?-i) (?i)database(?-i) (.*?) (?i)with", sql);
            System.out.println("[CreateDatabaseWith Regex] DbName is :" + dbName);
        }
    }

    /**
     * 创建表
     * 管理元数据需要做的事情：
     * 1、新增表元数据(表信息和字段信息)
     * 2、数据库元数据更新时间更新
     * 3、记录处理日志
     */
    private static void createTableLike() {
        String sql = "CREATE table table1 like ...";
        // 不区分大小写
        String regex1 = "(?i)create(?-i) (?i)table(?-i) (.*?) \\((?i)like(?-i)(.*)\\)";
        if (sql.matches(regex1)) {
            String tableName = getMatcher("(?i)create(?-i) (?i)table(?-i) (.*?) \\((?i)like(?-i)", sql);
            System.out.println("[CreateTableLike Regex] TableName is :" + tableName);
        }
    }

    /**
     * 添加注释(表、字段)
     * 管理元数据需要做的事情：
     * 1、重新拉取表元数据并更新
     * 2、删除表字段元数据并重新插入
     * 3、数据库元数据更新时间更新
     * 4、记录处理日志
     */
    private static void commentOn() {
        String sql = "COMMENT on table table1 is '...'";
        // 不区分大小写
        String regex = "(?i)comment(?-i) (?i)on(?-i) (.*?) (?i)is(?-i) (.*?)";
        String regex1 = "(?i)comment(?-i) (?i)on(?-i) (?i)table(?-i) (.*?) (?i)is(?-i) (.*?)";
        String regex2 = "(?i)comment(?-i) (?i)on(?-i) (?i)column(?-i) (.*?) (?i)is(?-i) (.*?)";
        if (sql.matches(regex)) {
            String schema = "";
            String table = "";
            String tableName = getMatcher("(?i)create(?-i) (?i)table(?-i) (.*?) \\((?i)like(?-i)", sql);
            System.out.println("[CreateTableLike Regex] TableName is :" + tableName);
            if (sql.matches(regex1)) {
                table = getMatcher("(?i)comment(?-i) (?i)on(?-i) (?i)table(?-i) (.*?) (?i)is(?-i)", sql);
                if (table.contains(".")) {
                    String[] tables = table.split("\\.");
                    schema = tables[0];
                    table = tables[1];
                } else {
                    schema = "public";
                }
            } else if (sql.matches(regex2)) {
                table = getMatcher("(?i)comment(?-i) (?i)on(?-i) (?i)column(?-i) (.*?) (?i)is(?-i)", sql);
                if (table.contains(".")) {
                    String[] tables = table.split("\\.");
                    if (tables.length == 2) {
                        table = tables[0];
                        schema = "public";
                    } else if (tables.length == 3) {
                        schema = tables[0];
                        table = tables[1];
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }

            System.out.println("[CommentOn Regex] Schema is :" + schema + "Table is :" + table);
        }
    }

    /**
     * GP others:
     * SQLDropDatabaseStatement:删除数据库元数据
     * SQLCreateDatabaseStatement:新增数据库元数据
     * SQLCreateTableStatement:新增表元数据(表、字段)、表血缘关系，数据库元数据更新时间更新
     * SQLDropTableStatement:删除表元数据(表、字段)、血缘节点、血缘过程
     * SQLTruncateStatement:删除表血缘，数据库元数据更新时间更新
     * SQLAlterTableStatement:覆盖更新表元数据(表、字段)、表血缘关系，数据库元数据更新时间更新
     * SQLInsertStatement:更新表元数据(表大小、记录数、数据更新时间)
     * SQLDeleteStatement:更新表元数据(表大小、记录数、数据更新时间)
     * SQLUpdateStatement:更新表元数据(表大小、记录数、数据更新时间)
     * SQLDropViewStatement:删除表元数据(表、字段)、血缘节点、血缘过程，数据库元数据更新时间更新
     * SQLCreateViewStatement:新增表元数据(表、字段)、血缘节点、血缘过程，数据库元数据更新时间更新
     *
     * GP 没有alter table as语法，所以修改表无法改变血缘；同时GP也没有alter view语法，所以血缘产生只能在创建和插入操作的时候。
     */

    /**
     * @param regex  正则表达式
     * @param source 待匹配字符串
     * @return
     */
    private static String getMatcher(String regex, String source) {
        String result = "";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        while (matcher.find()) {
            result = matcher.group(1);
        }
        return result;
    }

    /**
     * Hive:
     * operationName(hive的操作类型==钩子拿到的sql的操作类型):
     *      CREATEDATABASE(创建库):新增数据库元数据，新增操作日志
     *      DROPDATABASE(删除库):删除数据库元数据，新增操作日志
     *      ALTERDATABASE、ALTERDATABASE_OWNER:hive 没有修改数据库名的语法，所以无表、字段、备注等元数据操作，但是操作人修改人要记录
     *      CREATETABLE(创建表):新增表元数据(表、字段)，数据库元数据更新时间更新，新增操作日志。(由于从hdfs建表的过程记录成血缘会引起冲突，所以不记录，直接从表级开始)
     *      DROPTABLE、DROPVIEW(删除表、视图):删除表元数据(表、字段)、血缘关系，数据库元数据更新时间更新，新增操作日志
     *      TRUNCATETABLE(清空表):删除血缘(内部表。外部表不会清空数据，所以不用删除血缘)，数据库元数据更新时间更新，新增操作日志
     *      CREATETABLE_AS_SELECT(创建表):新增表元数据(表、字段)，数据库元数据更新时间更新，新增操作日志
     *      CREATEVIEW、ALTERVIEW_AS():由于CREATEVIEW和ALTERVIEW_AS返回的类型都是CREATEVIEW，区分不了，所以都采用：覆盖表元数据、覆盖血缘
     *          sql.matches("(?i)create(?-i) (?i)view(?-i) (.*?)"):新增表元数据(表、字段)、血缘关系，数据库元数据更新时间更新，新增操作日志
     *          sql.matches("(?i)alter(?-i) (?i)view(?-i) (.*?)"):删除血缘关系，新增表元数据(表、字段)、血缘关系，数据库元数据更新时间更新，新增操作日志
     *      QUERY:insert into as也是一种血缘
     *          同时有输入和输出节点的都要保存血缘:(新增血缘节点的时候，需要注意，这个节点可能已经存在，可能是其他操作已经建立了该节点。建立血缘过程的时候，也需要判断是否已经存在该过程了，可能是分区操作或者其他操作，已经建立了该过程；已经存在的就不用再建立了)
     *              sql.matches("(?i)insert(?-i) (?i)into(?-i) (.*?)"):新增血缘关系，更新表元数据
     *              sql.matches("(?i)insert(?-i) (?i)overwrite(?-i) (.*?)"):重建血缘关系，更新表元数据
     *          其他:
     *              !sql.matches("(?i)select(?-i) (.*?)"):更新表元数据
     *      ALTERTABLE_FILEFORMAT、ALTERTABLE_CLUSTER_SORT、ALTERTABLE_BUCKETNUM、ALTERTABLE_PROPERTIES、
     *      ALTERVIEW_PROPERTIES、ALTERTABLE_SERDEPROPERTIES、ALTERTABLE_SERIALIZER、ALTERTABLE_ADDCOLS、
     *      ALTERTABLE_REPLACECOLS、ALTERTABLE_PARTCOLTYPE、ALTERTABLE_LOCATION、ALTERTABLE_RENAMECOL:修改表元数据(表、字段)，数据库元数据更新时间更新，新增操作日志。(hive修改表并不产生血缘)
     *      ALTERTABLE_RENAME、ALTERVIEW_RENAME(修改表名):重命名表元数据、更新表元数据(表、字段)，数据库元数据更新时间更新，新增操作日志。
     */
}

