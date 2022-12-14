/*
 * This file is generated by jOOQ.
 */
package com.dw.dwproject.api.model.tables.records;


import com.dw.dwproject.api.model.tables.User;

import java.time.LocalDate;

import org.jooq.Field;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserRecord extends TableRecordImpl<UserRecord> implements Record3<Integer, String, LocalDate> {

    private static final long serialVersionUID = -1245481630;

    /**
     * Setter for <code>public.user.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.user.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.user.nom</code>.
     */
    public void setNom(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.user.nom</code>.
     */
    public String getNom() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.user.dateCreation</code>.
     */
    public void setDatecreation(LocalDate value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.user.dateCreation</code>.
     */
    public LocalDate getDatecreation() {
        return (LocalDate) get(2);
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, String, LocalDate> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<Integer, String, LocalDate> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return User.USER.ID;
    }

    @Override
    public Field<String> field2() {
        return User.USER.NOM;
    }

    @Override
    public Field<LocalDate> field3() {
        return User.USER.DATECREATION;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getNom();
    }

    @Override
    public LocalDate component3() {
        return getDatecreation();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getNom();
    }

    @Override
    public LocalDate value3() {
        return getDatecreation();
    }

    @Override
    public UserRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public UserRecord value2(String value) {
        setNom(value);
        return this;
    }

    @Override
    public UserRecord value3(LocalDate value) {
        setDatecreation(value);
        return this;
    }

    @Override
    public UserRecord values(Integer value1, String value2, LocalDate value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached UserRecord
     */
    public UserRecord() {
        super(User.USER);
    }

    /**
     * Create a detached, initialised UserRecord
     */
    public UserRecord(Integer id, String nom, LocalDate datecreation) {
        super(User.USER);

        set(0, id);
        set(1, nom);
        set(2, datecreation);
    }
}
