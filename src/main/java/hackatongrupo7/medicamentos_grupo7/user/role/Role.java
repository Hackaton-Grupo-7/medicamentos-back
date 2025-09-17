package hackatongrupo7.medicamentos_grupo7.user.role;

public enum Role {
    USER,
    ADMIN;

    public String getRoleName() {
        return "ROLE_" + this.name();
    }

    }
