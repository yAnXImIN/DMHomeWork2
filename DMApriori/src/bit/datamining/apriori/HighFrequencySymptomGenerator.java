package bit.datamining.apriori;

import java.util.ArrayList;
import java.util.List;

public class HighFrequencySymptomGenerator {
	public static List<Diagnose> record ;
	public static List<List<String>> highFreSymptom = new ArrayList<>();
	private static List<String> singleHighSym = new ArrayList<>();
	public static double MIN_SUPPORT = 0.25;
	private static List<Integer> distrute;
	public static void getHighFreSymptom(){
		List<String> dic = new ArrayList<>();
		//�����ʵ�
		for(int i=0;i<record.size();i++){
			for(int j=0;j<record.get(i).getSymptoms().size();j++){
				if(!dic.contains(record.get(i).getSymptoms().get(j))){
					dic.add(record.get(i).getSymptoms().get(j));
				}
			}
		}
		//ɨ���Ƶ�ʻ�
		for(int i=0;i<dic.size();i++){
			int tempCount=0;
			for(int j=0;j<record.size();j++){
				for(int k=0;k<record.get(j).getSymptoms().size();k++){
					if(dic.get(i).equals(
							record.get(j).getSymptoms().get(k)
							)){
						tempCount++;
					}
				}
			}
			if(tempCount>MIN_SUPPORT*record.size()){
				singleHighSym.add(dic.get(i));
			}
		}
		distrute = new ArrayList<>();
		distrute.add(0);
		//��1-�����highFreSymptom��
		for(int i=0;i<singleHighSym.size();i++){
			List<String> tempList = new ArrayList<>();
			tempList.add(singleHighSym.get(i));
			highFreSymptom.add(tempList);
		}
		distrute.add(highFreSymptom.size());
		while(true){
			List<List<String>> smallSet = new ArrayList<>();
			for(int i=distrute.get(distrute.size()-2);i<distrute.get(distrute.size()-1);i++){
				List<String> tempList = new ArrayList<>();
				for(int j=0;j<highFreSymptom.get(i).size();j++){
					tempList.add(new String(highFreSymptom.get(i).get(j)));
				}
				smallSet.add(tempList);
			}
			List<List<String>> largeSet = new ArrayList<>();
			//���ɺ�ѡ��
			for(int i=0;i<smallSet.size();i++){
				for(int j=0;j<singleHighSym.size();j++){
					List<String> tempList = copy(smallSet.get(i));
					boolean addFlag = true;
					boolean isExsist = tempList.contains(singleHighSym.get(j));
					tempList.add(singleHighSym.get(j));
					for(int k=0;k<largeSet.size();k++){
						if(isSameSet(tempList, largeSet.get(k))){
							addFlag = false;
						}
					}
					if(addFlag==false||isExsist){
						tempList.remove(tempList.size()-1);
					}
					else{
						largeSet.add(tempList);
					}
				}
			}
			//�������ż�
			for(int i=0;i<largeSet.size();i++){
				int tempCount = 0;
				for(int j=0;j<record.size();j++){
					if(isChildSet(largeSet.get(i), record.get(j).getSymptoms())){
						tempCount++;
					}
				}
				if(tempCount>MIN_SUPPORT*record.size()){
					highFreSymptom.add(largeSet.get(i));
				}
			}
			if(highFreSymptom.size()==distrute.get(distrute.size()-1)){
				break;
			}else{
				distrute.add(highFreSymptom.size());
			}
		}
	}
	/**
	 * ���һ��List�Ƿ�Ϊ��һ��List���Ӽ�
	 * */
	public static boolean isChildSet(List<String> child,List<String> parent){
		if(child.size()>parent.size()){
			return false;
		}
		for(int i=0;i<child.size();i++){
			if(!parent.contains(child.get(i))){
				return false;
			}
		}
		return true;
	}
	/**
	 * ������������Ƿ���ȡ�
	 * */
	private static boolean isSameSet(List<String> child,List<String> parent){
		if(toHash(child)==toHash(parent)){
			return true;
		}
		else{
			return false;
		}
	}
	private static List<String> copy(List<String> parent){
		List<String> list = new ArrayList<>();
		for(int i=0;i<parent.size();i++){
			list.add(new String(parent.get(i)));
		}
		return list;
	}
	private static int toHash(List<String> list){
		int hashcode = 0;
		for(String temp:list){
			hashcode+=temp.hashCode();
		}
		return hashcode;
	}
}
