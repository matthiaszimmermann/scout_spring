package org.eclipse.scout.apps.helloworld.server.helloworld;

import org.eclipse.scout.apps.helloworld.server.ServerSession;
import org.eclipse.scout.apps.helloworld.shared.IGreetingService;
import org.eclipse.scout.apps.helloworld.shared.helloworld.GreetingFormData;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.testing.platform.runner.RunWithSubject;
import org.eclipse.scout.rt.testing.server.runner.RunWithServerSession;
import org.eclipse.scout.rt.testing.server.runner.ServerTestRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * <h3>{@link GreetingServiceTest}</h3>
 *
 * @author mzi
 */
@RunWith(ServerTestRunner.class)
@RunWithSubject(GreetingServiceTest.SUBJECT_NAME)
@RunWithServerSession(ServerSession.class)
public class GreetingServiceTest {
	public static final String SUBJECT_NAME = "test_subject";

	@Test
	public void testMessageContainsSubjectName() {
		GreetingFormData input = new GreetingFormData();
		input = BEANS.get(IGreetingService.class).load(input);

		Assert.assertNotNull(input.getMessage());
		Assert.assertEquals("[1, 'Hello " + SUBJECT_NAME + "!']", input.getMessage().getValue());
	}
}
