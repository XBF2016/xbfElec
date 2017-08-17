package com.xbf.elec.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xbf.elec.dao.IElecTestDAO;
import com.xbf.elec.dao.util.Conditions;
import com.xbf.elec.dao.util.Conditions.Operator;
import com.xbf.elec.domain.ElecTest;

public class TestDAO {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"beans.xml");
		// DAO
		IElecTestDAO elecTestDAO = (IElecTestDAO) applicationContext
				.getBean("elecTestDAO");

		// 添加
		// ElecTest elecTest = new ElecTest();
		// elecTest.setTestName("add");
		// elecTestDAO.addOrUpdate(elecTest);

		// 添加所有
		// List<ElecTest> list = new ArrayList<ElecTest>();
		// ElecTest elecTest1 = new ElecTest();
		// elecTest1.setTestName("addAll1");
		// ElecTest elecTest2 = new ElecTest();
		// elecTest2.setTestName("addAll2");
		// list.add(elecTest1);
		// list.add(elecTest2);
		// elecTestDAO.addOrUpdateAll(list);

		// 更新
		// ElecTest elecTest = new ElecTest();
		// elecTest.setTestName("update");
		// elecTest.setTestId("8a70418c54d20cfb0154d20cff920001");
		// elecTestDAO.addOrUpdate(elecTest);

		// 更新所有
		// List<ElecTest> list = new ArrayList<ElecTest>();
		// ElecTest elecTest1 = new ElecTest();
		// elecTest1.setTestId("8a70418c54d20f5f0154d20f637f0001");
		// elecTest1.setTestName("updateAll1");
		// ElecTest elecTest2 = new ElecTest();
		// elecTest2.setTestId("8a70418c54d20f5f0154d20f63890002");
		// elecTest2.setTestName("updateAll2");
		// list.add(elecTest1);
		// list.add(elecTest2);
		// elecTestDAO.addOrUpdateAll(list);

		// 删除
		// ElecTest elecTest1 = new ElecTest();
		// elecTest1.setTestId("8a70418c54d20f5f0154d20f637f0001");
		// elecTestDAO.delete(elecTest1);
		// elecTestDAO.deleteById("8a70418c54c8ced00154c8ced4560001");

		// 删除所有
		// Serializable[] ids = { "8a70418c54cb9a300154cb9a33f90001",
		// "8a70418c54cb9adf0154cb9ae2cd0001" };
		// elecTestDAO.deleteAllByIds(ids);
		// List<ElecTest> list = new ArrayList<ElecTest>();
		// ElecTest elecTest1 = new ElecTest();
		// elecTest1.setTestId("8a70418c54c8cff10154c8cff4b20001");
		// ElecTest elecTest2 = new ElecTest();
		// elecTest2.setTestId("8a70418c54cb91c20154cb91c57e0001");
		// list.add(elecTest1);
		// list.add(elecTest2);
		// elecTestDAO.deleteAll(list);

		// 查询所有
		// List<ElecTest> list = elecTestDAO.findAll();
		// for (ElecTest elecTest : list) {
		// System.out.println(elecTest);
		// }

		// 条件查询
		Conditions conditions = new Conditions();
		conditions.addCondition("testName", null, Operator.LIKE);
		conditions.addOrderBy("testName", true);
		conditions.addOrderBy("testId", false);
		List<ElecTest> elecTests = elecTestDAO.findByConditions(conditions);
		for (ElecTest elecTest : elecTests) {
			System.out.println(elecTest);
		}

	}
}
