package no.helsebiblioteket.evs.plugin;

import java.io.Serializable;
import no.helsebiblioteket.admin.domain.LoggedInUser;

/**
 * Created by mla on 30/06/14.
 */
public class LoggedInUserWrapper implements Serializable {

    public static final long serialVersionUID = 1L;

    private transient LoggedInUser wrapped;

    public LoggedInUserWrapper ( final LoggedInUser wrapped ) {
        this.wrapped = wrapped;
    }

    public LoggedInUser getWrapped() {
        return wrapped;
    }

}
