package it.digitazon.musicfy.presentation.dto;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class ArtistDTO {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private long id;
    private String name;
    private boolean isDeleted;
    private String alias;

    private String birthDate;

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
    public Date convertBirthDate(){
        if (this.birthDate == null || this.birthDate.length() == 0){
            return null;
        }
        try {
            return  dateFormat.parse(this.birthDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public void convertDateToString(Date date){
        if (date == null){
            this.birthDate = null;

        }else {
            this.birthDate = dateFormat.format(date);
        }
    }
}
