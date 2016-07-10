package bit.datamining.apriori;

import java.util.List;
/**
 * 输入的每条诊断的POJO，包含症状和诊断结果
 * */
public class Diagnose {
	private List<String> symptoms;
	private String disease;
	public List<String> getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(List<String> symptoms) {
		this.symptoms = symptoms;
	}
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("");
		for(String s :symptoms){
			sb.append(s).append("\t");
		}
		sb.append(disease);
		return sb.toString();
	}

	
}
