package chenyang.dynamic;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class TireCheck {
	//使用位运算，根据已经包含的步骤计算一个key。这样所有包含相同步骤的plans（尽管排序不同）将会
	//得到相同的key。使用这个key来map所有这些plan中max_pressure最大的可行plan。
	private HashMap<Long, CheckPlan> plan_dict;
	// private Queue<CheckPlan> plan_part;
	private PlanStep[] available_steps;
	
	private class CheckPlan{
		private LinkedList<Integer> steps;
		private Long plan_key = 0L;
		private int max_pressure = 0;
//		TODO: private int min_pressure = 0;
		private int result_pressure = 0;
		
		public CheckPlan add_step(int step_index) {
			if (steps.contains(step_index)) {
				return null;
			}
			
			if (result_pressure + available_steps[step_index].pres_inc > 100) {
				return null;
			}
			
			if (result_pressure + available_steps[step_index].pres_inc - 
					available_steps[step_index].pres_dec < 0) {
				return null;
			}
			
			
			CheckPlan new_plan = new CheckPlan();
			new_plan.steps = new LinkedList<Integer>();
			new_plan.steps.addAll(steps);
			new_plan.steps.add(step_index);
			
			new_plan.plan_key = plan_key | (1 << step_index);
			
			if (result_pressure + available_steps[step_index].pres_inc > max_pressure) {
				new_plan.max_pressure = result_pressure + available_steps[step_index].pres_inc;
			}else {
				new_plan.max_pressure = max_pressure;
			}
			
			new_plan.result_pressure = result_pressure + available_steps[step_index].pres_inc -
					available_steps[step_index].pres_dec;
			
			return new_plan;
		}
	}
	
	public void init() {
		plan_dict = new HashMap<Long, CheckPlan>();
		// plan_part = new LinkedList<CheckPlan>();
		available_steps = new PlanStep[3];
		available_steps[0] = new PlanStep(99, 94); //5
		available_steps[1] = new PlanStep(5, 10);
		available_steps[2] = new PlanStep(10, 5);
	}
	
	public CheckPlan create_plan() {
		CheckPlan plan = new CheckPlan();
		plan.steps = new LinkedList<Integer>();
		//plan_part.add(plan);
		plan_dict.put(0L, plan);
		
		while(!plan_dict.isEmpty()) {
			Long plan_key = (Long) plan_dict.keySet().toArray()[0];
			plan = plan_dict.get(plan_key);
			
			if (plan.steps.size() == available_steps.length) { break; } //All steps were adopted already.
			
			for (int i = 0; i < available_steps.length; i++) {
				CheckPlan next_plan = plan.add_step(i);
				if (next_plan == null) { continue; }
				
				if (plan_dict.containsKey(next_plan.plan_key)) {
					CheckPlan known_plan = plan_dict.get(next_plan.plan_key);
					if (next_plan.max_pressure > known_plan.max_pressure) {
						plan_dict.put(next_plan.plan_key, next_plan);
					}
				}else {
					plan_dict.put(next_plan.plan_key, next_plan);
				}
			}
			
			plan_dict.remove(plan.plan_key);
		}
		
		assert(plan_dict.size() == 1);
		return plan;
	}
	
	public static void main(String[] args) {
		TireCheck test = new TireCheck();
		test.init();
		TireCheck.CheckPlan plan = test.create_plan();
		System.out.println(plan.max_pressure);
	}
}

class PlanStep{
	int pres_inc;
	int pres_dec;
	
	public PlanStep(int inc, int dec) {
		pres_inc = inc;
		pres_dec = dec;
	}
}
