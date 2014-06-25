package no.helsebiblioteket.evs.plugin.result;

import no.helsebiblioteket.admin.domain.LoggedInOrganization;

import java.io.Serializable;

/**
 * Created by mla on 25/06/14.
 */
public class LoggedInOrganizationWrapper
        implements Serializable
{
    private transient LoggedInOrganization wrapped;

    public LoggedInOrganizationWrapper(final LoggedInOrganization wrapped)
    {
        this.wrapped = wrapped;
    }

    public LoggedInOrganization getWrapped() {
        return wrapped;
    }
}
