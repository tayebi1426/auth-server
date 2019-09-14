package ir.demisco.cloud.auth.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;


@MappedSuperclass
public abstract class BaseModel<T extends Number> implements Serializable {

    public static final long serialVersionUID = 43243533452345342L;
    public final static String SEQ_GENERATOR_NAME = "SEQUENCE_GENERATOR_NAME";
    private T id;

    @Transient
    public T getId() {
        return id;
    }


    public void setId(T id) {
        this.id = id;
    }


    @Transient
    @JsonIgnore
    public boolean isIdSet() {
        return getId() != null;
    }

    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (this == other) return true;
        if (!(other instanceof BaseModel)) return false;
        final BaseModel model = (BaseModel) other;
        return this.getId() != null && model.getId() != null && this.getId().equals(model.getId());
    }

    public int hashCode() {
        if (this.getId() == null) {
            return super.hashCode();
        }
        int result;
        result = 31 * getId().hashCode();
        return result;
    }
}
