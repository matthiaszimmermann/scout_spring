package org.eclipse.scout.apps.helloworld.server.permission;

import java.security.PermissionCollection;

import org.eclipse.scout.rt.shared.services.common.security.AbstractAccessControlService;

/**
 * <h3>{@link AccessControlService}</h3>
 *
 * @author mzi
 */
public class AccessControlService extends AbstractAccessControlService<String> {

	  @Override
	  protected String getCurrentUserCacheKey() {
	    return getUserIdOfCurrentUser();
	  }

	  @Override
	  protected PermissionCollection execLoadPermissions(String userId) {
	    return null;
	  }
	}
