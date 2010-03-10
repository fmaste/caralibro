package caralibro.integrator;

import java.util.Collection;

public interface IKeepconManager {

	public Collection<Feed> getFeedsToRemove(Collection<Feed> feeds) throws Exception;
}
