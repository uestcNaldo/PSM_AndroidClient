package com.uestc.naldo.psm.dao;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import com.uestc.naldo.psm.model.Pet;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PET".
*/
public class PetDao extends AbstractDao<Pet, Long> {

    public static final String TABLENAME = "PET";

    /**
     * Properties of entity Pet.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Age = new Property(2, int.class, "age", false, "AGE");
        public final static Property Sex = new Property(3, String.class, "sex", false, "SEX");
        public final static Property Species = new Property(4, String.class, "species", false, "SPECIES");
        public final static Property Hr_content = new Property(5, String.class, "hr_content", false, "HR_CONTENT");
        public final static Property Start = new Property(6, java.util.Date.class, "start", false, "START");
        public final static Property End = new Property(7, java.util.Date.class, "end", false, "END");
        public final static Property OwnerId = new Property(8, long.class, "ownerId", false, "OWNER_ID");
        public final static Property CourseId = new Property(9, long.class, "courseId", false, "COURSE_ID");
    }

    private Query<Pet> owner_PetsQuery;
    private Query<Pet> course_PetsQuery;

    public PetDao(DaoConfig config) {
        super(config);
    }
    
    public PetDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PET\" (" + //
                "\"_id\" INTEGER PRIMARY KEY NOT NULL ," + // 0: id
                "\"NAME\" TEXT NOT NULL ," + // 1: name
                "\"AGE\" INTEGER NOT NULL ," + // 2: age
                "\"SEX\" TEXT," + // 3: sex
                "\"SPECIES\" TEXT," + // 4: species
                "\"HR_CONTENT\" TEXT," + // 5: hr_content
                "\"START\" INTEGER," + // 6: start
                "\"END\" INTEGER," + // 7: end
                "\"OWNER_ID\" INTEGER NOT NULL ," + // 8: ownerId
                "\"COURSE_ID\" INTEGER NOT NULL );"); // 9: courseId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PET\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Pet entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
        stmt.bindString(2, entity.getName());
        stmt.bindLong(3, entity.getAge());
 
        String sex = entity.getSex();
        if (sex != null) {
            stmt.bindString(4, sex);
        }
 
        String species = entity.getSpecies();
        if (species != null) {
            stmt.bindString(5, species);
        }
 
        String hr_content = entity.getHr_content();
        if (hr_content != null) {
            stmt.bindString(6, hr_content);
        }
 
        java.util.Date start = entity.getStart();
        if (start != null) {
            stmt.bindLong(7, start.getTime());
        }
 
        java.util.Date end = entity.getEnd();
        if (end != null) {
            stmt.bindLong(8, end.getTime());
        }
        stmt.bindLong(9, entity.getOwnerId());
        stmt.bindLong(10, entity.getCourseId());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Pet entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
        stmt.bindString(2, entity.getName());
        stmt.bindLong(3, entity.getAge());
 
        String sex = entity.getSex();
        if (sex != null) {
            stmt.bindString(4, sex);
        }
 
        String species = entity.getSpecies();
        if (species != null) {
            stmt.bindString(5, species);
        }
 
        String hr_content = entity.getHr_content();
        if (hr_content != null) {
            stmt.bindString(6, hr_content);
        }
 
        java.util.Date start = entity.getStart();
        if (start != null) {
            stmt.bindLong(7, start.getTime());
        }
 
        java.util.Date end = entity.getEnd();
        if (end != null) {
            stmt.bindLong(8, end.getTime());
        }
        stmt.bindLong(9, entity.getOwnerId());
        stmt.bindLong(10, entity.getCourseId());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public Pet readEntity(Cursor cursor, int offset) {
        Pet entity = new Pet( //
            cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // name
            cursor.getInt(offset + 2), // age
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // sex
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // species
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // hr_content
            cursor.isNull(offset + 6) ? null : new java.util.Date(cursor.getLong(offset + 6)), // start
            cursor.isNull(offset + 7) ? null : new java.util.Date(cursor.getLong(offset + 7)), // end
            cursor.getLong(offset + 8), // ownerId
            cursor.getLong(offset + 9) // courseId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Pet entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setName(cursor.getString(offset + 1));
        entity.setAge(cursor.getInt(offset + 2));
        entity.setSex(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setSpecies(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setHr_content(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setStart(cursor.isNull(offset + 6) ? null : new java.util.Date(cursor.getLong(offset + 6)));
        entity.setEnd(cursor.isNull(offset + 7) ? null : new java.util.Date(cursor.getLong(offset + 7)));
        entity.setOwnerId(cursor.getLong(offset + 8));
        entity.setCourseId(cursor.getLong(offset + 9));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Pet entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Pet entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Pet entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "pets" to-many relationship of Owner. */
    public List<Pet> _queryOwner_Pets(long ownerId) {
        synchronized (this) {
            if (owner_PetsQuery == null) {
                QueryBuilder<Pet> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.OwnerId.eq(null));
                owner_PetsQuery = queryBuilder.build();
            }
        }
        Query<Pet> query = owner_PetsQuery.forCurrentThread();
        query.setParameter(0, ownerId);
        return query.list();
    }

    /** Internal query to resolve the "pets" to-many relationship of Course. */
    public List<Pet> _queryCourse_Pets(long courseId) {
        synchronized (this) {
            if (course_PetsQuery == null) {
                QueryBuilder<Pet> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.CourseId.eq(null));
                course_PetsQuery = queryBuilder.build();
            }
        }
        Query<Pet> query = course_PetsQuery.forCurrentThread();
        query.setParameter(0, courseId);
        return query.list();
    }

}