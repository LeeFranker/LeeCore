package designpattern.prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Prototype_1 implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;

	private String string;

	private SerializableObject mObject;
	
	/**
	 * 浅赋值
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {

		Prototype_1 prototype = (Prototype_1) super.clone();
		return prototype;

	}
	
	/**
	 * 深赋值
	 */
	public Object deepClone() throws IOException,ClassNotFoundException{
		//写入当前对象的二进制流
		ByteArrayOutputStream bos =new ByteArrayOutputStream();
		ObjectOutputStream oos=new ObjectOutputStream(bos);
		oos.writeObject(this);
		//读出二进制流产生的新对象
		ByteArrayInputStream bis=new ByteArrayInputStream(bos.toByteArray());
		ObjectInputStream ois= new ObjectInputStream(bis);
		return ois.readObject();
	}
	
	private class SerializableObject implements Serializable{
		
		public static final long serialVersionUID=1L;
		
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public SerializableObject getObject() {
		return mObject;
	}

	public void setObject(SerializableObject object) {
		this.mObject = object;
	}


}
