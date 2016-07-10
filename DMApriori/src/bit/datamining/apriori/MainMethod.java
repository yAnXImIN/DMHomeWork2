package bit.datamining.apriori;

import java.io.IOException;
import java.util.List;

public class MainMethod {
	public static void main(String[] args) {
		long startime = System.currentTimeMillis();
		
//		规则预处理部分
		List<Diagnose> list;
		try {
			list = TxtReader.getRecord();
		} catch (IOException e) {
			System.out.println("文件读取失败");
			return;
		}
		if(list==null||list.size()==0){
			System.out.println("无规则");
			return;
		}
//		输出事实集合
//		for(Diagnose d :list){
//			System.out.println(d);
//		}
		
		//获取高频的症状集合
		HighFrequencySymptomGenerator.record = list;
		HighFrequencySymptomGenerator.MIN_SUPPORT = 0.3;
		HighFrequencySymptomGenerator.getHighFreSymptom();
		
		//获取高频规则集合
		HighFreaquencyRuleGenerator.MIN_CONFIDENCE = 0.4;
		HighFreaquencyRuleGenerator.MIN_SUPPORT = 0.3;
		HighFreaquencyRuleGenerator.highFreSymptom = HighFrequencySymptomGenerator.highFreSymptom;
		HighFreaquencyRuleGenerator.record = HighFrequencySymptomGenerator.record;
		HighFreaquencyRuleGenerator.generateRules();
		List<Rule> rules = HighFreaquencyRuleGenerator.rules;
		long endstime = System.currentTimeMillis();
		
		//输出结果实例
		System.out.println("输入测试集大小："+HighFrequencySymptomGenerator.record.size());
		System.out.println("共找到"+HighFrequencySymptomGenerator.highFreSymptom.size()+"个频繁症状集");
		System.out.println("共找到"+HighFreaquencyRuleGenerator.highFreDisease.size()+"个频繁疾病");
		System.out.println("共找到"+rules.size()+"个可以置信的规则，如下所示");
		for(Rule rule:rules){
			System.out.println(rule);
		}
		System.out.println("程序所用时间："+(endstime-startime)+"毫秒");
	}
}
