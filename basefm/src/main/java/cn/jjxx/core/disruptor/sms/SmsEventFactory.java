package cn.jjxx.core.disruptor.sms;

import com.lmax.disruptor.EventFactory;

/**
 * 
 * All rights Reserved, Designed By www.jjxxkj.cn
 * 
 * @title: LongEventFactory.java
 * @package cn.jjxx.core.disruptor.sms
 * @description: 定义事件工厂
 * @author: www.jjxxkj.cn
 * @date: 2017年6月7日 下午11:18:32
 * @version V1.0
 * @copyright: 2017 www.jjxxkj.cn Inc. All rights reserved.
 *
 */
public class SmsEventFactory implements EventFactory<SmsEvent> {
	public SmsEvent newInstance() {
		return new SmsEvent();
	}
}