package com.cloudyang.info;


public class SmsInfo{
	/*
	 * ��������
	*/
	private String smsBody;
	/*
	 * ���ŵ绰����
	*/
	private String phoneNumber;
	/*
	 * ���ŵ����ں�ʱ��
	*/
	private String date;
	/*
	 * ����������
	*/
	private String name;
	/*
	 * �������ͣ�1�ǽ��գ�2���ѷ���
	*/
	private String type;
	
	public String getSmsbody() {
		return smsBody;
	}
	
	public void setSmsbody(String smsBody){
		this.smsBody = smsBody;
	}
	
	public String getPhoneNumber(){
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}
	
	public String getDate(){
		return date;
	}
	
	public void setDate(String date){
		this.date = date;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getType(){
		return type;
	}
	
	public void setType(String type){
		this.type = type;
	}

	/*@Override
	public SmsInfo clone(){
		SmsInfo oInfo = null;
		try {
			oInfo = (SmsInfo) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return oInfo;
	}*/
	
	/*public static List deepCopy(ArrayList src) throws IOException, ClassNotFoundException{   
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();   
        ObjectOutputStream out = new ObjectOutputStream(byteOut);   
        out.writeObject(src);   
       
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());   
        ObjectInputStream in =new ObjectInputStream(byteIn);   
        ArrayList dest = (ArrayList)in.readObject();   
        return dest;
    }*/
	
	

}
