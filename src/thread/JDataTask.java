package thread;

/**
 * JSON任务－测试代码
 * 
 * @author LeeFranker
 * 
 */
public interface JDataTask {
	
	public static final int METHOD_GET_ONE = 0x0001;
	
	public static final int METHOD_GET_TWO = 0x0002;
	
	public abstract class AbsOnAnyTimeCallBack{
		
		public void onNetWorkException(Object...objects){};
		
		public void onPreExecuteCallBack(Object...objects){};
		
		public void onProgressUpdateCallBack(Object...objects){};
		
		public void onCancelledCallBack(Object...objects){};
		
		public abstract void onPostExecuteCallBack(Object...objects);
		
	}

}
