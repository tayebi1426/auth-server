package ir.demisco.cloud.auth.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import ir.demisco.cloud.auth.model.schema.*;

@Entity
@Table(name = "APPLICATION_USER",schema = SchemaList.COR)
public class User extends AuditModel<Long> implements UserDetails {

    public final static String SEQUENCE_NAME = "SQ_APPLICATION_USER";

    private String username;
    private String password;
    private String nickName;
    private Boolean enabled;
    private Boolean locked;
    private Boolean expired;
    private Long personId;
    private Date registerDate;
    private Date lastPasswordResetDate;

    private Collection<GrantedAuthority> authorities;

    private boolean sessionEntry = false;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public User(Long id, String username) {
        this.setId(id);
        this.username = username;
        this.sessionEntry = true;
    }

    public User(Long id, String username, String nickName) {
        this.setId(id);
        this.username = username;
        this.nickName = nickName;
    }

    public User(Long id, String username, String password, String nickName, Long personId, Boolean enabled, Boolean locked, Boolean expired, Collection<GrantedAuthority> authorities) {
        this.setId(id);
        this.username = username;
        this.password = password;
        this.nickName = nickName;
        this.personId = personId;
        this.enabled = enabled;
        this.locked = locked;
        this.expired = expired;
        this.authorities = Collections.unmodifiableCollection(authorities);
        this.sessionEntry = true;
    }

    public static User.Builder getBuilder() {
        return new User.Builder();
    }

    @Id
    @Column(nullable = false, length = 19)
    @GeneratedValue(generator = SEQUENCE_NAME, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME,schema = SchemaList.COR)
    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    @NotNull
    @Size(min = 3, max = 15)
    @Column(name = "USER_NAME", nullable = false, length = 15, updatable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    @NotNull
    @Column(nullable = false, length = 33)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull
    @Size(min = 1, max = 60)
    @Column(nullable = false, length = 60)
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @NotNull
    @Column(name = "LOCKED_FLG", length = 1, nullable = false)
    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    @NotNull
    @Column(name = "EXPIRED_FLG", length = 1, nullable = false)
    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    @NotNull
    @Column(name = "ENABLED_FLG", nullable = false, length = 1)
    public Boolean getEnabledWrapper() {
        return enabled;
    }

    public void setEnabledWrapper(Boolean enabled) {
        this.enabled = enabled;
    }

    @Transient
    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    @Column(name = "PERSON_ID", precision = 19/*, nullable = false*/)
    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    @Override
    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return !getExpired();
    }

    @Override
    @Transient
    public boolean isAccountNonLocked() {
        return !getLocked();
    }

    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isEnabled() {
        return this.enabled;
    }

    @Transient
    public boolean isSessionEntry() {
        return sessionEntry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        return getId() != null ? getId().equals(user.getId()) : user.getId() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        return result;
    }

    public static class Builder {
        User user;

        public Builder() {
            user = new User();
        }

        public Builder username(String username) {
            user.username = username;
            return this;
        }

        public Builder nickName(String nickName) {
            user.nickName = nickName;
            return this;
        }

        public Builder password(String password) {
            user.password = password;
            return this;
        }

        public Builder locked(Boolean locked) {
            user.locked = locked;
            return this;
        }

        public Builder expired(Boolean expired) {
            user.expired = expired;
            return this;
        }

        public Builder enabled(Boolean enabled) {
            user.enabled = enabled;
            return this;
        }

        public Builder registerDate(Date registerDate) {
            user.registerDate = registerDate;
            return this;
        }

        public Builder personId(Long personId) {
            user.personId = personId;
            return this;
        }

        public Builder lastPasswordResetDate(Date lastPasswordResetDate) {
            user.lastPasswordResetDate = lastPasswordResetDate;
            return this;
        }

        public Builder authorities(Collection<GrantedAuthority> authorities) {
            user.authorities = authorities;
            return this;
        }

        public User build() {
            return user;
        }
    }
}