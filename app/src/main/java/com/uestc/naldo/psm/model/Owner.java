package com.uestc.naldo.psm.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Unique;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.uestc.naldo.psm.dao.DaoSession;
import com.uestc.naldo.psm.dao.FeedbackDao;
import com.uestc.naldo.psm.dao.PetDao;
import com.uestc.naldo.psm.dao.OwnerDao;

/**
 * Created by Naldo on 2017/5/11.
 */

@Entity
public class Owner {
    @Id
    private long id;
    private String name;
    @Unique
    private String username;

    private String password;

    private int age;
    private String sex;
    private String email;

    @ToMany(referencedJoinProperty = "ownerId")
    List<Pet> pets;

    @ToMany(referencedJoinProperty = "ownerId")
    List<Feedback> feedbacks;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 640643577)
    private transient OwnerDao myDao;

    @Generated(hash = 437749160)
    public Owner(long id, String name, String username, String password, int age,
            String sex, String email) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.age = age;
        this.sex = sex;
        this.email = email;
    }

    @Generated(hash = 748179547)
    public Owner() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 983490205)
    public List<Pet> getPets() {
        if (pets == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PetDao targetDao = daoSession.getPetDao();
            List<Pet> petsNew = targetDao._queryOwner_Pets(id);
            synchronized (this) {
                if (pets == null) {
                    pets = petsNew;
                }
            }
        }
        return pets;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1767632067)
    public synchronized void resetPets() {
        pets = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1346705801)
    public List<Feedback> getFeedbacks() {
        if (feedbacks == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            FeedbackDao targetDao = daoSession.getFeedbackDao();
            List<Feedback> feedbacksNew = targetDao._queryOwner_Feedbacks(id);
            synchronized (this) {
                if (feedbacks == null) {
                    feedbacks = feedbacksNew;
                }
            }
        }
        return feedbacks;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 874729701)
    public synchronized void resetFeedbacks() {
        feedbacks = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2009899070)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getOwnerDao() : null;
    }

}
