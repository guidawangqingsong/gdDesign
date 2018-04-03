package cn.jjxx.modules.iface.controller;

public class IfcConstants {

	public static final String WBS_NOSTART = "0";//未施工
	public static final String WBS_START = "1";//施工中
	public static final String WBS_PAUSE = "2";//暂停施工
	public static final String WBS_FINISH = "3";//完成施工
	
	public static enum WbsTaskStatus{
		WBS_NOSTART,WBS_START,WBS_PAUSE,WBS_FINISH
	}
	
	public static String getStatus(String status){
		String type = WbsTaskStatus.WBS_NOSTART.toString();
		switch (status) {
		case "0":
			type = WbsTaskStatus.WBS_NOSTART.toString();
			break;
		case "1":
			type = WbsTaskStatus.WBS_START.toString();
			break;
		case "2":
			type = WbsTaskStatus.WBS_PAUSE.toString();
			break;
		case "3":
			type = WbsTaskStatus.WBS_FINISH.toString();
			break;
		}
		return type;
	}
}
