package bit.datamining.apriori;

import java.io.IOException;
import java.util.List;

public class MainMethod {
	public static void main(String[] args) {
		long startime = System.currentTimeMillis();
		
//		����Ԥ������
		List<Diagnose> list;
		try {
			list = TxtReader.getRecord();
		} catch (IOException e) {
			System.out.println("�ļ���ȡʧ��");
			return;
		}
		if(list==null||list.size()==0){
			System.out.println("�޹���");
			return;
		}
//		�����ʵ����
//		for(Diagnose d :list){
//			System.out.println(d);
//		}
		
		//��ȡ��Ƶ��֢״����
		HighFrequencySymptomGenerator.record = list;
		HighFrequencySymptomGenerator.MIN_SUPPORT = 0.3;
		HighFrequencySymptomGenerator.getHighFreSymptom();
		
		//��ȡ��Ƶ���򼯺�
		HighFreaquencyRuleGenerator.MIN_CONFIDENCE = 0.4;
		HighFreaquencyRuleGenerator.MIN_SUPPORT = 0.3;
		HighFreaquencyRuleGenerator.highFreSymptom = HighFrequencySymptomGenerator.highFreSymptom;
		HighFreaquencyRuleGenerator.record = HighFrequencySymptomGenerator.record;
		HighFreaquencyRuleGenerator.generateRules();
		List<Rule> rules = HighFreaquencyRuleGenerator.rules;
		long endstime = System.currentTimeMillis();
		
		//������ʵ��
		System.out.println("������Լ���С��"+HighFrequencySymptomGenerator.record.size());
		System.out.println("���ҵ�"+HighFrequencySymptomGenerator.highFreSymptom.size()+"��Ƶ��֢״��");
		System.out.println("���ҵ�"+HighFreaquencyRuleGenerator.highFreDisease.size()+"��Ƶ������");
		System.out.println("���ҵ�"+rules.size()+"���������ŵĹ���������ʾ");
		for(Rule rule:rules){
			System.out.println(rule);
		}
		System.out.println("��������ʱ�䣺"+(endstime-startime)+"����");
	}
}
