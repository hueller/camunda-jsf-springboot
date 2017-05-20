package de.hsansbach.ecommerce.process;

import static org.junit.Assert.assertEquals;

import org.camunda.bpm.engine.impl.ProcessEngineImpl;
import org.camunda.bpm.engine.impl.test.TestHelper;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.hsansbach.ecommerce.CamundaJsfSpringbootProcessApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CamundaJsfSpringbootProcessApplication.class)
public class SimpleProcessTest extends AbstractProcessTest {

	@Test
	@Deployment
	public void startProcess() {
		camundaProcessService.startProcess(ProcessKey.SIMPLE);
		
		TestHelper.waitForJobExecutorToProcessAllJobs(((ProcessEngineImpl) processEngine).getProcessEngineConfiguration(), 10000, 50);

		assertEquals(0, camundaProcessService.getRuntimeService().createProcessInstanceQuery().count());
	}
}
