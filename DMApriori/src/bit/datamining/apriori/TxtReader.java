package bit.datamining.apriori;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
/**
 * ��ģ�鸺��������ļ����д���������Ҫ�ĸ�
 * 
 * */
public class TxtReader {
	//�����ļ�����λ��
	private static final String INPUT_FILE = "diagnosis.data";
	/**
	 * Get symptoms and disease record from file given;
	 * @author yanximin
	 * @return the record of symptoms and disease in the form of List
	 * */
	public static List<Diagnose> getRecord() throws IOException{
		List<Diagnose> record = new ArrayList<Diagnose>();
		File file = new File(INPUT_FILE);
		FileInputStream fileInputStream=null;
		try {
			fileInputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			System.out.println("�ļ��򿪴���");
			e.printStackTrace();
			return null;
		}
		InputStreamReader reader = new InputStreamReader(fileInputStream);
		BufferedReader br = new BufferedReader(reader);
		String tempString = "";
		while((tempString=br.readLine())!=null){
			String[] infomations = tempString.split("\\t");
			//���� d2 decision: Nephritis of renal pelvis origin { yes, no } 
			//���� d1 �ĳ� infomations.length-2 ���ɣ�
			String dis = infomations[infomations.length-2];
			Diagnose diagnose = new Diagnose();
			List<String> tempSymptoms = new ArrayList<>();
			for(int i =0;i<infomations.length-3;i++){
				tempSymptoms.add(i+infomations[i]);
			}
			diagnose.setSymptoms(tempSymptoms);
			
			diagnose.setDisease(dis);
			record.add(diagnose);
		}
		br.close();
		reader.close();
		fileInputStream.close();
		return record;
	}  
}
