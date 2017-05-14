package com.uestc.naldo.psm.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.uestc.naldo.psm.model.Course;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "COURSE".
*/
public class CourseDao extends AbstractDao<Course, Long> {

    public static final String TABLENAME = "COURSE";

    /**
     * Properties of entity Course.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Condition = new Property(2, String.class, "condition", false, "CONDITION");
        public final static Property Duration = new Property(3, String.class, "duration", false, "DURATION");
        public final static Property Content = new Property(4, String.class, "content", false, "CONTENT");
        public final static Property Prise = new Property(5, String.class, "prise", false, "PRISE");
        public final static Property Note = new Property(6, String.class, "note", false, "NOTE");
    }

    private DaoSession daoSession;


    public CourseDao(DaoConfig config) {
        super(config);
    }
    
    public CourseDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"COURSE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY NOT NULL ," + // 0: id
                "\"NAME\" TEXT NOT NULL ," + // 1: name
                "\"CONDITION\" TEXT," + // 2: condition
                "\"DURATION\" TEXT," + // 3: duration
                "\"CONTENT\" TEXT," + // 4: content
                "\"PRISE\" TEXT," + // 5: prise
                "\"NOTE\" TEXT);"); // 6: note
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"COURSE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Course entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
        stmt.bindString(2, entity.getName());
 
        String condition = entity.getCondition();
        if (condition != null) {
            stmt.bindString(3, condition);
        }
 
        String duration = entity.getDuration();
        if (duration != null) {
            stmt.bindString(4, duration);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(5, content);
        }
 
        String prise = entity.getPrise();
        if (prise != null) {
            stmt.bindString(6, prise);
        }
 
        String note = entity.getNote();
        if (note != null) {
            stmt.bindString(7, note);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Course entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
        stmt.bindString(2, entity.getName());
 
        String condition = entity.getCondition();
        if (condition != null) {
            stmt.bindString(3, condition);
        }
 
        String duration = entity.getDuration();
        if (duration != null) {
            stmt.bindString(4, duration);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(5, content);
        }
 
        String prise = entity.getPrise();
        if (prise != null) {
            stmt.bindString(6, prise);
        }
 
        String note = entity.getNote();
        if (note != null) {
            stmt.bindString(7, note);
        }
    }

    @Override
    protected final void attachEntity(Course entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public Course readEntity(Cursor cursor, int offset) {
        Course entity = new Course( //
            cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // condition
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // duration
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // content
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // prise
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6) // note
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Course entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setName(cursor.getString(offset + 1));
        entity.setCondition(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setDuration(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setContent(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setPrise(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setNote(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Course entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Course entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Course entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}